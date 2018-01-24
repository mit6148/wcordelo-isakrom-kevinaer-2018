package io.smartraise.service.impl;

import io.smartraise.dao.ContactInformationDAO;
import io.smartraise.dao.MemberDAO;
import io.smartraise.util.Parser;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ContactInformationDAO contactInformationDAO;

    @Override
    public Member get(String id) throws Exception{
        if (Parser.isEmail(id)) {
            return memberDAO.findByEmail(id);
        } else {
            return memberDAO.findOne(id);
        }
    }

    @Override
    public void create(Member member) throws Exception {
        if (isValid(member) && !memberDAO.exists(member.getUsername())) {
            memberDAO.save(member);
            contactInformationDAO.save(member.getContactInformation());
        } else {
            throw new Exception("Member already exists");
        }
    }

    @Override
    public Member getPublic(Member member) throws Exception {
        return new Member(member.getFirstName(), member.getLastName(), member.getUsername());
    }

    @Override
    public void update(Member member) throws Exception {
        if (memberDAO.exists(member.getUsername())) {
            memberDAO.save(member);
            contactInformationDAO.save(member.getContactInformation());
        } else {
            throw new Exception("No member found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (memberDAO.exists(id)) {
            contactInformationDAO.delete(id);
            memberDAO.delete(id);
        } else {
            throw new Exception("No member found");
        }
    }

    @Override
    public boolean isValid(Member member) {
        return !(member.getUsername().isEmpty() && member.getEmail().isEmpty());
    }

    @Override
    public Set<Member> getMembersFromOrganization(Organization organization) throws Exception {
        return new HashSet<>(memberDAO.findAllByUsernameIn(organization.getMembers()));
    }

    @Override
    public Set<Member> getAdminsFromOrganization(Organization organization) throws Exception {
        return new HashSet<>(memberDAO.findAllByUsernameIn(organization.getAdmin()));
    }

    @Override
    public void addOrganization(String username, String organizationId) throws Exception{
        Member member = this.get(username);
        member.addOrganization(organizationId);
        this.update(member);
    }

    @Override
    public void removeOrganization(String username, String organizationId) throws Exception{
        Member member = this.get(username);
        member.removeOrganization(organizationId);
        this.update(member);
    }

    @Override
    public boolean exists(String id) {
        return memberDAO.exists(id);
    }
}
