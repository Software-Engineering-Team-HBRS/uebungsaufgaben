package test;

import businesslogic.HexNumberTransformer;
import businesslogic.NumberTransformer;
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