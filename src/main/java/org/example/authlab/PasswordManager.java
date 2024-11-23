package org.example.authlab;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordManager {

    public final static Random rand = new SecureRandom();

    public static byte[] generateSalt() {
        byte[] salt = new byte[32];
        rand.nextBytes(salt);
        return salt;
    }

    public static String byteArrayToString(byte[] barray) {
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(barray);
    }

    public static byte[] stringToByteArray(String str) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return decoder.decode(str);
    }

    public static String generatePasswordHash(String password, byte[] salt){
        byte[] passwordHash = null;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 512);
            passwordHash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(PasswordManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteArrayToString(passwordHash);
    }

    public static boolean verifyPassword(String email, String enteredPassword) {
        String strSalt = DBManager.retrieveSalt(email);
        String storedPasswordHash = DBManager.retrievePassword(email);

        byte[] bsalt = stringToByteArray(strSalt);
        String calculatedPasswordHash = generatePasswordHash(enteredPassword, bsalt);

        byte[] storedPasswordHashBytes = stringToByteArray(storedPasswordHash);
        byte[] calculatedPasswordHashBytes = stringToByteArray(calculatedPasswordHash);

        return MessageDigest.isEqual(storedPasswordHashBytes, calculatedPasswordHashBytes);





    }
}
