import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAndDecryptUtil {
    public static void encryptFile(String filename) {
        try {
            // Read entire file as bytes
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));

            // Generate random AES key (16 bytes)
            byte[] keyBytes = new byte[16];
            new java.util.Random().nextBytes(keyBytes);
            String keyHex = toHex(keyBytes);

            // AES setup
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Encrypt
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            // Save ciphertext
            Files.write(Paths.get("ciphertext.txt"), encryptedBytes);

            // Output info
            System.out.println("File encrypted.");
            System.out.println("Encrypted data saved to ciphertext.txt");
            System.out.println("KEY (hex): " + keyHex);

        } catch (Exception e) {
            System.out.println("Error encrypting file.");
            e.printStackTrace();
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X", b));
        return sb.toString();
    }

    public static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) (
                    (Character.digit(hex.charAt(i), 16) << 4) +
                            Character.digit(hex.charAt(i+1), 16)
            );
        }
        return data;
    }


    public static void decryptFile(String filename, String keyHex) {
        try {
            // Read ciphertext
            byte[] encryptedBytes = Files.readAllBytes(Paths.get(filename));

            // Convert key
            byte[] keyBytes = hexToBytes(keyHex);

            // AES setup
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            // Decrypt
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Save plaintext
            Files.write(Paths.get("plaintext.txt"), decryptedBytes);

            // Output info
            System.out.println("File decrypted.");
            System.out.println("Decrypted data saved to plaintext.txt");

        } catch (Exception e) {
            System.out.println("Error decrypting file.");
            e.printStackTrace();
        }
    }

}