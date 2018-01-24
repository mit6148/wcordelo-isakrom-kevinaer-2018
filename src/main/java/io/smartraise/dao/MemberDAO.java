package io.smartraise.dao;

import io.smartraise.model.fundraise.Organization;
import io.smartraise.model.accounts.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface MemberDAO extends MongoRepository<Member, String> {

    Member findByEmail(String email);

    Member findByContactInformation_Email(String email);

    List<Member> findAllByUsernameIn(Set<String> usernames);
}
