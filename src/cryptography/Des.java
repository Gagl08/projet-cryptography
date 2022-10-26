/*
 * cryptography.Des.java, 13/09/2022
 * INU Champollion 2022-2023, L3 INFO
 * pas de copyright, aucun droits
 */
package cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * @author Lucàs VABRE et les 2 autres
 */
public class Des {

    private static final int TAILLE_BLOC = 64;
    private static final int TAILLE_SOUS_BLOC = 32;
    private static final int NB_ROUND = 1;
    private static final int[] TAB_DECALAGE = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    private static final int[] PERM_INITIALE = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

 /*   private static final int[] INV_PERM_INITIALE = {
            40,8,48,16,56,24,64,32,
            39,7,47,15,55,23,63,31,
            38,6,46,14,54,22,62,30,
            37,5,45,13,53,21,61,29,
            36,4,44,12,52,20,60,28,
            35,3,43,11,51,19,59,27,
            34,2,42,10,50,18,58,26,
            33,1,41,9,49,17,57,25
    };
*/

    private static final int[][] S = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };
    private static final int[] E = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1,
    };

    private final int[] masterKey = new int[64];
    public ArrayList<int[]> table_cles;

    public Des() {
        Random random = new Random();
        for (int i = 0; i < masterKey.length; i++) {
            this.masterKey[i] = random.nextInt(2);
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
        for (int i = 0; i < taille; i++) {
            listeIndice.add(i);
        }

        Random r = new Random();
        for (int i = taille; i > 0; i--) {
            listePermut[taille - i] = listeIndice.remove(r.nextInt(i));
        }

        return listePermut;
    }

    public static String bitsToString(int[] blocs) {

        StringBuilder bit = new StringBuilder();
        for (int b : blocs) bit.append(b);

        return new String(new BigInteger(bit.toString(), 2).toByteArray());
    }

    public static void permutation(int[] tab_permutation, int[] bloc) {
        int[] newTab = new int[bloc.length];
        for (int i = 0; i < bloc.length; i++) {

            newTab[i] = bloc[tab_permutation[i] % tab_permutation.length];
        }

        System.arraycopy(newTab, 0, bloc, 0, newTab.length);
    }

    public static void invPermutation(int[] tab_permutation, int[] bloc) {
        int[] newTab = new int[bloc.length];

        //// System.out.println(bloc.length);
        for (int i = 0; i < bloc.length; i++) {
            //// System.out.println(tab_permutation[i] % tab_permutation.length);

            newTab[tab_permutation[i] % tab_permutation.length] = bloc[i];
        }

        System.arraycopy(newTab, 0, bloc, 0, newTab.length);
    }

    public static int[][] decoupage(int[] bloc, int nbBlocs) {
        int surplu = bloc.length % nbBlocs;
        int z = (bloc.length) / nbBlocs;
        if (surplu != 0) {
            z = (bloc.length + (nbBlocs - surplu)) / nbBlocs;
        }

        int[][] newTab = new int[nbBlocs][z];

        // Remplis le tableau de 0
        for (int[] b : newTab) Arrays.fill(b, 0);

        int y = 0;
        for (int i = 0; i < bloc.length; i++) {
            if (i % z == 0 && i > 0) {
                y++;
            }
            newTab[y][i - y * z] = bloc[i];
        }
        return newTab;
    }

    public static int[] recollageBloc(int[][] blocs) {

        int[] bloc = new int[blocs.length * blocs[0].length];
        int y = 0;
        for (int[] ints : blocs) {
            for (int anInt : ints) {
                bloc[y] = anInt;
                y++;
            }
        }

        return bloc;
    }

    public static int[] decaleGauche(int[] blocs, int nbCran) {
        int[] newBloc = new int[blocs.length];

        for (int i = 0; i < blocs.length; i++) {
            int y = (i + nbCran) % blocs.length;
            newBloc[i] = blocs[y];
        }

        return newBloc;
    }

    public static int[] xor(int[] tab1, int[] tab2) {
        int[] resultat = new int[tab1.length];
        // System.out.println(resultat.length);

        for (int i = 0; i < resultat.length; i++) {
            resultat[i] = (tab1[i] + tab2[i]) % 2;
        }

        return resultat;
    }

    public void genereCle(int n) {
        int[] newCle = new int[56];
        int[] lastCle = new int[48];
        int[] permInit = generePermutation(newCle.length);
        permutation(permInit, newCle);

        System.arraycopy(this.masterKey, 0, newCle, 0, newCle.length);

        int[][] cleDecoupe = decoupage(newCle, 2);
        assert cleDecoupe != null;
        cleDecoupe[0] = decaleGauche(cleDecoupe[0], TAB_DECALAGE[n]);
        cleDecoupe[1] = decaleGauche(cleDecoupe[1], TAB_DECALAGE[n]);

        newCle = recollageBloc(cleDecoupe);

        int[] lastPerm = generePermutation(lastCle.length);
        System.arraycopy(newCle, 0, lastCle, 0, lastCle.length);
        permutation(lastPerm, lastCle);
        this.table_cles.add(lastCle);
    }

    public static int[] fonction_S(int[] tab) {

        String l = "" + tab[0] + tab[5];
        int ligne = Integer.parseInt(l, 2);
        String c = "" + tab[1] + tab[2] + tab[3] + tab[4];
        int colonne = Integer.parseInt(c, 2);

        // chaque blocs de 6 bits on fait le truc avec bit 1 et bit 6 et l'autre truc pour avoir ligne et colonne de S
        String coordonneeStr = Integer.toString(S[ligne][colonne], 2);
        int coordonneeInt = Integer.parseInt(coordonneeStr);

        int[] resultat = new int[4];
        for (int i = 0; i < resultat.length; i++, coordonneeInt /= 10) {
            resultat[resultat.length - i - 1] = coordonneeInt % 10;
        }

        return resultat;
    }

    public int[] fonction_F(int indice_cle, int[] Dn) {
        //xor entre this.E et la cle trouvé à cette ronde
        int[] cle = this.table_cles.get(indice_cle);
        int[] d_prime_n = new int[48];
        for (int i = 0; i < E.length; i++) {
            d_prime_n[i] = Dn[E[i] % Dn.length];
        }
        int[] D_etoile_n = xor(cle, d_prime_n);

        //découpage en 8 blocs de 6 bits
        int[][] decoupe = decoupage(D_etoile_n, 8);
        int[][] bloc = new int[8][4];

        for (int i = 0; i < decoupe.length; i++) {
            int[] tab = fonction_S(decoupe[i]);
            System.arraycopy(tab, 0, bloc[i], 0, tab.length);
        }
        return recollageBloc(bloc);
    }

    public int[] crypte(String message_clair) {

        int[] msg_bit = stringToBits(message_clair);
        int taille = (int) Math.ceil(msg_bit.length / (float) TAILLE_BLOC) * 64;

        int[] msgBit = new int[taille];
        Arrays.fill(msgBit, 0);

        // boucle du XOR
        for (int y = 0; y < msg_bit.length; y++) {
            msgBit[y] = (msgBit[y] + msg_bit[y]) % 2;
        }

        int[][] decoupe = decoupage(msgBit, (int) Math.ceil(msg_bit.length / (float) TAILLE_BLOC));


        int[][] msg_crypte_bit = new int[(int) Math.ceil(msg_bit.length / (float) TAILLE_BLOC)][TAILLE_BLOC];


        //Boucle de génération 16 des clés
        for (int n = 0; n < (TAILLE_BLOC / 4); n++) {
            this.genereCle(n);
        }

        for (int i = 0; i < decoupe.length; i++) {
            permutation(PERM_INITIALE, decoupe[i]);

            int[][] decoupe2 = decoupage(decoupe[i], 2);


            for (int n = 0; n < 16; n++) {
                int[] Gn1 = new int[decoupe2[1].length];
                System.arraycopy(decoupe2[1], 0, Gn1, 0, Gn1.length);
                int[] F = fonction_F(n, decoupe2[1]);
                decoupe2[1] = xor(decoupe2[0], F);
                decoupe2[0] = Gn1;
            }

            msg_crypte_bit[i] = recollageBloc(decoupe2);
            invPermutation(PERM_INITIALE, msg_crypte_bit[i]);
        }
        return recollageBloc(msg_crypte_bit);
    }

    public String decrypte(int[] messageCode) {


        int[][] decoupe = decoupage(messageCode, (int) (messageCode.length / TAILLE_BLOC));

        for (int i = 0; i < decoupe.length; i++) {
            permutation(PERM_INITIALE, decoupe[i]);
            int[][] bloc32 = decoupage(decoupe[i], 2);

            for (int n = 15; n >= 0; n--) {
                int[] dn1 = new int[bloc32[0].length];
                System.out.println("clé de chiffrement utilisé :" + Arrays.toString(this.table_cles.get(n)));
                System.arraycopy(bloc32[0], 0, dn1, 0, dn1.length);
                bloc32[0] = xor(bloc32[1], fonction_F(n, dn1));
                bloc32[1] = dn1;
                System.out.println("bloc32[0] :" + Arrays.toString(bloc32[0]));
                System.out.println("bloc32[1] :" + Arrays.toString(bloc32[1]));

            }
            decoupe[i] = recollageBloc(bloc32);
            invPermutation(PERM_INITIALE, decoupe[i]);

        }
        int[] message_decrypte = recollageBloc(decoupe);
        return bitsToString(message_decrypte);
    }
}

