package Uebung1.test;

import Uebung1.businesslogic.HexNumberTransformer;
import Uebung1.businesslogic.NumberTransformer;
import org.junit.jupiter.api.Test;

public class TestHexNumberTransformer {


    @Test
    void TestHexNumbers() {
        NumberTransformer transformer = new HexNumberTransformer();
        assert("Nicht im Wertebereich 1 - 65535".equals(transformer.transformNumber(-100)));
        assert("64".equals(transformer.transformNumber(100)));
        assert("Nicht im Wertebereich 1 - 65535".equals(transformer.transformNumber(100000)));
    }
}


//Frage 4: Damit sie klar getrennt vom production Code sind und nicht versehentlich geliefert werden
//Frage 5: Um sicherzustellen, dass alle unterschiedlichen Arten von Testfällen abgedeckt sind.
//Frage 6: Die Klasse client lässt sich nicht leicht testen, da sie keine Werte zurückgibt die überprüft werden können sondern Konsolen output printed, der schlecht getestet werden kann
