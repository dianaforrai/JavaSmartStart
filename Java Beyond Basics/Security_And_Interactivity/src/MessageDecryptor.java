import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class MessageDecryptor {

    public static String decryptMessage(MessageEncryptor.EncryptionResult encryptionResult, SecretKey secretKey) {
        if (encryptionResult == null) {
            throw new IllegalArgumentException("Invalid input for decryption: Encryption result cannot be null");
        }
        if (secretKey == null) {
            throw new IllegalArgumentException("Invalid input for decryption: Secret key cannot be null");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(encryptionResult.getIvAsBase64()));

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptionResult.getEncryptedDataAsBase64()));

            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption: " + e.getMessage(), e);
        }
    }
}