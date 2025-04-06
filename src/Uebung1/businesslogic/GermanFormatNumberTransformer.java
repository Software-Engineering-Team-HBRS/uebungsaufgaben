package Uebung1.businesslogic;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class GermanFormatNumberTransformer implements NumberTransformer{
    @Override
    public String transformNumber(int number) {

        if(0 < number && number < 65535){

            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
            DecimalFormat df = new DecimalFormat("#,###.##", symbols);
            return df.format(number);
        }
        return "Nicht im Wertebereich 1 - 65535";
    }

    @Override
    public String getTransformerType() {
        return "Transformer für deutsche Zahlenformatierungen";
    }
}
