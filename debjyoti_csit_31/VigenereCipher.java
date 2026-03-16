import java.util.Scanner;

public class VigenereCipher {

    // Encrypt plaintext using Vigenere cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                boolean isUpper = Character.isUpperCase(ch);
                char base = isUpper ? 'A' : 'a';

                int textChar = ch - base;
                int keyChar = key.charAt(keyIndex % key.length()) - 'a';

                char encryptedChar = (char) ((textChar + keyChar) % 26 + base);
                ciphertext.append(encryptedChar);

                keyIndex++;
            } else {
                // Keep non-alphabet characters unchanged
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    // Decrypt ciphertext using Vigenere cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);

            if (Character.isLetter(ch)) {
                boolean isUpper = Character.isUpperCase(ch);
                char base = isUpper ? 'A' : 'a';

                int cipherChar = ch - base;
                int keyChar = key.charAt(keyIndex % key.length()) - 'a';

                char decryptedChar = (char) ((cipherChar - keyChar + 26) % 26 + base);
                plaintext.append(decryptedChar);

                keyIndex++;
            } else {
                plaintext.append(ch);
            }
        }
        return plaintext.toString();
    }

    // Validate that the key is alphabetic
    private static boolean isValidKey(String key) {
        return key.matches("[a-zA-Z]+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter key (letters only): ");
        String key = scanner.nextLine();

        if (!isValidKey(key)) {
            System.out.println("Invalid key. Key must contain letters only.");
            return;
        }

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);

        scanner.close();
    }
}
