package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;
import io.smartraise.service.skeleton.AccountService;

public interface MemberService extends AccountService<Member> {

    void createFromCredential(Credential credential) throws Exception;

    Member getPublic(Member member) throws Exception;
}
