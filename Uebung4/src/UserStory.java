import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserStory implements Serializable {

    int id;
    String description;
    Priority priority;
    List<Task> tasks = new ArrayList<Task>();

    public UserStory(int id, String description, Priority priority) throws UserStoryException {

        this.id = id;
        this.description = description;
        this.priority = priority;
    }


    public boolean hasTasks() {
        return !this.tasks.isEmpty();
    }

    public void addTask(Task task) {

        tasks.add(task);
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description + ", Priorität: " + this.priority;
    }
}
