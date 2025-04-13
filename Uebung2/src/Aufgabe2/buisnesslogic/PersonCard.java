package Aufgabe2.buisnesslogic;

public interface PersonCard {
    public String getFirstName();
    public String getLastName();
    // Die ID dient als Primärschlüssel zur Unterscheidung alle PersonCard Objekte.
    // Die ID darf nicht innerhalb der CardBox-Klasse gesetzt werden.
    public int getId();

}
