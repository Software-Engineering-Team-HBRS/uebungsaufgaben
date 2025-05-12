import java.util.ArrayList;
import java.util.List;

public class Task {

    int id;
    String description;
    static List<Task> tasks = new ArrayList<Task>();

    public Task(int id, String description) {
        this.id = id;
        this.description = description;

        tasks.add(this);
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description;
    }

}
