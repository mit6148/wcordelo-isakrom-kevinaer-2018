package io.smartraise.dao;

import io.smartraise.model.fundraise.Organization;
import io.smartraise.model.accounts.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberDAO extends MongoRepository<Member, String> {

    List<Member> findByOrganizationsIn(Organization organization);

    Member findByEmail(String email);
}
