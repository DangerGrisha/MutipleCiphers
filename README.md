Substitution Cipher in Java
This project is a Java implementation of a Substitution Cipher, a classic encryption technique. It includes both encryption and decryption functionalities using a custom shifter string. The project also features a Caesar Shift, which extends the Substitution Cipher to allow for shifting characters based on a given value.

Features
Custom Shifter: Encrypt and decrypt text using a unique shifter string that maps input characters to different output characters.
Caesar Shift: Shift characters by a specific number of positions within the encodable range.
Handles invalid input and includes robust error handling.
How It Works
The Substitution Cipher replaces each character in the plaintext with another character from a shifter string, while the Caesar Shift allows shifting each character within a predefined range.

For example, using the shifter:

Encodable Range: A B C D E F G
Shifter String:  G C B E A F D
Encrypting "FAD" results in "FGE".
Decrypting "CGE" results in "BAD".

Classes
Substitution: Handles encryption and decryption using a shifter string.
CaesarShift: Extends Substitution by adding a Caesar cipher mechanism.


**Usage**

Encryption:
Substitution cipher = new Substitution("GCBEAFD");
String encrypted = cipher.encrypt("FAD");

Decryption:
Substitution cipher = new Substitution("GCBEAFD");
String decrypted = cipher.decrypt("CGE");

Caesar Shift:
CaesarShift caesar = new CaesarShift(3);
String shifted = caesar.encrypt("FAD");
