package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.login.Credential;

public interface MemberService {

    Member get(String id) throws Exception;

    void create(Member member) throws Exception;

    void createFromCredential(Credential credential) throws Exception;

    Member getPublic(Member member) throws Exception;
}
