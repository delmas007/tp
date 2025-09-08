package ci.overnetflow.tp.domain;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CalculTest {

    private Calcul calcul;

    @BeforeEach
    void setUp() {
        // Initialisation d'un objet Calcul avec deux valeurs (peuvent être modifiées)
        calcul = new Calcul(10f, 5f);
    }

    @Test
    void testAdditionner() {
        // Addition de 10 + 5 = 15
        Float resultat = calcul.additionner(10f, 5f);
        assertEquals(15f, resultat, "L'addition de 10 + 5 doit être 15");
    }

    @Test
    void testSoustraire() {
        // Soustraction de 10 - 5 = 5
        Float resultat = calcul.soustraire(10f, 5f);
        assertEquals(5f, resultat, "La soustraction de 10 - 5 doit être 5");
    }

    @Test
    void testMultiplier() {
        // Multiplication de 10 * 5 = 50
        Float resultat = calcul.multiplier(10f, 5f);
        assertEquals(50f, resultat, "La multiplication de 10 * 5 doit être 50");
    }

    @Test
    void testDiviser() throws Exception {
        // Division de 10 / 5 = 2
        Float resultat = calcul.diviser(10f, 5f);
        assertEquals(2f, resultat, "La division de 10 / 5 doit être 2");
    }

    @Test
    void testDiviserSansException() throws Exception {
        // Test d'une division normale
        Float resultat = calcul.diviser(10f, 2f); // pas de division par zéro
        assertEquals(5f, resultat, "La division de 10 / 2 doit être 5");
    }


    @Test
    void testIdentiteRemarquable() {
        // (a + b)^2 = 10 + 5 = 15 => 15^2 = 225
        Float resultat = calcul.identiteRemarquable(10f, 5f);
        assertEquals(225f, resultat, "L'identité remarquable (10+5)^2 doit être 225");
    }
}
