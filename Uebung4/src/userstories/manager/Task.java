package userstories.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {

    int id;
    String description;
    static List<Task> tasks = new ArrayList<Task>();

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description;
    }

    public static Task getTask(int id) throws UserStoryException {
        for (Task t : tasks) {
            if (t.id == id) {
                return t;
            }
        }

        throw new UserStoryException("UserStories.Task nicht vorhanden.");
    }

}
