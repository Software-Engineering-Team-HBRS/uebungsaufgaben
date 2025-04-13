package Aufgabe2.factory;

import Aufgabe2.buisnesslogic.CardBox;

public class CardBoxFactory {
    public static CardBox createCardBox() {
        return new CardBox();
    }
}
