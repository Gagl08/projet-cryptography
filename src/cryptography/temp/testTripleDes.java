package cryptography.temp;

import cryptography.Des;
import cryptography.TripleDes;

public class testTripleDes {

    public static void main(String[] args) {
        TripleDes tripleDes = new TripleDes();

        String message = "Hello";

        int[] messageCrypte = tripleDes.crypte(message);
        String messageDecrypte = tripleDes.decrypte(messageCrypte);

        System.out.printf("\"%s\" -> \"%s\" -> \"%s\"", message, Des.bitsToString(messageCrypte), messageDecrypte);
    }
    
}
