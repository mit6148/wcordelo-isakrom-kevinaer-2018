package io.smartraise.service.impl;

import io.smartraise.dao.MemberDAO;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public Member get(String id) throws Exception{
        return memberDAO.findOne(id);
    }

    @Override
    public void create(Member member) throws Exception {
        if (member.getEmail() == "") {
            throw new Exception("Fields empty");
        }
        if (memberDAO.exists(member.getEmail())) {
            throw new Exception("Member already exists");
        } else {
            memberDAO.save(member);
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
}
