package io.smartraise.model.fundraise;

import io.smartraise.model.accounts.Member;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class Organization {

    @Id
    private final long organizationId;
    private String name;
    private String descrption;
    private String website;
    private final Set<Member> members;
    private final Set<Member> admin;

    public Organization(){
        this.organizationId = 0;
        this.name = "";
        this.descrption = "";
        this.website = "";
        this.members = new HashSet<>();
        this.admin = new HashSet<>();
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
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

    public Set<Member> getAdmin() {
        return admin;
    }

    public void addAdmin(Member member) {
        if (!members.contains(member)){
            this.addMember(member);
        }
        this.admin.add(member);
    }
}
