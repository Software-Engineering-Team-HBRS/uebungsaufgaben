package businesslogic;

import java.util.List;

//CR3
public class PersonCardView {

    public static void showContent(List<PersonCard> cards) {
        for(PersonCard c : cards) {
            System.out.println(c.toString());
        }
    }
}
