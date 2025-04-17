package test;

import buisnesslogic.CardBox;
import buisnesslogic.CardBoxException;
import buisnesslogic.PersonCard;
import factory.CardBoxFactory;
import factory.PersonCardFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestCardBox {
    CardBox cardBox;
    PersonCard personCard1;
    PersonCard personCard2;
    PersonCard personCard3;
    @Before
    public void setUp() {
        cardBox = CardBoxFactory.createCardBox();
        personCard1 = PersonCardFactory.createEnduserCard("John", "Doe", 1);
        personCard2 = PersonCardFactory.createDeveloperCard("Doe", "Doe", 2);
        personCard3 = PersonCardFactory.createDeveloperCard("John", "John", 2);
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

        assertEquals(cardBox.deletePersonCard(2), "Das CardBox-Objekt mit der ID: 2 wurde entfernt.");
        assertEquals(cardBox.deletePersonCard(2), "Das CardBox-Objekt mit der ID: 2 konnte nicht entfernt werden, weil es nicht vorhanden ist.");
    }

    @Test
    public void testCardBoxInhalt() throws CardBoxException {
        cardBox.addPersonCard(personCard1);
        cardBox.addPersonCard(personCard2);
        cardBox.showContent();
        System.out.print("\n");
        cardBox.deletePersonCard(2);
        cardBox.showContent();
    }
}
//Fälle
//Add mit zwei unterschiedlichen ID's & mit gleichen ID's
//Delete bei einem das schon vorhanden ist und eines welches nicht vorhanden ist
//Werden alle Card aus DC und EC richtig dargestellt?
//Ist die Size richtig
