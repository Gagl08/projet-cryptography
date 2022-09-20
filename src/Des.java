/*
 * Des.java, 13/09/2022
 * INU Champollion 2022-2023, L3 INFO
 * pas de copyright, aucun droits
 */

/**
 * @author Lucàs VABRE
 */
public class Des {

    final private static int TAILLE_BLOC = 64;
    final private static int TAILLE_SOUS_BLOC = 32;
    final private static int NB_RONDE = 1;
    final private static int[] TAB_DECALAGE = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    final private static int[] PERM_INITIALE = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17,  9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    final private static int[][] S = {
            {14,  4, 13, 1,  2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15,  7, 4, 14,  2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8, 2, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 50, 3, 15, 12, 8, 2, 4, 9, 175, 11, 3, 14, 10, 0, 6,133
    };
    final private static int[] E = {
            32,  1,  2,  3,  4,  5,
             4,  5,  6,  7,  8,  9,
             8,  9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 21, 18, 19, 20,
            21, 20, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };


    private int[] masterKey;
    private int[] tabCles;


}
