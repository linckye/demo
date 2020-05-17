package crypto;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;
import sun.plugin2.message.Message;
import sun.security.provider.MD5;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

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