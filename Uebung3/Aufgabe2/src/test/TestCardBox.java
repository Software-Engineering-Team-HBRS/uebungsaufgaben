package test;

import buisnesslogic.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCardBox {
    CardBox cardBox;
    PersonCard personCard1;
    PersonCard personCard2;
    PersonCard personCard3;

    @BeforeEach
    public void setUp() {
        cardBox = CardBox.getInstance();
        personCard1 = PersonCardFactory.createEnduserCard("John", "Doe", 1);
        personCard2 = PersonCardFactory.createDeveloperCard("Doe", "Doe", 2);
        personCard3 = PersonCardFactory.createDeveloperCard("John", "John", 2);
    }

    @AfterEach
    public void tearDown() {
        CardBox.reset();
    }

    @AfterAll
    public static void tearDownAll() {
        deleteSaveFileIfExists();
    }

    @Test
    public void testCardBoxSize1() throws CardBoxException {
        assert cardBox.size() == 0; //CardBox sollte leer sein
        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);
        assert cardBox.size() == 2;
    }

    @Test
    public void testCardBoxAdd() throws CardBoxException {
        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);
        CardBoxException exception = assertThrows(CardBoxException.class, () -> cardBox.addPersonCard(personCard3));

        assertEquals("Das CardBox-Objekt mit der ID: 2 ist bereits vorhanden",exception.getMessage());
    }

    @Test
    public void testCardBoxDelete() throws CardBoxException {
        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);

        assertEquals("Das CardBox-Objekt mit der ID: 2 wurde entfernt.", cardBox.deletePersonCard(2));
        assertEquals("Das CardBox-Objekt mit der ID: 2 konnte nicht entfernt werden, weil es nicht vorhanden ist.", cardBox.deletePersonCard(2));
    }

    @Test
    public void testCardBoxShowContent() throws CardBoxException {
        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);
        PersonCardView.showContent(cardBox.getCurrentList());
        cardBox.deletePersonCard(2);
        PersonCardView.showContent(cardBox.getCurrentList());
    }

    //CR4
    @Test
    public void testCardBoxSaveAndLoad() throws CardBoxException {

        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);

        cardBox.save();
        assert cardBox.size() == 2;

        File file = new File(CardBox.SAVEFILEPATH);
        assert file.exists();

        cardBox.load();
        List<PersonCard> cards = cardBox.getCurrentList();
        assert cards.size() == 2;
        assert cards.get(0).toString().equals(personCard1.toString());
        assert cards.get(1).toString().equals(personCard2.toString());
    }

    @Test
    public void testCardBoxSaveAndLoad_Empty() throws CardBoxException {

        cardBox.save();
        assert cardBox.size() == 0;

        File file = new File(CardBox.SAVEFILEPATH);
        assert file.exists();

        cardBox.load();
        assert cardBox.size() == 0;
    }

    @Test
    public void testCardBoxLoad_FileCorrupt() throws IOException{

        try (FileWriter writer = new FileWriter(CardBox.SAVEFILEPATH)) {
            writer.write("corrupted file");
        }

        CardBoxStorageException exception = assertThrows(CardBoxStorageException.class, () -> cardBox.load());
        assertEquals("Dateiformat ungültig – Liste von PersonCard erwartet.", exception.getMessage());
    }

    @Test
    public void testCardBoxLoad_FileMissing() {
        deleteSaveFileIfExists();

        CardBoxStorageException exception = assertThrows(CardBoxStorageException.class, () -> cardBox.load());
        assertEquals("Datei nicht gefunden", exception.getMessage());
    }

    private static void deleteSaveFileIfExists() {
        File file = new File(CardBox.SAVEFILEPATH);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("deleted file");
            }
            else {
                System.out.println("failed to delete file");
            }
        }
    }
}
//Fälle
//Add mit zwei unterschiedlichen ID's & mit gleichen ID's
//Delete bei einem das schon vorhanden ist und eines welches nicht vorhanden ist
//Werden alle Card aus DC und EC richtig dargestellt?
//Ist die Size richtig
