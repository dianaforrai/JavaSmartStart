import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class MessageEncryptor {

    public static class EncryptionResult {
        private final byte[] encryptedData;
        private final byte[] iv;

        public EncryptionResult(byte[] encryptedData, byte[] iv) {
            this.encryptedData = encryptedData;
            this.iv = iv;
        }

        public String getEncryptedDataAsBase64() {
            return Base64.getEncoder().encodeToString(encryptedData);
        }

        public String getIvAsBase64() {
            return Base64.getEncoder().encodeToString(iv);
        }
    }

    public static EncryptionResult encryptMessage(String message, SecretKey secretKey) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Invalid input for encryption: Message cannot be null or empty");
        }
        if (secretKey == null) {
            throw new IllegalArgumentException("Invalid input for encryption: Secret key cannot be null");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] encryptedData = cipher.doFinal(message.getBytes());

            return new EncryptionResult(encryptedData, iv);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption: " + e.getMessage(), e);
        }
    }
}