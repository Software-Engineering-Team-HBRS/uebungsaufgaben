package Uebung1.client;

import Uebung1.businesslogic.HexNumberTransformer;



public class Client extends HexNumberTransformer {
    public void printTransformation(int number) {
        String result = Utils.createHexNumberTranformer().transformNumber(number);
        System.out.println("Die Hexadezimaldarstellung der Zahl " + number + " ist: " + result);
    }

}
