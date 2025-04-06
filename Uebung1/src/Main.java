import businesslogic.GermanFormatNumberTransformer;
import businesslogic.HexNumberTransformer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Hello Patrick");

        HexNumberTransformer test = new HexNumberTransformer();
        String result = test.getTransformerType();
        System.out.println("transformer type: " + result);

        String numberResult = test.transformNumber(50);
        System.out.println("transformed number: " + numberResult);

        GermanFormatNumberTransformer germanTransformer = new GermanFormatNumberTransformer();
        String germanNumber = germanTransformer.transformNumber(63000);
        System.out.print("german Number: " + germanNumber);

        //1.1a) Frage 1:
        //man könnte das Interface NumberTransformer.java in eine abstrakte Klasse abändern und
        // dort eine Methode für den Wertebereich Check implementieren.
    }

}