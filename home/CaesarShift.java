import java.util.HashSet;

public class CaesarShift extends Substitution {

    // Constructor for CaesarShift that accepts a shift value
    public CaesarShift(int shift) {
        super(); // Calls the parent class constructor (Substitution)

        // If the shift value is less than or equal to 0, throw an exception to ensure a valid shift
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be greater than 0.");
        }

        StringBuilder shifterBuilder = new StringBuilder(); // StringBuilder to create the shifted alphabet

        // Calculate the range of characters based on Cipher.MIN_CHAR and Cipher.MAX_CHAR.
        // This represents the total number of encodable characters.
        int range = Cipher.MAX_CHAR - Cipher.MIN_CHAR + 1;

        // Loop through the character range, shifting each character by the specified amount
        for (int i = 0; i < range; i++) {
            // Calculate the shifted character using modular arithmetic to wrap around the alphabet
            char shiftedChar = (char) (Cipher.MIN_CHAR + (i + shift) % range);
            shifterBuilder.append(shiftedChar); // Append the shifted character to the builder
        }

        // Pass the generated shifted alphabet (shifterBuilder) to the parent class via setShifter method
        setShifter(shifterBuilder.toString());
    }
}

