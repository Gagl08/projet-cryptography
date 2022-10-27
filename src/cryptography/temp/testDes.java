package cryptography.temp;

import cryptography.Des;

import java.util.Arrays;

public class testDes {

    /**
     * @deprecated
     */
    public static void testStringToBits() {
        System.out.println(Arrays.toString(Des.stringToBits("Bonjour")));
    }

    /**
     * @deprecated
     */
    public static void testBitsToString() {
        System.out.println(Des.bitsToString(Des.stringToBits("Bonjour")));
    }

    public static void testGenerePermutation() {
        System.out.println(Arrays.toString(Des.generePermutation(64)));
    }

    public static void testPermuation() {
        int[] permutation = Des.generePermutation(64);
        int[] bloc = new int[permutation.length];

        for (int i = 0; i < bloc.length; i++) {
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
        for (int i = 0; i < bloc.length; i++) {
            bloc[i] = i;
            bloc2[i] = i;
        }

        System.out.println("Bloc avant permuation: ");
        System.out.println(Arrays.toString(bloc));

        Des.permutation(permutation, bloc);
        System.out.println("Bloc apres permuation: ");
        System.out.println(Arrays.toString(bloc));

        Des.invPermutation(permutation, bloc);
        System.out.println("Bloc après Inv-permuation: ");
        System.out.println(Arrays.toString(bloc));

        System.out.println(Arrays.equals(bloc, bloc2));
    }

    public static void testDecoupage() {
        System.out.println(Arrays.deepToString(Des.decoupage(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 7)));
    }

    public static void testRecollage() {
        int[][] decoupage = Des.decoupage(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 7);

        System.out.println(Arrays.deepToString(decoupage));
        assert decoupage != null;
        System.out.println(Arrays.toString(Des.recollageBloc(decoupage)));
    }

    public static void testDecaleGauche() {
        System.out.println(Arrays.toString(Des.decaleGauche(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 4)));
    }

    /**
     * @deprecated
     */
    public static void testXor() {
        System.out.println(Arrays.toString(Des.xor(new int[]{1, 1, 0, 0}, new int[]{1, 0, 1, 0})));
    }

    public static void testGenereCle() {
        Des a = new Des();
        a.genereCle(1);
        System.out.println(Arrays.toString(a.table_cles.get(0)));
    }

    public static void testFonctionS() {
//        System.out.println(Arrays.toString(Des.fonction_S(new int[]{1, 1, 1, 1, 1, 1})));
    }

    public static void testFonctionF() {
        Des des = new Des();

//         System.out.println(Arrays.toString(des.fonction_F()));
    }

    private static void testCrypte() {
        Des des = new Des();
        int[] msg = des.crypte("Bonjour");
        System.out.println("en bit : " + Arrays.toString(msg));
        System.out.println("en string :" + Des.bitsToString(msg));
    }

    private static void testDecrypte() {
        Des des = new Des();
        int[] msg = des.crypte("Bonjour, je suis super heureux de vous voir");
        System.out.println("Message crypté : " + Des.bitsToString(msg));
        System.out.println(des.decrypte(msg));
    }

    public static void main(String[] args) {
//         testStringToBits(); //Il marche
//         testBitsToString();//Il marche
//         testGenerePermutation();//Il marche
//         testPermuation();//Il marche
//         testInvPermuation();//Il marche
//         testDecoupage();//Il marche (même avec un nombre de bloc qui divise pas la taille du bloc de base)
//         testRecollage();//Il marche
//         testDecaleGauche();//Il marche
//         testXor();//Il marche
//         testGenereCle();// Aucune idée, ça créé une clé random mais je sais pas si c'est comme ça
//         testFonctionS();// corrigé
//         testFonctionF(); // il a l'air bon
//         testCrypte(); //Il a l'air bon (si on regarde decoupe[0]et [1] à chaque itérations on voit que c'est cohérent)
        testDecrypte(); // Un truc est normal c'est que jusqu'à la deniere itération sur les 16 ,
        // les clés sont randoms jusqu'à la dernière itération ou elle est tout le temps la meme
    }

}
