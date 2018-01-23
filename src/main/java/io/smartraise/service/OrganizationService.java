package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.skeleton.ItemService;

import java.util.Set;
import java.util.UUID;

/**
 * Service for the {@link Organization} model
 */
public interface OrganizationService extends ItemService<Organization> {

//    /**
//     * Gets all {@link Member} in an {@link Organization}
//     * @param id    the id of the {@link Organization}
//     * @return  a set of all {@link Member} in the {@link Organization}
//     * @throws Exception
//     */
//    Set<Member> getMembers(UUID id) throws Exception;
//
//    /**
//     * Gets all admin {@link Member} in an {@link Organization}
//     * @param id    the id of the {@link Organization}
//     * @return  a set of all admin {@link Member} in the {@link Organization}
//     * @throws Exception
//     */
//    Set<Member> getAdmins(UUID id) throws Exception;

    /**
     *  Adds a {@link Member} into the {@link Organization}
     * @param member    the {@link Member} being added
     * @param id    the id of the {@link Organization}
     * @throws Exception
     */
    void addMember(Member member, String id) throws Exception;

    /**
     *  Removes a {@link Member} from the {@link Organization}
     * @param member    the {@link Member} being removed
     * @param id    the id of the {@link Organization}
     * @throws Exception
     */
    void deleteMember(Member member, String id) throws Exception;

    /**
     *  Adds a {@link Member} into the {@link Organization}'s admin list
     * @param member    the {@link Member} being added to the admin list
     * @param id    the id of the {@link Organization}
     * @throws Exception
     */
    void addAdmin(Member member, String id) throws Exception;

    /**
     *  Removes a {@link Member} from the {@link Organization}'s admin list
     * @param member    the {@link Member} being removed from the admin list
     * @param id    the id of the {@link Organization}
     * @throws Exception
     */
    void deleteAdmin(Member member, String id) throws Exception;

    /**
     * Gets all organization a {@link Member} is a part of
     * @param member  the {@link Member}
     * @return the set of organizations a {@link Member is a part of}
     * @throws Exception
     */
    Set<Organization> getFromMember(Member member) throws Exception;

}
