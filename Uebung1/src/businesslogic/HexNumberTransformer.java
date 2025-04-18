package businesslogic;

public class HexNumberTransformer implements businesslogic.NumberTransformer {
    @Override
    public String transformNumber(int number) {

        if(0 < number && number < 65535){
            return Integer.toHexString(number);
        }
        return "Nicht im Wertebereich 1 - 65535";
    }

    @Override
    public String getTransformerType() {

        return "Transformer für Hexadezimalzahlen";
    }



}
