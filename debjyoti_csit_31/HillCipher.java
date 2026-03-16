import java.util.*;

public class HillCipher {

    static int[][] key = {
            {3, 3},
            {2, 5}
    };

    // Encrypt text
    public static String encrypt(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        if (text.length() % 2 != 0) {
            text += "X"; // padding
        }

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] p = {
                    text.charAt(i) - 'A',
                    text.charAt(i + 1) - 'A'
            };

            int c1 = (key[0][0] * p[0] + key[0][1] * p[1]) % 26;
            int c2 = (key[1][0] * p[0] + key[1][1] * p[1]) % 26;

            cipher.append((char) (c1 + 'A'));
            cipher.append((char) (c2 + 'A'));
        }

        return cipher.toString();
    }

    // Decrypt text
    public static String decrypt(String cipher) {
        int[][] inverseKey = inverseMatrix(key);

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {
            int[] c = {
                    cipher.charAt(i) - 'A',
                    cipher.charAt(i + 1) - 'A'
            };

            int p1 = (inverseKey[0][0] * c[0] + inverseKey[0][1] * c[1]) % 26;
            int p2 = (inverseKey[1][0] * c[0] + inverseKey[1][1] * c[1]) % 26;

            plain.append((char) ((p1 + 26) % 26 + 'A'));
            plain.append((char) ((p2 + 26) % 26 + 'A'));
        }

        return plain.toString();
    }

    // Find inverse of key matrix
    private static int[][] inverseMatrix(int[][] matrix) {
        int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        det = ((det % 26) + 26) % 26;

        int detInverse = modInverse(det, 26);

        int[][] inverse = new int[2][2];
        inverse[0][0] = matrix[1][1] * detInverse % 26;
        inverse[0][1] = -matrix[0][1] * detInverse % 26;
        inverse[1][0] = -matrix[1][0] * detInverse % 26;
        inverse[1][1] = matrix[0][0] * detInverse % 26;

        return inverse;
    }

    // Modular inverse
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        String cipherText = encrypt(plaintext);
        System.out.println("Encrypted text: " + cipherText);

        String decryptedText = decrypt(cipherText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}