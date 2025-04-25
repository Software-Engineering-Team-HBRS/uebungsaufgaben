package buisnesslogic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    //FA1
public class CardBox {
    private static CardBox instance;
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



    //CR1 - Singleton Pattern - Factory wird nicht mehr benötigt?
    public static CardBox getInstance() {
        if (instance == null) {
            instance = new CardBox();
        }
        return instance;
    }

    //CR2
    public void save() throws CardBoxStorageException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Uebung3/Aufgabe3.2/src/file.txt"))) {
            oos.writeObject(cards);
        } catch (IOException e) {
            throw new CardBoxStorageException("Fehler beim Speichern der CardBox", e);
        }
    }

    public void load() throws CardBoxStorageException {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Uebung3/Aufgabe3.2/src/file.txt"))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                cards.clear();
                cards.addAll((List<PersonCard>) obj);
            } else {
                throw new CardBoxStorageException("Dateiformat ungültig – Liste von PersonCard erwartet.");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CardBoxStorageException("Fehler beim Laden der CardBox", e);
        }
    }
}
