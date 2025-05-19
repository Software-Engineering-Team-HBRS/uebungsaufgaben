package userstories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserStory implements Serializable {

    int id;
    String description;
    Priority priority;
    List<Task> tasks = new ArrayList<Task>();

    public UserStory(int id, String description, Priority priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }


    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public void addTask(Task task) throws UserStoryException {
        if (tasks.contains(task)) {
            throw new UserStoryException("Task ist bereits zu dieser Story zugeordnet");
        }
        tasks.add(task);
    }

    public String toString() {
        return "ID: " + this.id + ", Beschreibung: " + this.description + ", Priorität: " + this.priority;
    }
}
