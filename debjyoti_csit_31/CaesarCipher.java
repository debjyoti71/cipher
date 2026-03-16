class CaesarCipher
{
    public static void main(String[] args)
    {
        String plaintext = "NETWORKING";
        String ciphertext = "";
        String decryptedtext = "";
        int key = 3;

        // Encryption
        for (int i = 0; i < plaintext.length(); i++)
        {
            char ch = plaintext.charAt(i);
            char encryptedChar = (char) (ch + key);
            ciphertext = ciphertext + encryptedChar;
        }

        // Decryption
        for (int i = 0; i < ciphertext.length(); i++)
        {
            char ch = ciphertext.charAt(i);
            char decryptedChar = (char) (ch - key);
            decryptedtext = decryptedtext + decryptedChar;
        }

        // Output
        System.out.println("Plaintext  : " + plaintext);
        System.out.println("Ciphertext : " + ciphertext);
        System.out.println("Decrypted Text : " + decryptedtext);
    }
}
