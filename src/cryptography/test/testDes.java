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

    public static void testGenerePermutation(){
        System.out.println(Arrays.toString(Des.generePermutation(64)));
    }

    public static void testPermuation() {
        int[] permutation = Des.generePermutation(64);
        int[] bloc = new int[permutation.length];

        for(int i = 0; i < bloc.length; i++){
            bloc[i] = i;
        }
        System.out.println(Arrays.toString(bloc));
        Des.permutation(permutation, bloc);
        System.out.println(Arrays.toString(bloc));
    }

    public static void testInvPermuation() {
        int[] permutation = Des.generePermutation(64);
        int[] bloc = new int[permutation.length];
        int[] bloc2 = new int[permutation.length];
        for(int i = 0; i < bloc.length; i++) {
            bloc[i] = i;
            bloc2[i] = i;
        }

        System.out.println("Bloc avant permuation: ");
        System.out.println(Arrays.toString(bloc));

        Des.permutation(permutation, bloc);
        System.out.println("Bloc apres permuation: ");
        System.out.println(Arrays.toString(bloc));

        Des.invPermuation(permutation, bloc);
        System.out.println("Bloc après Inv-permuation: ");
        System.out.println(Arrays.toString(bloc));

        System.out.println(Arrays.equals(bloc, bloc2));
    }

    public static void main(String[] args) {
        // testStringToBits();
        // testBitsToString();
        // testGenerePermutation();
        // testPermuation();
        testInvPermuation();
    }
}
