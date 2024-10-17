public class Substitution extends Cipher {

    private String shifter; // This stores the character mappings for the substitution

    // No-arg constructor (starts with no shifter set)
    public Substitution() {
        this.shifter = null;
    }

    // Constructor that takes a shifter string
    public Substitution(String shifter) {
        setShifter(shifter); // Set the shifter using the provided string
    }

    // Method to set the shifter
    public void setShifter(String shifter) {
        // Check if the shifter's length matches the number of encodable characters
        if (shifter.length() != Cipher.TOTAL_CHARS) {
            throw new IllegalArgumentException("Shifter length must match the encodable range.");
        }

        // Ensure there are no duplicate characters and each is in the valid range
        boolean[] seen = new boolean[Cipher.TOTAL_CHARS]; // Tracks seen characters

        for (int i = 0; i < shifter.length(); i++) {
            char c = shifter.charAt(i);
            // Ensure the character is within the valid range
            if (c < Cipher.MIN_CHAR || c > Cipher.MAX_CHAR) {
                throw new IllegalArgumentException("Shifter contains characters outside the valid range.");
            }
            // Ensure there are no duplicates
            if (seen[c - Cipher.MIN_CHAR]) {
                throw new IllegalArgumentException("Shifter contains duplicate characters.");
            }
            seen[c - Cipher.MIN_CHAR] = true;
        }

        this.shifter = shifter; // Assign the valid shifter string
    }

    // Encrypt method
    @Override
    public String encrypt(String plaintext) {
        // If the shifter hasn't been set, throw an exception
        if (shifter == null) {
            throw new IllegalStateException("Shifter is not set.");
        }

        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            // Check if the character is within the encodable range
            if (c >= Cipher.MIN_CHAR && c <= Cipher.MAX_CHAR) {
                // Find the character's position in the range and map to the shifter
                encrypted.append(shifter.charAt(c - Cipher.MIN_CHAR));
            } else {
                encrypted.append(c); // If outside the range, keep it as is
            }
        }
        return encrypted.toString();
    }

    // Decrypt method
    @Override
    public String decrypt(String ciphertext) {
        // If the shifter hasn't been set, throw an exception
        if (shifter == null) {
            throw new IllegalStateException("Shifter is not set.");
        }

        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            // Check if the character is within the encodable range
            if (c >= Cipher.MIN_CHAR && c <= Cipher.MAX_CHAR) {
                // Find the character's position in the shifter and map back to the original character
                int index = shifter.indexOf(c);
                if (index == -1) {
                    throw new IllegalArgumentException("Character not found in shifter.");
                }
                decrypted.append((char) (index + Cipher.MIN_CHAR));
            } else {
                decrypted.append(c); // If outside the range, keep it as is
            }
        }
        return decrypted.toString();
    }
}

