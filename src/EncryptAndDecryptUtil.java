import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptAndDecryptUtil {

    public static ArrayList<String> encryptFile(String filename) {
        ArrayList<String> encryptedLines = new ArrayList<>();
        File inputFile = new File(filename);

        try {
            // Generate random key
            byte[] keyBytes = new byte[16];
            new java.util.Random().nextBytes(keyBytes);

            // Convert key to hex string
            String keyHex = toHex(keyBytes);

            // AES cipher using the random key
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Read file
            Scanner sc = new Scanner(inputFile);
            ArrayList<String> ciphertextOutput = new ArrayList<>();

            // Encrypt file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                byte[] encrypted = cipher.doFinal(line.getBytes());
                //Change encrypted bytes into hex
                String hexCipher = toHex(encrypted);

                //Add hex to return and the file
                encryptedLines.add(hexCipher);
                ciphertextOutput.add(hexCipher);
            }

            // Write ciphertext to file
            Files.write(Paths.get("ciphertext.txt"), ciphertextOutput);

            // Print key to screen
            System.out.println("File encrypted. Ciphertext saved to ciphertext.txt");
            System.out.println("HEX Key: " + keyHex);

        } catch (Exception e) {
            System.out.println("Error encrypting file.");
            e.printStackTrace();
        }

        return encryptedLines;
    }

    //byte array to a hex string
    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }


}