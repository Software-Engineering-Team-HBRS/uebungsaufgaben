package businesslogic;

import java.io.Serializable;

public abstract class AbstractPersonCard implements PersonCard, Serializable {
    String FirstName;
    String LastName;
    int id;

    public AbstractPersonCard(String firstName, String lastName, int id) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return this.FirstName;
    }
    public String getLastName(){
        return this.LastName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public int getId(){ // Die ID dient als Primärschlüssel zur Unterscheidung alle PersonCard Objekte.
        return this.id;      // Die ID darf nicht innerhalb der CardBox-Klasse gesetzt werden.
    }

    private void setId(int id){
        this.id = id;
    }

    public String toString() {
        return "ID = " + getId() + ", Vorname = " + getFirstName() + ", Nachname = " + getLastName();
    }

}
