package cryptography.test;

import cryptography.Des;

import java.util.Arrays;

public class testDes {

    public static void testStringToBits() {
        System.out.println(Arrays.toString(Des.stringToBits("Bonjour")));
    }

    public static void testBitsToString() {

        System.out.println(Des.bitsToString(Des.stringToBits("Bonjour")));
    }

    public static void main(String[] args) {
        testStringToBits();
        testBitsToString();
    }
}
