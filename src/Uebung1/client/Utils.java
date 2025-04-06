package Uebung1.client;

import Uebung1.businesslogic.GermanFormatNumberTransformer;
import Uebung1.businesslogic.HexNumberTransformer;
import Uebung1.businesslogic.NumberTransformer;

//Frage 2:
public class Utils
{
    static HexNumberTransformer hexTransformer = null;

    public static NumberTransformer createHexNumberTranformer(){
        if (hexTransformer == null) {
            hexTransformer = new HexNumberTransformer();
        }
        return hexTransformer;

        //Frage 3:
        //Diese Implementation der Methode garantiert, dass nie mehr als 1 NumberTransformer instanziiert wird.
    }

    public static NumberTransformer createGermanNumberTranformer(){
        return new GermanFormatNumberTransformer();
    }
}
