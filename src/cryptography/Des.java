/*
 * cryptography.Des.java, 13/09/2022
 * INU Champollion 2022-2023, L3 INFO
 * pas de copyright, aucun droits
 */
package cryptography;

import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * @author Lucàs VABRE
 */
public class Des {

    private static final int TAILLE_BLOC = 64;
    private static final int TAILLE_SOUS_BLOC = 32;
    private static final int NB_ROUND = 1;
    private static final int[] TAB_DECALAGE = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    private static final int[] PERM_INITIALE = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
    private static final int[][] S = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    private static final int[] E = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};

    private int[] masterKey = new int[64];
    private ArrayList<int[]> table_cles;

    public Des() {
        Random random = new Random();
        for (int i = 0; i < masterKey.length; i++) {
            this.masterKey[0] = random.nextInt(2);
        }
        this.table_cles = new ArrayList<>();
    }

    public static int[] stringToBits(String message) {
        char[] bitsInCharList = new BigInteger(message.getBytes())
                .toString(2)
                .toCharArray();
        int[] bits = new int[bitsInCharList.length];
        for (int i = 0; i < bitsInCharList.length; i++) {
            bits[i] = Integer.parseInt(String.valueOf(bitsInCharList[i]));
        }
        return bits;
    }

    public static int[] generePermutation(int taille) {
        int[] listePermut = new int[taille];
        ArrayList<Integer> listeIndice = new ArrayList<>();

        // Remplir le tableau d'indice
        for (int i = 0; i < taille ; i++) {
            listeIndice.add(i);
        }

        Random r = new Random();
        for (int i = taille; i > 0 ; i--){
            listePermut[taille- i] = listeIndice.remove(r.nextInt(0, i));
        }

        return listePermut;
    }

    public static String bitsToString(int[] blocs) {

        StringBuilder bit = new StringBuilder();
        for (int b : blocs) bit.append(b);

        return new String(new BigInteger(bit.toString(), 2).toByteArray());
    }

    public static void permutation(int[] tab_permutation, int[] bloc){
        int[] newTab = new int[bloc.length];

        for(int i = 0; i < bloc.length ; i++) {
            newTab[i] = bloc[tab_permutation[i]];
        }

        System.arraycopy(newTab, 0, bloc, 0, newTab.length);
    }

    public static void invPermuation(int[] tab_permutation, int[] bloc) {
        int[] newTab = new int[bloc.length];

        for(int i = 0; i < bloc.length ; i++) {
            newTab[tab_permutation[i]] = bloc[i];
        }

        System.arraycopy(newTab, 0, bloc, 0, newTab.length);
    }

    private static int[] crypte(String message_clair) {
//        int[] msg_crypte = message_clair.byt
        return null; // Todo Bouchon
    }


}

