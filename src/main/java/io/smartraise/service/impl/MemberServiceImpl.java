package io.smartraise.service.impl;

import io.smartraise.dao.MemberDAO;
import io.smartraise.helper.Parser;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

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
        } else {
            throw new Exception("Member already exists");
        }
    }

    @Override
    public void createFromCredential(Credential credential) throws Exception {
        Member member = new Member(credential.getEmail(), credential.getUsername());
        this.create(member);
    }

    @Override
    public Member getPublic(Member member) throws Exception {
        return new Member(member.getFirstName(), member.getLastName(), member.getUsername());
    }

    @Override
    public void update(Member member) throws Exception {
        if (memberDAO.exists(member.getUsername())) {
            memberDAO.save(member);
        } else {
            throw new Exception("No member found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (memberDAO.exists(id)) {
            memberDAO.delete(id);
        } else {
            throw new Exception("No member found");
        }
    }

    @Override
    public boolean isValid(Member member) {
        return !(member.getUsername().isEmpty() && member.getEmail().isEmpty());
    }
}
