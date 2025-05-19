package userstories;

import java.util.List;

public class TasksView {
    private final List<Task> tasks;

    public TasksView(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getAmount() {
        return tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Keine Tasks vorhanden.";
        }
        String result = "Die folgenden Tasks sind im System gespeichert:\n";
        for (Task task : tasks) {
            result += task.toString() + "\n";
        }
        return result;
    }
}
