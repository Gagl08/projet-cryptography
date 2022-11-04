/*
 * DesTest.java, 26/10/2022
 * INU Champollion 2022-2023, L3 INFO
 * pas de copyright, aucun droits
 */
package cryptography;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gaël BURGUÈS
 * @author Laurian DUFRECHOU
 * @author Lucàs VABRE
 */
class DesTest {

    private final ArrayList<Des> FIXTURE = new ArrayList<>();

    @BeforeEach
    public void init() {
        int[][] MASTER_KEYS = {{
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
        }, {
                1, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 1,
        }, {
                1, 0, 1, 0, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1,
                1, 0, 1, 0, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1,
        }, {
                1, 1, 0, 1, 1, 1, 0, 1,
                0, 0, 0, 1, 0, 0, 0, 1,
                0, 0, 0, 1, 1, 0, 1, 1,
                1, 1, 1, 0, 1, 0, 1, 1,
                1, 0, 0, 1, 1, 1, 1, 1,
                1, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0, 1, 1,
                0, 0, 1, 1, 1, 0, 1, 1,
        }};

        for (int[] masterKey : MASTER_KEYS) FIXTURE.add(new Des(masterKey));
    }

    @Test
    void stringToBits() {
        // Chaine vide
        assertArrayEquals(new int[]{}, Des.stringToBits(""));

        // Minuscules
        assertArrayEquals(new int[]{
                0, 1, 1, 0, 0, 0, 0, 1, // a
                0, 1, 1, 0, 0, 0, 1, 0, // b
                0, 1, 1, 0, 0, 0, 1, 1, // c
                0, 1, 1, 0, 0, 1, 0, 0, // d
                0, 1, 1, 0, 0, 1, 0, 1, // e
                0, 1, 1, 0, 0, 1, 1, 0, // f
                0, 1, 1, 0, 0, 1, 1, 1, // g
                0, 1, 1, 0, 1, 0, 0, 0, // h
                0, 1, 1, 0, 1, 0, 0, 1, // i
                0, 1, 1, 0, 1, 0, 1, 0, // j
                0, 1, 1, 0, 1, 0, 1, 1, // k
                0, 1, 1, 0, 1, 1, 0, 0, // l
                0, 1, 1, 0, 1, 1, 0, 1, // m
                0, 1, 1, 0, 1, 1, 1, 0, // n
                0, 1, 1, 0, 1, 1, 1, 1, // o
                0, 1, 1, 1, 0, 0, 0, 0, // p
                0, 1, 1, 1, 0, 0, 0, 1, // q
                0, 1, 1, 1, 0, 0, 1, 0, // r
                0, 1, 1, 1, 0, 0, 1, 1, // s
                0, 1, 1, 1, 0, 1, 0, 0, // t
                0, 1, 1, 1, 0, 1, 0, 1, // u
                0, 1, 1, 1, 0, 1, 1, 0, // v
                0, 1, 1, 1, 0, 1, 1, 1, // w
                0, 1, 1, 1, 1, 0, 0, 0, // x
                0, 1, 1, 1, 1, 0, 0, 1, // y
                0, 1, 1, 1, 1, 0, 1, 0, // z
        }, Des.stringToBits("abcdefghijklmnopqrstuvwxyz"));

        // Majuscules
        assertArrayEquals(new int[]{
                0, 1, 0, 0, 0, 0, 0, 1, // A
                0, 1, 0, 0, 0, 0, 1, 0, // B
                0, 1, 0, 0, 0, 0, 1, 1, // C
                0, 1, 0, 0, 0, 1, 0, 0, // D
                0, 1, 0, 0, 0, 1, 0, 1, // E
                0, 1, 0, 0, 0, 1, 1, 0, // F
                0, 1, 0, 0, 0, 1, 1, 1, // G
                0, 1, 0, 0, 1, 0, 0, 0, // H
                0, 1, 0, 0, 1, 0, 0, 1, // I
                0, 1, 0, 0, 1, 0, 1, 0, // J
                0, 1, 0, 0, 1, 0, 1, 1, // K
                0, 1, 0, 0, 1, 1, 0, 0, // L
                0, 1, 0, 0, 1, 1, 0, 1, // M
                0, 1, 0, 0, 1, 1, 1, 0, // N
                0, 1, 0, 0, 1, 1, 1, 1, // O
                0, 1, 0, 1, 0, 0, 0, 0, // P
                0, 1, 0, 1, 0, 0, 0, 1, // Q
                0, 1, 0, 1, 0, 0, 1, 0, // R
                0, 1, 0, 1, 0, 0, 1, 1, // S
                0, 1, 0, 1, 0, 1, 0, 0, // T
                0, 1, 0, 1, 0, 1, 0, 1, // U
                0, 1, 0, 1, 0, 1, 1, 0, // V
                0, 1, 0, 1, 0, 1, 1, 1, // W
                0, 1, 0, 1, 1, 0, 0, 0, // X
                0, 1, 0, 1, 1, 0, 0, 1, // Y
                0, 1, 0, 1, 1, 0, 1, 0, // Z
        }, Des.stringToBits("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // Chiffres
        assertArrayEquals(new int[]{
                0, 0, 1, 1, 0, 0, 0, 0, // 0
                0, 0, 1, 1, 0, 0, 0, 1, // 1
                0, 0, 1, 1, 0, 0, 1, 0, // 2
                0, 0, 1, 1, 0, 0, 1, 1, // 3
                0, 0, 1, 1, 0, 1, 0, 0, // 4
                0, 0, 1, 1, 0, 1, 0, 1, // 5
                0, 0, 1, 1, 0, 1, 1, 0, // 6
                0, 0, 1, 1, 0, 1, 1, 1, // 7
                0, 0, 1, 1, 1, 0, 0, 0, // 8
                0, 0, 1, 1, 1, 0, 0, 1, // 9
        }, Des.stringToBits("0123456789"));

        // Symboles mathématiques
        assertArrayEquals(new int[]{
                0, 0, 1, 0, 1, 0, 1, 1, // +
                0, 0, 1, 0, 1, 1, 0, 1, // -
                0, 0, 1, 0, 1, 1, 1, 1, // /
                0, 0, 1, 0, 1, 0, 1, 0, // *
                0, 0, 1, 0, 0, 1, 0, 1, // %
                0, 0, 1, 1, 1, 1, 0, 1, // =
        }, Des.stringToBits("+-/*%="));

        // Ponctuations
        assertArrayEquals(new int[]{
                0, 0, 1, 1, 1, 1, 1, 1, // ?
                0, 0, 1, 0, 0, 0, 0, 1, // !
                0, 0, 1, 0, 1, 1, 1, 0, // .
                0, 0, 1, 0, 1, 1, 0, 0, // ,
                0, 0, 1, 1, 1, 0, 1, 1, // ;
                0, 0, 1, 1, 1, 0, 1, 0, // :
                0, 1, 0, 1, 1, 1, 1, 1, // _
                0, 0, 1, 0, 0, 0, 0, 0, // space
        }, Des.stringToBits("?!.,;:_ "));

        // Parenthèses
        assertArrayEquals(new int[]{
                0, 0, 1, 0, 1, 0, 0, 0, // (
                0, 0, 1, 0, 1, 0, 0, 1, // )
                0, 1, 1, 1, 1, 0, 1, 1, // {
                0, 1, 1, 1, 1, 1, 0, 1, // }
                0, 1, 0, 1, 1, 0, 1, 1, // [
                0, 1, 0, 1, 1, 1, 0, 1, // ]
        }, Des.stringToBits("(){}[]"));

        // Caractères spéciaux
        assertArrayEquals(new int[]{
                0, 0, 1, 0, 0, 1, 1, 0, // &
                0, 1, 0, 0, 0, 0, 0, 0, // @
                0, 0, 1, 0, 0, 0, 1, 1, // #
                0, 1, 0, 1, 1, 1, 1, 0, // ^
                0, 0, 1, 0, 0, 0, 1, 0, // "
                0, 0, 1, 0, 0, 1, 1, 1, // '
                0, 1, 1, 0, 0, 0, 0, 0, // `
        }, Des.stringToBits("&@#^\"'`"));
    }

    @Test
    void bitsToString() {

        // Chaine vide
        assertEquals("", Des.bitsToString(new int[]{}));

        // Minuscules
        assertEquals("abcdefghijklmnopqrstuvwxyz", Des.bitsToString(new int[]{
                0, 1, 1, 0, 0, 0, 0, 1, // a
                0, 1, 1, 0, 0, 0, 1, 0, // b
                0, 1, 1, 0, 0, 0, 1, 1, // c
                0, 1, 1, 0, 0, 1, 0, 0, // d
                0, 1, 1, 0, 0, 1, 0, 1, // e
                0, 1, 1, 0, 0, 1, 1, 0, // f
                0, 1, 1, 0, 0, 1, 1, 1, // g
                0, 1, 1, 0, 1, 0, 0, 0, // h
                0, 1, 1, 0, 1, 0, 0, 1, // i
                0, 1, 1, 0, 1, 0, 1, 0, // j
                0, 1, 1, 0, 1, 0, 1, 1, // k
                0, 1, 1, 0, 1, 1, 0, 0, // l
                0, 1, 1, 0, 1, 1, 0, 1, // m
                0, 1, 1, 0, 1, 1, 1, 0, // n
                0, 1, 1, 0, 1, 1, 1, 1, // o
                0, 1, 1, 1, 0, 0, 0, 0, // p
                0, 1, 1, 1, 0, 0, 0, 1, // q
                0, 1, 1, 1, 0, 0, 1, 0, // r
                0, 1, 1, 1, 0, 0, 1, 1, // s
                0, 1, 1, 1, 0, 1, 0, 0, // t
                0, 1, 1, 1, 0, 1, 0, 1, // u
                0, 1, 1, 1, 0, 1, 1, 0, // v
                0, 1, 1, 1, 0, 1, 1, 1, // w
                0, 1, 1, 1, 1, 0, 0, 0, // x
                0, 1, 1, 1, 1, 0, 0, 1, // y
                0, 1, 1, 1, 1, 0, 1, 0, // z
        }));

        // Majuscules
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", Des.bitsToString(new int[]{
                0, 1, 0, 0, 0, 0, 0, 1, // A
                0, 1, 0, 0, 0, 0, 1, 0, // B
                0, 1, 0, 0, 0, 0, 1, 1, // C
                0, 1, 0, 0, 0, 1, 0, 0, // D
                0, 1, 0, 0, 0, 1, 0, 1, // E
                0, 1, 0, 0, 0, 1, 1, 0, // F
                0, 1, 0, 0, 0, 1, 1, 1, // G
                0, 1, 0, 0, 1, 0, 0, 0, // H
                0, 1, 0, 0, 1, 0, 0, 1, // I
                0, 1, 0, 0, 1, 0, 1, 0, // J
                0, 1, 0, 0, 1, 0, 1, 1, // K
                0, 1, 0, 0, 1, 1, 0, 0, // L
                0, 1, 0, 0, 1, 1, 0, 1, // M
                0, 1, 0, 0, 1, 1, 1, 0, // N
                0, 1, 0, 0, 1, 1, 1, 1, // O
                0, 1, 0, 1, 0, 0, 0, 0, // P
                0, 1, 0, 1, 0, 0, 0, 1, // Q
                0, 1, 0, 1, 0, 0, 1, 0, // R
                0, 1, 0, 1, 0, 0, 1, 1, // S
                0, 1, 0, 1, 0, 1, 0, 0, // T
                0, 1, 0, 1, 0, 1, 0, 1, // U
                0, 1, 0, 1, 0, 1, 1, 0, // V
                0, 1, 0, 1, 0, 1, 1, 1, // W
                0, 1, 0, 1, 1, 0, 0, 0, // X
                0, 1, 0, 1, 1, 0, 0, 1, // Y
                0, 1, 0, 1, 1, 0, 1, 0, // Z
        }));

        // Chiffres
        assertEquals("0123456789", Des.bitsToString(new int[]{
                0, 0, 1, 1, 0, 0, 0, 0, // 0
                0, 0, 1, 1, 0, 0, 0, 1, // 1
                0, 0, 1, 1, 0, 0, 1, 0, // 2
                0, 0, 1, 1, 0, 0, 1, 1, // 3
                0, 0, 1, 1, 0, 1, 0, 0, // 4
                0, 0, 1, 1, 0, 1, 0, 1, // 5
                0, 0, 1, 1, 0, 1, 1, 0, // 6
                0, 0, 1, 1, 0, 1, 1, 1, // 7
                0, 0, 1, 1, 1, 0, 0, 0, // 8
                0, 0, 1, 1, 1, 0, 0, 1, // 9
        }));

        // Symboles mathématiques
        assertEquals("+-/*%=", Des.bitsToString(new int[]{
                0, 0, 1, 0, 1, 0, 1, 1, // +
                0, 0, 1, 0, 1, 1, 0, 1, // -
                0, 0, 1, 0, 1, 1, 1, 1, // /
                0, 0, 1, 0, 1, 0, 1, 0, // *
                0, 0, 1, 0, 0, 1, 0, 1, // %
                0, 0, 1, 1, 1, 1, 0, 1, // =
        }));

        // Ponctuations
        assertEquals("?!.,;:_ ", Des.bitsToString(new int[]{
                0, 0, 1, 1, 1, 1, 1, 1, // ?
                0, 0, 1, 0, 0, 0, 0, 1, // !
                0, 0, 1, 0, 1, 1, 1, 0, // .
                0, 0, 1, 0, 1, 1, 0, 0, // ,
                0, 0, 1, 1, 1, 0, 1, 1, // ;
                0, 0, 1, 1, 1, 0, 1, 0, // :
                0, 1, 0, 1, 1, 1, 1, 1, // _
                0, 0, 1, 0, 0, 0, 0, 0, // space
        }));

        // Parenthèses
        assertEquals("(){}[]", Des.bitsToString(new int[]{
                0, 0, 1, 0, 1, 0, 0, 0, // (
                0, 0, 1, 0, 1, 0, 0, 1, // )
                0, 1, 1, 1, 1, 0, 1, 1, // {
                0, 1, 1, 1, 1, 1, 0, 1, // }
                0, 1, 0, 1, 1, 0, 1, 1, // [
                0, 1, 0, 1, 1, 1, 0, 1, // ]
        }));

        // Caractères spéciaux
        assertEquals("&@#^\"'`", Des.bitsToString(new int[]{
                0, 0, 1, 0, 0, 1, 1, 0, // &
                0, 1, 0, 0, 0, 0, 0, 0, // @
                0, 0, 1, 0, 0, 0, 1, 1, // #
                0, 1, 0, 1, 1, 1, 1, 0, // ^
                0, 0, 1, 0, 0, 0, 1, 0, // "
                0, 0, 1, 0, 0, 1, 1, 1, // '
                0, 1, 1, 0, 0, 0, 0, 0, // `
        }));
    }

    @Test
    void xor() {
        assertArrayEquals(
                new int[]{0, 1, 1, 0},
                Des.xor(new int[]{0, 1, 0, 1}, new int[]{0, 0, 1, 1})
        );
    }

    @Test
    void decoupage() {
        assertArrayEquals(new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {9, 10, 11},
                {12, 13, 14},
                {15, 16, 17},
                {18, 19, 0}
        }, Des.decoupage(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 7));
    }

    @Test
    void recollageBloc() {
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                Des.recollageBloc(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10},})
        );
    }

    @Test
    void decaleGauche() {
        assertArrayEquals(new int[]{0, 0, 1, 0}, Des.decaleGauche(new int[]{0, 0, 0, 1}, 1));
        assertArrayEquals(new int[]{0, 1, 0, 0}, Des.decaleGauche(new int[]{0, 0, 0, 1}, 2));
        assertArrayEquals(new int[]{0, 0, 0, 1}, Des.decaleGauche(new int[]{0, 0, 0, 1}, 4));
        assertArrayEquals(new int[]{0, 1, 0, 0}, Des.decaleGauche(new int[]{0, 0, 0, 1}, 10));

        assertArrayEquals(
                new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3},
                Des.decaleGauche(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, 4)
        );
    }

    @Test
    void generePermutation() {
        // Longueur de la permuation
        assertEquals(64, Des.generePermutation(64).length);

        /* Utilisation d'ensemble pour détecter la présence d'éléments, de doublons, etc... */
        int[] unePermuation = Des.generePermutation(64);

        Set<Integer> ensemblePermutation = new HashSet<>();
        for (int valeur : unePermuation) ensemblePermutation.add(valeur);

        // Pas de Doublons
        assertEquals(ensemblePermutation.size(), unePermuation.length);

        // contiens les éléments de 0 a n-1 (n est la longueur de la liste)
        for (int i = 0; i < unePermuation.length; i++) {
            assertTrue(ensemblePermutation.contains(i));
        }

        // Ne contiens pas n (la longueur de la liste)
        assertFalse(ensemblePermutation.contains(64));
    }

    @Test
    void permutation() {
        // Erreur si les tailles des paramètres ne sont pas les mêmes
        assertThrows(IllegalArgumentException.class, () -> Des.permutation(new int[]{1, 2}, new int[]{0, 1, 2, 3, 4, 5}));
        assertThrows(IllegalArgumentException.class, () -> Des.permutation(new int[]{0, 1, 2, 3, 4, 5}, new int[]{1, 2}));

        // Test de la longueur du résultat
        assertEquals(5, Des.permutation(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 3, 4}).length);

        // Test de permuation simple
        assertArrayEquals(new int[]{1, 5, 3, 4, 2, 0}, Des.permutation(
                new int[]{1, 5, 3, 4, 2, 0},
                new int[]{0, 1, 2, 3, 4, 5}
        ));

        // Test de permutation complexe
        assertArrayEquals(new int[]{2, 0, 1, 5, 4, 3}, Des.permutation(
                new int[]{1, 5, 3, 4, 2, 0},
                new int[]{3, 2, 4, 1, 5, 0}
        ));
    }

    @Test
    void invPermutation() {
        // Erreur si les tailles des paramètres ne sont pas les mêmes
        assertThrows(IllegalArgumentException.class, () -> Des.invPermutation(new int[]{1, 2}, new int[]{0, 1, 2, 3, 4, 5}));
        assertThrows(IllegalArgumentException.class, () -> Des.invPermutation(new int[]{0, 1, 2, 3, 4, 5}, new int[]{1, 2}));

        // Test de la longueur du résultat
        assertEquals(5, Des.invPermutation(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 3, 4}).length);

        // Test de permuation simple
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5}, Des.invPermutation(
                new int[]{1, 5, 3, 4, 2, 0},
                new int[]{1, 5, 3, 4, 2, 0}
        ));

        // Test de permutation complexe
        assertArrayEquals(new int[]{3, 2, 4, 1, 5, 0}, Des.invPermutation(
                new int[]{1, 5, 3, 4, 2, 0},
                new int[]{2, 0, 1, 5, 4, 3}
        ));
    }

    @Test
    void genereCle() {
        for (Des des : FIXTURE) {
            // 1 élément généré
            assertEquals(1, des.genereCle(1).length);

            // Premier élément est composé de 48 éléments
            assertEquals(48, des.genereCle(1)[0].length);
        }
    }

    @Test
    void fonction_S() {
        for (Des des : FIXTURE) {
            des.genereS(0);

            String coordonneeStr = Integer.toString(des.table_S.get(0)[3][9], 2);
            int resultat_attendu_int = Integer.parseInt(coordonneeStr);

            int[] resultat_attendu = new int[4];
            for (int i = 0; i < resultat_attendu.length; i++, resultat_attendu_int /= 10)
                resultat_attendu[resultat_attendu.length - i - 1] = resultat_attendu_int % 10;

            assertArrayEquals(resultat_attendu, des.fonction_S(new int[]{1, 1, 0, 0, 1, 1}, 0));
        }
    }

    @Test
    void fonction_F() {
        // Impossible à tester, dépend fortement du Random
//        System.out.println(Arrays.toString(des.fonction_F(1, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17})));
    }

    @Test
    @DisplayName("crypte() & decrypte()")
    void crypteDecrypte() {
        for (Des des : FIXTURE) {
            String message = "Hello World!";
            int[] messageCrypte = des.crypte(message);
            assertEquals(message, des.decrypte(messageCrypte));
        }
    }
}