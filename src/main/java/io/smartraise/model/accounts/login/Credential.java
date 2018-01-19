package io.smartraise.model.accounts.login;

import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class Credential {

    public enum Type {ADMINISTRATOR, CHARITY, DONOR, MEMBER}

    private final Set<Type> types;
    @Id
    private final String username;
    private final String email;
    private final byte[] hash;
    private final byte[] salt;

    public Credential(Type type, String email, byte[] hash, byte[] salt, String username) {
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

    public Set<Type> getTypes() {
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

    public void addType(Type type) {
        this.types.add(type);
    }
}
