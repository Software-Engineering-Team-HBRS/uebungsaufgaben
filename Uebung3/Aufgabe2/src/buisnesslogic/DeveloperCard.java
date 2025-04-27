package buisnesslogic;

public class DeveloperCard extends AbstractPersonCard {
    DeveloperCard(String firstName, String lastName, int id) {
        super(firstName, lastName, id);
    }

    public boolean hasEnoughCoffee(){
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", Hat genug Kaffee = " + hasEnoughCoffee();
    }
}
