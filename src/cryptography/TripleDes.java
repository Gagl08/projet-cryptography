package cryptography;

public class TripleDes {

    private final Des[] listeDes;

    public TripleDes() {
        this.listeDes = new Des[]{new Des(), new Des(), new Des(),};
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
