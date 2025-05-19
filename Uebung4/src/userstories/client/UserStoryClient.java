package userstories.client;

import userstories.manager.Priority;
import userstories.manager.UserStoryException;
import userstories.manager.UserStoryManager;

public class UserStoryClient {

    UserStoryManager manager;

    public UserStoryClient() {
        manager = new UserStoryManager();
    }

    public void processInput(String input)
    {
        System.out.print("> ");
        String[] command = input.split(" ");
        try {
            switch (command[0]) {
                case "stories":
                    manager.showStories();
                    break;
                case "tasks":
                    manager.showTasks();
                    break;
                case "load":
                    manager.load();
                    break;
                case "save":
                    manager.save();
                    break;
                case "story":
                    manager.createStory(Integer.parseInt(command[1]), command[2], Priority.valueOf(command[3]));
                    break;
                case "task":
                    manager.createTask(Integer.parseInt(command[1]), command[2]);
                    break;
                case "assign":
                    manager.assign(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    break;
                default:
                    System.out.println("ungültiger Befehl!");
            }
        } catch (UserStoryException e) {
            System.out.println(e.getMessage());
        }
    }
}
