package datn.shopmypham.ultil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthTokenUtil {
    private static final int TOKEN_LENGTH = 64;
    private static final long EXPIRATION_TIME = 3600 * 1000; // 1 hour
    private static final String SECRET_KEY = "secret_key";

    public static String generateAuthToken() {
        try {
            // Sinh chuỗi ngẫu nhiên với kích thước TOKEN_LENGTH
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);

            // Sử dụng SHA-256 để mã hóa chuỗi ngẫu nhiên
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(randomBytes);

            // Mã hóa chuỗi băm thành một chuỗi Base64
            String authToken = Base64.getEncoder().encodeToString(hashedBytes);

            return authToken;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}