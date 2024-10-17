import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assume.assumeTrue;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'G' Spec Example")
    public void subAGTest() {
        // Remember that you can change MIN_CHAR AND MAX_CHAR
        // in Cipher.java to make testing easier! For this
        // example test, we are using MIN_CHAR = A and MAX_CHAR = G

        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('G'));

        Cipher testSubstitution = new Substitution("GCBEAFD");
        assertEquals("FGE", testSubstitution.encrypt("FAD"));
        assertEquals("BAD", testSubstitution.decrypt("CGE"));

        // Per the spec, we should throw an IllegalArgumentException if
        // the length of the shifter doesn't match the number of characters
        // within our Cipher's encodable range
        assertThrows(IllegalArgumentException.class, () -> {
            new Substitution("GCB");
        });
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - 'A'-'Z' Shifter")
    public void subAZTest() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // Reverse alphabetic
        Cipher testSubstitution = new Substitution(
                "ZYXWVUTSRQPONMLKJIHGFEDCBA");
        assertEquals("UZW", testSubstitution.encrypt("FAD"));
        assertEquals("BAD", testSubstitution.decrypt("YZW"));
    }

    @Test
    @DisplayName("EXAMPLE TEST CASE - ' '-'}' Shifter")
    public void subComplexTest() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) (' ') && Cipher.MAX_CHAR == (int) ('}'));

        // Swapping lowercase a<->b
        Cipher testSubstitution = new Substitution(
                " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`" +
                        "bacdefghijklmnopqrstuvwxyz{|}");
        assertEquals("FAD", testSubstitution.encrypt("FAD"));
        assertEquals("fbd", testSubstitution.encrypt("fad"));
        assertEquals("BAD", testSubstitution.decrypt("BAD"));
        assertEquals("bad", testSubstitution.decrypt("abd"));
    }

    @Test
    @DisplayName("TODO: CaesarKey - 'A'-'Z'")
    public void keyAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // Create a CaesarKey cipher with the key "TIN"
        Cipher caesarKeyCipher = new CaesarKey("TIN");

        // Encrypt the message "HELLO"
        String encrypted = caesarKeyCipher.encrypt("HELLO");
        System.out.println("Encrypted 'HELLO' with key 'TIN': " + encrypted);

        // Check if the encrypted message is accurate
        assertEquals("EBJJM", encrypted); // Replace with correct value

        // Decrypt the encrypted message
        String decrypted = caesarKeyCipher.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);

        // Check if the decrypted message is "HELLO"
        assertEquals("HELLO", decrypted);
    }

    @Test
    @DisplayName("TODO: CaesarShift - 'A'-'Z' Shifter")
    public void shiftAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // Create a CaesarShift cipher with a shift of 6
        Cipher caesarShiftCipher = new CaesarShift(6);

        // Encrypt the message "HELLO"
        String encrypted = caesarShiftCipher.encrypt("HELLO");
        // Check if the encrypted message is accurate (expected output depends on shift
        // logic)
        assertEquals("NKRRU", encrypted);

        // Decrypt the encrypted message
        String decrypted = caesarShiftCipher.decrypt(encrypted);
        // Check if the decrypted message is "HELLO"
        assertEquals("HELLO", decrypted);
    }

    @Test
    @DisplayName("TODO: MultiCipher - 'A'-'Z' Shifter")
    public void multiAZOne() {
        // Skip this test if the constants have changed
        assumeTrue(Cipher.MIN_CHAR == (int) ('A') && Cipher.MAX_CHAR == (int) ('Z'));

        // Create a MultiCipher with ciphers CaesarKey("TIN") and CaesarShift(6)
        MultiCipher multiCipher = new MultiCipher(
                Arrays.asList(new CaesarKey("TIN"), new CaesarShift(6)));

        // Encrypt the message "HELLO"
        String encrypted = multiCipher.encrypt("HELLO");
        // Check if the encrypted message is accurate (expected output depends on how
        // both ciphers work together)
        assertEquals("KHPPS", encrypted);

        // Decrypt the encrypted message
        String decrypted = multiCipher.decrypt(encrypted);
        // Check if the decrypted message is "HELLO"
        assertEquals("HELLO", decrypted);
    }
}

