package io.smartraise.service.impl;

import io.smartraise.dao.EventDAO;
import io.smartraise.dao.OrganizationDAO;
import io.smartraise.model.accounts.Member;
import io.smartraise.model.donations.Donation;
import io.smartraise.model.fundraise.Event;
import io.smartraise.model.fundraise.Organization;
import io.smartraise.service.MemberService;
import io.smartraise.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Override
    public Set<Organization> getFromMember(Member member) {
        return new HashSet<>(organizationDAO.findAllByOrganizationIdIn(member.getOrganizations()));
    }

    @Override
    public void addAdmin(Member member, String id) {
        Organization organization = this.get(id);
        organization.addAdmin(member);
        this.update(organization);
    }

    @Override
    public void deleteAdmin(Member member, String id) {
        Organization organization = this.get(id);
        organization.removeAdmin(member);
        this.update(organization);
    }

    @Override
    public void addMember(Member member, String id) {
        Organization organization = this.get(id);
        organization.addMember(member);
        this.update(organization);
    }

    @Override
    public void deleteMember(Member member, String id) {
        Organization organization = this.get(id);
        organization.removeMember(member);
        this.update(organization);
    }

    @Override
    public Organization get(String id) {
        return organizationDAO.findOne(id.toString());
    }

    @Override
    public boolean create(Organization organization) {
        if (isValid(organization) && !exists(organization.getOrganizationId())) {
            organizationDAO.save(organization);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Organization organization) {
        if (exists(organization.getOrganizationId()) && isValid(organization)) {
            organizationDAO.save(organization);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (organizationDAO.exists(id)) {
            organizationDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(Organization organization) {
        return !organization.getName().isEmpty()
                && organization.getMembers().size() > 0
                && organization.getAdmin().size() > 0
                && organization.getMembers().containsAll(organization.getAdmin());
    }

    @Override
    public boolean exists(String id) {
        return organizationDAO.exists(id);
    }
}
