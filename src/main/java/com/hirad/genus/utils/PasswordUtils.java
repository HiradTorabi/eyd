package com.hirad.genus.utils;

import org.mindrot.jbcrypt.BCrypt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils
{
    public static String hash(String password)
    {
        try
        {

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
return hashedPassword;

//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hashed = digest.digest(password.getBytes());
//            StringBuilder hex = new StringBuilder();
//            for (byte b : hashed)
//            {
//                hex.append(String.format("%02x", b));
//            }
//            return hex.toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}

