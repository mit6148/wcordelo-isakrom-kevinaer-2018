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
    public Set<Organization> getFromMember(Member member) throws Exception {
        return new HashSet<>(organizationDAO.findAllByOrganizationIdIn(member.getOrganizations()));
    }

    @Override
    public void addAdmin(Member member, String id) throws Exception {
        Organization organization = this.get(id);
        organization.addAdmin(member);
        this.update(organization);
    }

    @Override
    public void deleteAdmin(Member member, String id) throws Exception {
        Organization organization = this.get(id);
        organization.removeAdmin(member);
        this.update(organization);
    }

    @Override
    public void addMember(Member member, String id) throws Exception {
        Organization organization = this.get(id);
        organization.addMember(member);
        this.update(organization);
    }

    @Override
    public void deleteMember(Member member, String id) throws Exception {
        Organization organization = this.get(id);
        organization.removeMember(member);
        this.update(organization);
    }

    @Override
    public Organization get(String id) throws Exception {
        Organization organization = organizationDAO.findOne(id.toString());
        return organizationDAO.findOne(id.toString());
    }

    @Override
    public Organization create(Organization organization) throws Exception {
        if (isValid(organization) && !organizationDAO.exists(organization.getOrganizationId())) {
            organizationDAO.save(organization);
            return organization;
        } else {
            throw new Exception("Invalid organization");
        }
    }

    @Override
    public void update(Organization organization) throws Exception {
        if (organizationDAO.exists(organization.getOrganizationId())) {
            organizationDAO.save(organization);
        } else {
            throw new Exception("Organization doesn't exist");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        if (organizationDAO.exists(id)) {
            organizationDAO.delete(id);
        } else {
            throw new Exception("Organization doesn't exist");
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
