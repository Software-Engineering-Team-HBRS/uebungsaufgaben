package Aufgabe2.buisnesslogic;

public class EnduserCard extends AbstractPersonCard {
    public EnduserCard(String firstName, String lastName, int id) {
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
