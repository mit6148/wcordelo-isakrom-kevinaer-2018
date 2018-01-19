package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;

public interface MemberService extends Service<Member> {

    void createFromCredential(Credential credential) throws Exception;

    Member getPublic(Member member) throws Exception;
}
