package factory;

import businesslogic.GermanFormatNumberTransformer;
import businesslogic.HexNumberTransformer;
import businesslogic.NumberTransformer;

public class NumberTransformerFactory
{
    public static NumberTransformer createHexNumberTranformer() {
        return new HexNumberTransformer();
    }

    public static NumberTransformer createGermanNumberTranformer(){
        return new GermanFormatNumberTransformer();
    }
}
