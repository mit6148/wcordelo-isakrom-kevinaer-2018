package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Address;
import io.smartraise.model.accounts.Member;
import io.smartraise.util.CascadeSave;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Model representing a fundraising organization
 */
public class Organization {

    @Id
    private final String organizationId;
    private String name;
    private String description;
    private String website;
    @DBRef
    @CascadeSave
    private Address address;
    private final Set<String> members;
    private final Set<String> admin;

    public Organization(){
        this.organizationId = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.website = "";
        this.address = new Address();
        this.members = new HashSet<>();
        this.admin = new HashSet<>();
    }

    public String getOrganizationId() {
        return this.organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void addMember(String member) {
        this.members.add(member);
    }

    public void removeMember(String member) {
        if (this.admin.contains(member)) {
            this.admin.remove(member);
        }
        this.members.remove(member);
    }

    public void addAdmin(String member) {
        if (!members.contains(member)) {
            members.add(member);
        }
        admin.add(member);
    }

    public void removeAdmin(String member) {
        admin.add(member);
    }

    public void addMember(Member member) {
        this.members.add(member.getUsername());
    }

    public void removeMember(Member member) {
        if (this.admin.contains(member.getUsername())){
            this.admin.remove(member.getUsername());
        }
        this.members.remove(member.getUsername());
    }

    public void removeAdmin(Member member) {
        this.admin.remove(member.getUsername());
    }

    public Set<String> getAdmin() {
        return new HashSet<>(admin);
    }

    public void addAdmin(Member member) {
        if (!members.contains(member.getUsername())){
            this.members.add(member.getUsername());
        }
        this.admin.add(member.getUsername());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return this.organizationId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Organization){
            Organization that = (Organization) obj;
            return this.organizationId.equals(that.organizationId);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Organization {Name: %s, Description: %s}",this.name, this.description);
    }
}
