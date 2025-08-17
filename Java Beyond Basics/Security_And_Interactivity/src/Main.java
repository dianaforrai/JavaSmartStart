import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== AES Encryption and Decryption Application ===\n");

            String originalMessage = "Hello, World! This is a secret message that will be encrypted using AES-256 encryption. " +
                    "It contains special characters: @#$%^&*()_+ and numbers: 123456789";

            System.out.println("Original Message:");
            System.out.println("\"" + originalMessage + "\"");
            System.out.println("Message length: " + originalMessage.length() + " characters\n");

            // Step 1: Generate secret key
            System.out.println("=== STEP 1: Generating Secret Key ===");
            javax.crypto.SecretKey secretKey = SecretKeyGenerator.generateSecretKey();
            System.out.println("Key (truncated): " + SecretKeyGenerator.getKeyDisplayString(secretKey));
            System.out.println();

            // Step 2: Encrypt the message
            System.out.println("=== STEP 2: Encrypting Message ===");
            MessageEncryptor.EncryptionResult encryptionResult = MessageEncryptor.encryptMessage(originalMessage, secretKey);

            System.out.println("\nEncrypted Message (Base64):");
            System.out.println("Data: " + encryptionResult.getEncryptedDataAsBase64());
            System.out.println("IV: " + encryptionResult.getIvAsBase64());
            System.out.println();

            // Step 3: Decrypt the message
            System.out.println("=== STEP 3: Decrypting Message ===");
            String decryptedMessage = MessageDecryptor.decryptMessage(encryptionResult, secretKey);

            System.out.println("\nDecrypted Message:");
            System.out.println("\"" + decryptedMessage + "\"");
            System.out.println();

            // Step 4: Verify encryption/decryption success
            System.out.println("=== STEP 4: Verification ===");
            boolean isSuccessful = originalMessage.equals(decryptedMessage);
            System.out.println("Encryption/Decryption Success: " + (isSuccessful ? "✓ YES" : "✗ NO"));

            if (isSuccessful) {
                System.out.println("The decrypted message matches the original message perfectly!");
            } else {
                System.out.println("ERROR: The decrypted message does not match the original!");
                System.out.println("Original:  \"" + originalMessage + "\"");
                System.out.println("Decrypted: \"" + decryptedMessage + "\"");
            }

            System.out.println();

            demonstrateWithDifferentMessages(secretKey);

            demonstrateErrorHandling();

            System.out.println("=== Application completed successfully ===");

        } catch (Exception e) {
            System.err.println("An unexpected error occurred in the main application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateWithDifferentMessages(javax.crypto.SecretKey secretKey) {
        System.out.println("=== ADDITIONAL DEMONSTRATIONS ===");

        String[] testMessages = {
                "Short message",
                "Message with numbers: 1234567890",
                "Special chars: !@#$%^&*()_+-=[]{}|;:'\",.<>?/~`",
                "Unicode: 你好世界 🌍 Привет мир",
                "Long message: " + "A".repeat(1000)
        };

        for (int i = 0; i < testMessages.length; i++) {
            try {
                String message = testMessages[i];
                System.out.println("\nTest " + (i + 1) + ": " +
                        (message.length() > 50 ? message.substring(0, 47) + "..." : message));

                MessageEncryptor.EncryptionResult result = MessageEncryptor.encryptMessage(message, secretKey);
                String decrypted = MessageDecryptor.decryptMessage(result, secretKey);

                boolean success = message.equals(decrypted);
                System.out.println("Result: " + (success ? "✓ SUCCESS" : "✗ FAILED"));

                if (!success) {
                    System.out.println("Expected: " + message);
                    System.out.println("Got: " + decrypted);
                }

            } catch (Exception e) {
                System.out.println("✗ FAILED with exception: " + e.getMessage());
            }
        }
    }

    private static void demonstrateErrorHandling() {
        System.out.println("\n=== ERROR HANDLING DEMONSTRATIONS ===");

        try {
            javax.crypto.SecretKey validKey = SecretKeyGenerator.generateSecretKey();

            // Test 1: Null message encryption
            System.out.println("\nTest: Encrypting null message");
            try {
                MessageEncryptor.encryptMessage(null, validKey);
                System.out.println("✗ Should have thrown exception");
            } catch (RuntimeException e) {
                System.out.println("✓ Correctly handled null message: " + e.getMessage());
            }

            // Test 2: Empty message encryption
            System.out.println("\nTest: Encrypting empty message");
            try {
                MessageEncryptor.encryptMessage("", validKey);
                System.out.println("✗ Should have thrown exception");
            } catch (RuntimeException e) {
                System.out.println("✓ Correctly handled empty message: " + e.getMessage());
            }

            // Test 3: Null key encryption
            System.out.println("\nTest: Encrypting with null key");
            try {
                MessageEncryptor.encryptMessage("Test message", null);
                System.out.println("✗ Should have thrown exception");
            } catch (RuntimeException e) {
                System.out.println("✓ Correctly handled null key: " + e.getMessage());
            }

            // Test 4: Null decryption
            System.out.println("\nTest: Decrypting null result");
            try {
                MessageDecryptor.decryptMessage((MessageEncryptor.EncryptionResult) null, validKey);
                System.out.println("✗ Should have thrown exception");
            } catch (RuntimeException e) {
                System.out.println("✓ Correctly handled null encryption result: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error during error handling demonstration: " + e.getMessage());
        }
    }
}