import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    private static final String ALGORITHM = "AES";
    private static final int KEY_LENGTH = 256; // 256-bit key for strong encryption

    public static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);

            // Initialize with secure random and 256-bit key length
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(KEY_LENGTH, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();

            System.out.println("Secret key generated successfully");
            System.out.println("Algorithm: " + secretKey.getAlgorithm());
            System.out.println("Format: " + secretKey.getFormat());
            System.out.println("Key length: " + (secretKey.getEncoded().length * 8) + " bits");

            return secretKey;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: AES algorithm not available: " + e.getMessage());
            throw new RuntimeException("Failed to generate secret key", e);
        } catch (Exception e) {
            System.err.println("Unexpected error during key generation: " + e.getMessage());
            throw new RuntimeException("Failed to generate secret key", e);
        }
    }

    public static String keyToBase64String(SecretKey secretKey) {
        try {
            if (secretKey == null) {
                throw new IllegalArgumentException("Secret key cannot be null");
            }
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            System.err.println("Error converting key to Base64: " + e.getMessage());
            return "Error encoding key";
        }
    }

    public static String getKeyDisplayString(SecretKey secretKey) {
        try {
            String fullKey = keyToBase64String(secretKey);
            if (fullKey.length() > 16) {
                return fullKey.substring(0, 8) + "..." + fullKey.substring(fullKey.length() - 8);
            }
            return fullKey;
        } catch (Exception e) {
            return "Error displaying key";
        }
    }
}