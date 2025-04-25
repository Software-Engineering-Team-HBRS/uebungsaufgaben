package buisnesslogic;

import java.util.ArrayList;
import java.util.List;

    //FA1
public class CardBox {
    List<PersonCard> cards;

    public CardBox() {
        cards = new ArrayList<PersonCard>();
    }

    public void addPersonCard(PersonCard personCard) throws CardBoxException {
        for(PersonCard c : cards) {
            if(c.getId() == personCard.getId()) {
                throw new CardBoxException("Das CardBox-Objekt mit der ID: " + personCard.getId() + " ist bereits vorhanden");
            }
        }
        cards.add(personCard);
        return;
    }

    //FA2 - Nachteile dieser Lösung sind: Von einem String ist nicht direkt erkennbar, ob es ein Fehler ist.
    //Außerdem ist Exception Handhabung genau für Fehlererkennung als Standard eingeführt worden.
    public String deletePersonCard(int id) {
        for(PersonCard c : cards) {
            if(c.getId() == id) {
                cards.remove(c);
                return "Das CardBox-Objekt mit der ID: " + id + " wurde entfernt.";
            }
        }
        return "Das CardBox-Objekt mit der ID: " + id + " konnte nicht entfernt werden, weil es nicht vorhanden ist.";
    }

    //FA3
    public void showContent() {
        for(PersonCard c : cards) {
            System.out.println(c.toString());
        }
    }

    //FA4
    public int size() {
        return cards.size();
    }
}
