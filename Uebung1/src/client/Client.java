package client;

import businesslogic.HexNumberTransformer;

public class Client {
    public void printTransformation(int number) {
        String result = Utils.createHexNumberTranformer().transformNumber(number);
        System.out.println("Die Hexadezimaldarstellung der Zahl " + number + " ist: " + result);
    }
}
