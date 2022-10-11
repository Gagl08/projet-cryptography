package cryptography.test;

import cryptography.Des;

import java.util.Arrays;

public class testDes {

    public static void testStringToBits() {
        System.out.println(Arrays.toString(Des.stringToBits("13")));
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

    public static void testDecoupage() {
        System.out.println(Arrays.deepToString(Des.decoupage(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 4)));
    }

    public static void testRecollage() {
        int[][] decoupage = Des.decoupage(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 4);

        System.out.println(Arrays.deepToString(decoupage));
        assert decoupage != null;
        System.out.println(Arrays.toString(Des.recollageBloc(decoupage)));
    }

    public static void testDecaleGauche() {
        System.out.println(Arrays.toString(Des.decaleGauche(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 4)));
    }

    public static void testXor() {
        System.out.println(Arrays.toString(Des.xor(new int[]{1, 1, 0, 0}, new int[]{1, 0, 1, 0})));
    }

    public static void testGenereCle() {
        Des a = new Des();
        a.genereCle(1);
        System.out.println(Arrays.toString(a.table_cles.get(0)));
    }

    public static void testFonctionS() {
        System.out.println(Arrays.toString(Des.fonction_S(new int[]{1, 1, 1, 1, 1, 1})));
    }
    public static void testFonctionF() {
        Des des = new Des();

        // System.out.println(Arrays.toString(des.fonction_F()));
    }

    private static void testCrypte() {
        Des des = new Des();

        System.out.println(Arrays.toString(des.crypte("Hello World!")));
    }

    public static void main(String[] args) {
        // testStringToBits();
        // testBitsToString();
        // testGenerePermutation();
        // testPermuation();
        // testInvPermuation();
        // testDecoupage();
        // testRecollage();
        // testDecaleGauche();
        // testXor();
        // testGenereCle();
        // testFonctionS();
        // testFonctionF();
        testCrypte();
        // testDecrypte();
    }

}
