package io.smartraise.model.login;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;


public class Credential {

    // Currently don't support charity!
    public enum UserType {
        ADMINISTRATOR,
        @Deprecated
        CHARITY,
        @Deprecated
        DONOR,
        MEMBER,
        NONE}

    @Id
    private final String username;
    @Indexed(unique = true)
    private String email;
    private final Set<UserType> types;
    private byte[] hash;
    private byte[] salt;

    public Credential(
            UserType type,
            String email,
            byte[] hash,
            byte[] salt,
            String username) {
        this.types = new HashSet<>();
        this.types.add(type);
        this.email = email;
        this.hash = hash;
        this.salt = salt;
        this.username = username;
    }

    public Credential() {
        this.types = new HashSet<>();
        this.email = "";
        this.hash = new byte[0];
        this.salt = new byte[0];
        this.username = "";
    }

    public Set<UserType> getTypes() {
        return new HashSet<>(types);
    }

    public String getEmail() {
        return email;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public void addType(UserType type) {
        this.types.add(type);
    }

    public void removeType(UserType type) { this.types.remove(type); }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(byte[] salt, byte[] hash) {
        this.salt = salt;
        this.hash = hash;
    }

}
