import java.util.HashSet;
import java.util.Set;

public class CaesarKey extends Substitution {

    // Constructor for CaesarKey that takes a string key
    public CaesarKey(String key) {
        super(); // Calls the parent class constructor (Substitution)

        // Check if the key is null or empty and throw an exception if it is
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty.");
        }

        // HashSet to keep track of characters that have already been added to avoid duplicates
        HashSet<Character> usedChars = new HashSet<>();
        // StringBuilder to construct the shifted alphabet based on the key
        StringBuilder shifterBuilder = new StringBuilder();

        // Process each character in the key
        for (char c : key.toCharArray()) {
            // Ensure that the character is within the allowed range
            if (c < Cipher.MIN_CHAR || c > Cipher.MAX_CHAR) {
                throw new IllegalArgumentException("Character out of range: " + c);
            }

            // Check for duplicate characters in the key
            if (usedChars.contains(c)) {
                throw new IllegalArgumentException("Duplicate character found in key: " + c);
            }

            // Add the character to the set of used characters and append it to the shifterBuilder
            usedChars.add(c);
            shifterBuilder.append(c);
        }

        // After processing the key, append the remaining characters from the allowed range that
        // were not already included in the key
        for (char c = (char) Cipher.MIN_CHAR; c <= Cipher.MAX_CHAR; c++) {
            if (!usedChars.contains(c)) {
                shifterBuilder.append(c);
            }
        }

        // Set the generated alphabet (key-based shifter) using the setShifter method
        setShifter(shifterBuilder.toString());
    }
}

