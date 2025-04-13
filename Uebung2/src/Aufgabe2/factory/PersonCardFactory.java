package Aufgabe2.factory;

import Aufgabe2.buisnesslogic.DeveloperCard;
import Aufgabe2.buisnesslogic.EnduserCard;
import Aufgabe2.buisnesslogic.PersonCard;

public class PersonCardFactory {
    public static PersonCard createEnduserCard(String FirstName, String LastName, int id) {return new EnduserCard(FirstName, LastName, id);}
    public static PersonCard createDeveloperCard(String Firstname, String LastName, int id) {return new DeveloperCard(Firstname, LastName, id);}

}
