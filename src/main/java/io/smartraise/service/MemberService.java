package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.skeleton.AccountCrudService;
import io.smartraise.service.skeleton.ValidationService;

import java.util.Set;

/**
 * Service for the {@link Member} model
 */
public interface MemberService extends AccountCrudService<Member>, ValidationService<Member> {

    /**
     * Returns the publicly viewable version of a {@link Member} model
     * @param member    the {@link Member} model
     * @return a censored version of the {@link Member} hiding user details
     */
    Member getPublic(Member member);

    /**
     * Gets all {@link Member} in an {@link Organization}
     * @param organization    the {@link Organization}
     * @return  a set of all {@link Member} in the {@link Organization}
     */
    Set<Member> getMembersFromOrganization(Organization organization);

    /**
     * Gets all admin {@link Member} in an {@link Organization}
     * @param organization    the {@link Organization}
     * @return  a set of all admin {@link Member} in the {@link Organization}
     */
    Set<Member> getAdminsFromOrganization(Organization organization);

    /**
     * Adds an {@link Organization} to the {@link Member}'s organization
     * @param username  the username of the {@link Member}
     * @param organizationId    the id of the {@link Organization}
     */
    void addOrganization(String username, String organizationId);

    /**
     * Removes an {@link Organization} from the {@link Member}'s organization
     * @param username  the username of the {@link Member}
     * @param organizationId    the id of the {@link Organization}
     */
    void removeOrganization(String username, String organizationId);
}
