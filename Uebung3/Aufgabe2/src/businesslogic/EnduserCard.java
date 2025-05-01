package businesslogic;

public class EnduserCard extends AbstractPersonCard {
    EnduserCard(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
    }

    public boolean isHungry(){
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", Ist Hungrig = " + isHungry();
    }
}
