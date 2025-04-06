package client;

import factory.NumberTransformerFactory;

public class Client {
    public void printTransformation(int number) {
        String result = NumberTransformerFactory.createHexNumberTranformer().transformNumber(number);
        System.out.println("Die Hexadezimaldarstellung der Zahl " + number + " ist: " + result);
    }
}
