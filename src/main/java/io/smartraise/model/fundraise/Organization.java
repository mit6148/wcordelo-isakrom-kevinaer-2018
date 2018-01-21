package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Member;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Organization {

    @Id
    private final UUID organizationId;
    private String name;
    private String description;
    private String website;
    private final Set<Member> members;
    private final Set<Member> admin;

    public Organization(){
        this.organizationId = UUID.randomUUID();
        this.name = "";
        this.description = "";
        this.website = "";
        this.members = new HashSet<>();
        this.admin = new HashSet<>();
    }

    public UUID getOrganizationId() {
        return organizationId;
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

    public Set<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        if (this.admin.contains(member)){
            this.admin.remove(member);
        }
        this.members.remove(member);
    }

    public void removeAdmin(Member member) {
        this.admin.remove(member);
    }

    public Set<Member> getAdmin() {
        return new HashSet<>(admin);
    }

    public void addAdmin(Member member) {
        if (!members.contains(member)){
            this.addMember(member);
        }
        this.admin.add(member);
    }
}
