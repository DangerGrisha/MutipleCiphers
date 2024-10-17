import java.util.List;

public class MultiCipher extends Cipher {
    // List to hold multiple cipher objects
    private List<Cipher> ciphers;

    // Constructor for MultiCipher that takes a list of Cipher objects
    public MultiCipher(List<Cipher> ciphers) {
        // Ensure that the provided list of ciphers is not null
        if (ciphers == null) {
            throw new IllegalArgumentException("Cipher list cannot be null.");
        }
        // Store the list of ciphers
        this.ciphers = ciphers;
    }

    @Override
    public String encrypt(String plaintext) {
        String result = plaintext;
        // Pass the plaintext through each cipher's encryption in sequence
        for (Cipher cipher : ciphers) {
            result = cipher.encrypt(result); // Each cipher encrypts the result of the previous one
        }
        return result; // Return the final encrypted result
    }

    @Override
    public String decrypt(String ciphertext) {
        String result = ciphertext;
        // Pass the ciphertext through each cipher's decryption in reverse order
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            result = ciphers.get(i).decrypt(result); // Decrypt in reverse order
        }
        return result; // Return the final decrypted result
    }
}

