package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Member;
import org.springframework.data.annotation.Id;

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
    private final Set<String> members;
    private final Set<String> admin;

    public Organization(){
        this.organizationId = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.website = "";
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
