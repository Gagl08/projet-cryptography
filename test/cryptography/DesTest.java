package cryptography;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DesTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void stringToBits() {
        HashMap<String, int[]> attendu  = new HashMap<>() {{
            put("Bonjour", new int[]{0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0});
        }};

        for (Map.Entry<String, int[]> test : attendu.entrySet()) {
            assertArrayEquals(Des.stringToBits(test.getKey()), test.getValue());
        }
    }

    @Test
    void generePermutation() {
    }

    @Test
    void bitsToString() {
    }

    @Test
    void permutation() {
    }

    @Test
    void invPermutation() {
    }

    @Test
    void decoupage() {
    }

    @Test
    void recollageBloc() {
    }

    @Test
    void decaleGauche() {
    }

    @Test
    void xor() {
    }

    @Test
    void genereCle() {
    }

    @Test
    void fonction_S() {
    }

    @Test
    void fonction_F() {
    }

    @Test
    void crypte() {
    }

    @Test
    void decrypte() {
    }
}