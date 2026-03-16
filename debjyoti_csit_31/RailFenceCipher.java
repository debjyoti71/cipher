import java.util.Scanner;

public class RailFenceCipher {

    public static String encrypt(String text, int key) {
        if (key == 1) return text;

        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++) rail[i] = new StringBuilder();

        int row = 0;
        boolean down = true;

        for (int i = 0; i < text.length(); i++) {
            rail[row].append(text.charAt(i));

            if (row == 0) down = true;
            else if (row == key - 1) down = false;

            row += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rail) result.append(sb);

        return result.toString();
    }

    public static String decrypt(String cipher, int key) {
        if (key == 1) return cipher;

        int len = cipher.length();
        char[][] rail = new char[key][len];

        boolean down = false;
        int row = 0;

        for (int col = 0; col < len; col++) {
            if (row == 0 || row == key - 1) down = !down;
            rail[row][col] = '*';
            row += down ? 1 : -1;
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (rail[i][j] == '*' && index < len) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;

        for (int col = 0; col < len; col++) {
            if (row == 0 || row == key - 1) down = !down;
            result.append(rail[row][col]);
            row += down ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter key (depth): ");
        int key = sc.nextInt();

        String encrypted = encrypt(message, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
