import java.util.Scanner;

class PlayfairCipher_20 {

    static char[][] playfairMatrix = new char[5][5];
    static int[][] letterPosition = new int[26][2];

    // STEP 1: Create Playfair Matrix from Key
    static void createMatrix(String key) {

        boolean[] used = new boolean[26];
        key = key.replace("J", "I");

        int row = 0, col = 0;

        // Place key characters
        for (char ch : key.toCharArray()) {
            if (!used[ch - 'A']) {
                playfairMatrix[row][col] = ch;
                letterPosition[ch - 'A'][0] = row;
                letterPosition[ch - 'A'][1] = col;
                used[ch - 'A'] = true;

                col++;
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }

        // Place remaining alphabets
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch != 'J' && !used[ch - 'A']) {
                playfairMatrix[row][col] = ch;
                letterPosition[ch - 'A'][0] = row;
                letterPosition[ch - 'A'][1] = col;
                used[ch - 'A'] = true;

                col++;
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
    }

    // STEP 2: Prepare Plain Text
    static String prepareText(String text) {

        text = text.replace("J", "I");
        StringBuilder prepared = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            prepared.append(text.charAt(i));

            if (i + 1 < text.length() &&
                text.charAt(i) == text.charAt(i + 1)) {
                prepared.append('X');
            }
        }

        if (prepared.length() % 2 != 0) {
            prepared.append('X');
        }

        return prepared.toString();
    }

    // STEP 3: Encrypt or Decrypt
    static String processText(String text, int direction) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            char first = text.charAt(i);
            char second = text.charAt(i + 1);

            int r1 = letterPosition[first - 'A'][0];
            int c1 = letterPosition[first - 'A'][1];
            int r2 = letterPosition[second - 'A'][0];
            int c2 = letterPosition[second - 'A'][1];

            // Same row
            if (r1 == r2) {
                result.append(playfairMatrix[r1][(c1 + direction + 5) % 5]);
                result.append(playfairMatrix[r2][(c2 + direction + 5) % 5]);
            }
            // Same column
            else if (c1 == c2) {
                result.append(playfairMatrix[(r1 + direction + 5) % 5][c1]);
                result.append(playfairMatrix[(r2 + direction + 5) % 5][c2]);
            }
            // Rectangle rule
            else {
                result.append(playfairMatrix[r1][c2]);
                result.append(playfairMatrix[r2][c1]);
            }
        }

        return result.toString();
    }

    // STEP 4: Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String key = "MONARCHY";

        System.out.print("Enter Plain Text: ");
        String plainText =
                sc.nextLine().toUpperCase().replaceAll("\\s", "");

        createMatrix(key);

        String preparedText = prepareText(plainText);
        String cipherText = processText(preparedText, 1);
        String decryptedText = processText(cipherText, -1);

        System.out.println("\nKey           : " + key);
        System.out.println("Plain Text    : " + plainText);
        System.out.println("Cipher Text   : " + cipherText);
        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }
}
