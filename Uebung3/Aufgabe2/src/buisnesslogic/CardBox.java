package buisnesslogic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//FA1
public class CardBox {
    public static final String SAVEFILEPATH = "Aufgabe2/file.txt";
    private static CardBox instance;
    private final List<PersonCard> cards;

    private CardBox() {
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

    //FA4
    public int size() {
        return cards.size();
    }

    //CR1 - Singleton Pattern - Factory wird nicht mehr benötigt
    public static CardBox getInstance() {
        if (instance == null) {
            instance = new CardBox();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    //CR2
    public void save() throws CardBoxStorageException {
        try (FileOutputStream fos = new FileOutputStream(SAVEFILEPATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cards);
        } catch (IOException e) {
            throw new CardBoxStorageException("Fehler beim Speichern der CardBox", e);
        }
    }

    public void load() throws CardBoxStorageException {

        File file = new File(SAVEFILEPATH);
        if (!file.exists()) {
           throw new CardBoxStorageException("Datei nicht gefunden");
        }

        try (FileInputStream fis = new FileInputStream(SAVEFILEPATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (obj instanceof List) {
                cards.clear();
                cards.addAll((List<PersonCard>) obj);
            } else {
                throw new CardBoxStorageException("unerwarteter Fehler beim Laden der CardBox");
            }
        }
        catch (StreamCorruptedException e) {
            throw new CardBoxStorageException("Dateiformat ungültig – Liste von PersonCard erwartet.", e);
        }
        catch (IOException e) {
            throw new CardBoxStorageException("IO Fehler beim Laden der CardBox", e);
        }
        catch (ClassNotFoundException e) {
            throw new CardBoxStorageException("class not found exception", e);
        }
    }

    //CR3
    public List<PersonCard> getCurrentList() {
        return cards;
    }
}
