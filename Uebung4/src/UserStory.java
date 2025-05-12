import java.io.Serializable;

public class UserStory implements Serializable {

    int id;
    String description;
    Priority priority;
    Task[] tasks;

    public UserStory(int id, String description, Priority priority) throws UserStoryException {

        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public boolean hasTasks() {
        return this.tasks != null;
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description + ", Priorität: " + this.priority;
    }
}
