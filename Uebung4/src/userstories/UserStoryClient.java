package userstories;

import java.util.Arrays;
import java.util.Map;

public class UserStoryClient {

    Map<String, String> expectedParams = Map.of(
            "story", "<story_id: number> <priority: must|should|could|won_t> <description: text>",
            "task", "<task_id: number> <description: text>",
            "assign", "<story_id: number> <task_id: number>"
    );

    UserStoryManager manager;

    public UserStoryClient() {
        manager = new UserStoryManager();
    }

    public void SetSaveFilePath(String filePath) {
        manager.SetSaveFilePath(filePath);
    }

    public boolean processInput(String input)
    {
        if (input == null) {
            System.out.println("Ungültiger Input: null");
            return false;
        }

        String[] split = input.split(" ");
        String command = split[0];
        try {
            switch (command) {
                case "stories":
                    System.out.println(manager.getStories().toString());
                    break;
                case "tasks":
                    System.out.println(manager.getTasks().toString());
                    break;
                case "load":
                    System.out.println(manager.load());
                    break;
                case "save":
                    System.out.println(manager.save());
                    break;
                case "story": {
                    int id = Integer.parseInt(split[1]);
                    Priority priority = Priority.valueOf(split[2]);
                    String description = String.join(" ", Arrays.copyOfRange(split, 3, split.length));
                    System.out.println(manager.createStory(id, priority, description));
                    } break;
                case "task": {
                    int id = Integer.parseInt(split[1]);
                    String description = String.join(" ", Arrays.copyOfRange(split, 2, split.length));
                    System.out.println(manager.createTask(id, description));
                    } break;
                case "assign": {
                    int storyId = Integer.parseInt(split[1]);
                    int taskId = Integer.parseInt(split[2]);
                    System.out.println(manager.assign(storyId, taskId));
                    } break;
                default:
                    throw new UserStoryException("ungültiger Befehl!");
            }
        } catch (UserStoryException e) {
            System.out.println("Fehler: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println("ungültige Parameter! erwartet:\n" + command + " " + expectedParams.get(command));
            return false;
        }
        return true;
    }
}
