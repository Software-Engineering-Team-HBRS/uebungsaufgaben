package userstories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {

    int id;
    String description;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description;
    }
}
