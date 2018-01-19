package io.smartraise.dao;

import io.smartraise.model.Organization;
import io.smartraise.model.accounts.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberDAO extends MongoRepository<Member, String> {

    public List<Member> findByOrganizationsIn(Organization organization);
}
