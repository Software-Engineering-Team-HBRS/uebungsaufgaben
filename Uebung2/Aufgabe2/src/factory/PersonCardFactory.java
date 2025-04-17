package factory;

import buisnesslogic.DeveloperCard;
import buisnesslogic.EnduserCard;
import buisnesslogic.PersonCard;

public class PersonCardFactory {
    public static PersonCard createEnduserCard(String FirstName, String LastName, int id) {return new EnduserCard(FirstName, LastName, id);}
    public static PersonCard createDeveloperCard(String Firstname, String LastName, int id) {return new DeveloperCard(Firstname, LastName, id);}

}
