package cryptography;

public class TripleDes {

    private static final int NB_DES = 3;
    private final Des[] listeDes = new Des[NB_DES];

    public TripleDes() {
        for (int i = 0; i < NB_DES; i++) {
            listeDes[i] = new Des();
        }
    }

    public TripleDes(int[][] masterKeys) {
        if (masterKeys.length != NB_DES)
            throw new IllegalArgumentException("masterKeys parameter should have 3 elements");
        for (int[] masterKey : masterKeys) {
            if (masterKey.length != Des.TAILLE_BLOC)
                throw new IllegalArgumentException("Each elements in masterKeys should have 64 bits");
        }

        for (int i = 0; i < NB_DES; i++) {
            listeDes[i] = new Des(masterKeys[i]);
        }
    }

    public int[] crypte(String messageClair) {
        String resultat = messageClair;

        for (Des des : listeDes) {
            resultat = Des.bitsToString(des.crypte(resultat));
        }
        return Des.stringToBits(resultat);
    }

    public String decrypte(int[] messageCrypte) {
        int[] resultat = messageCrypte;

        // Il faut décripter dans le sens inverse, donc de l'index le plus grand vers le plus petit
        for (int i = listeDes.length - 1; i >= 0; i--) {
            resultat = Des.stringToBits(listeDes[i].decrypte(resultat));
        }
        return Des.bitsToString(resultat);
    }
}
