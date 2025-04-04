public class HexNumberTransformer implements org.hbrs.se1.ss25.uebung1.businesslogic.NumberTransformer {
    @Override
    public String transformNumber(int number) {

        //char number_char = (char) number;
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
