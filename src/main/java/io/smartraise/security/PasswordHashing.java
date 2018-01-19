package io.smartraise.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordHashing {

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hash(String password, byte[] salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        StringBuilder builder = new StringBuilder();
        for (byte b: salt) {
            builder.append(b);
        }
        builder.append(password);
        byte[] hash = digest.digest(builder.toString().getBytes(StandardCharsets.UTF_8));
        return hash;
    }

    public static boolean authenticate(String password, byte[] salt, byte[] hash) throws Exception{
        return Arrays.equals(hash, hash(password, salt));
    }

}
