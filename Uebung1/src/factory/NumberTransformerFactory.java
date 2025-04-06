package factory;

import businesslogic.GermanFormatNumberTransformer;
import businesslogic.HexNumberTransformer;
import businesslogic.NumberTransformer;

public class NumberTransformerFactory
{
    static HexNumberTransformer hexTransformer = null;

    public static NumberTransformer createHexNumberTranformer() {

        //make sure there is never more than 1 hexTransformer instance
        if (hexTransformer == null) {
            hexTransformer = new HexNumberTransformer();
        }
        return hexTransformer;
    }

    public static NumberTransformer createGermanNumberTranformer(){
        return new GermanFormatNumberTransformer();
    }
}
