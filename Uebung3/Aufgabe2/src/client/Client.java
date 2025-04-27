package client;

import buisnesslogic.*;

import java.util.List;

//CR3
public class Client {

    public Client() {
        CardBox box = CardBox.getInstance();

        PersonCard derek = PersonCardFactory.createDeveloperCard("Derek", "Yu", 123);
        PersonCard manfred = PersonCardFactory.createEnduserCard("Manfred", "Tunnel", 456);
        PersonCard van = PersonCardFactory.createEnduserCard("Van", "Horsing", 789);

        try {
            box.addPersonCard(derek);
            box.addPersonCard(manfred);
            box.addPersonCard(van);

            List<PersonCard> cards = box.getCurrentList();
            PersonCardView.showContent(cards);
        }
        catch (CardBoxException e) {
            System.out.println(e.getMessage());
        }
    }
}
