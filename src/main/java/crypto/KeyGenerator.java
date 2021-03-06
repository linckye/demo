package crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author linckye
 */
public class KeyGenerator {

    private static final String prefix = "pass ";

    public static String encode(String content, String secret) throws Exception {
        secret = getSecret(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal((prefix + content).getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decode(String content, String secret) throws Exception {
        secret = getSecret(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
    }

    public static String getSecret(String secret) {
        int padding = 16 - secret.length();
        StringBuilder secretBuilder = new StringBuilder(secret);
        for (int i = 0; i < padding; i++) {
            secretBuilder.append("x");
        }
        secret = secretBuilder.toString();
        return secret;
    }

}