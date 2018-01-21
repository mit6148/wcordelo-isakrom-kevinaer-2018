package io.smartraise.service;

import io.smartraise.model.accounts.Member;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.skeleton.ItemService;

import java.util.Set;
import java.util.UUID;

public interface OrganizationService extends ItemService<Organization> {

    Set<Member> getMembers(UUID id) throws Exception;

    Set<Member> getAdmins(UUID id) throws Exception;

    void addMember(Member member, UUID id) throws Exception;

    void deleteMember(Member member, UUID id) throws Exception;

    void addAdmin(Member member, UUID id) throws Exception;

    void deleteAdmin(Member member, UUID id) throws Exception;

}
