package UserStories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStoryManager {

    private static List<UserStory> stories = new ArrayList<>();
    private static final String SAVEFILEPATH = "Uebung4/user_stories.txt";

    public static void processInput(String input)
    {
        System.out.print("> ");
        String[] command = input.split(" ");
        try {
            switch (command[0]) {
                case "stories":
                    showStories();
                    break;
                case "tasks":
                    showTasks();
                    break;
                case "load":
                    load();
                    break;
                case "save":
                    save();
                    break;
                case "story":
                    createStory(Integer.parseInt(command[1]), command[2], Priority.valueOf(command[3]));
                    break;
                case "task":
                    createTask(Integer.parseInt(command[1]), command[2]);
                    break;
                case "assign":
                    assign(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    break;
                default:
                    System.out.println("ungültiger Befehl!");
            }
        } catch (UserStoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showStories() {

        if(!stories.isEmpty()) {

            System.out.println("Die folgenden User Stories sind im System gespeichert:");

            for (UserStory story : stories) {

                System.out.println(story);

                if(story.hasTasks()) {

                    System.out.println("Zugeordnete Tasks:");
                    for (Task task : story.tasks) {

                        System.out.println(task);
                    }
                }
                else{

                    System.out.println("Keine Tasks zugeordnet.");
                }
            }
        }
        else{

            System.out.println("Keine User Stories vorhanden.");
        }
    }

    public static void showTasks() {

        if(!Task.tasks.isEmpty()) {

            System.out.println("Die folgenden Tasks sind im System gespeichert:");

            for (Task task : Task.tasks) {

                System.out.println(task);
            }
        }
        else {

            System.out.println("Keine Tasks vorhanden.");
        }
    }

    public static void createStory(int id, String description, Priority priority) throws UserStoryException {

        if(userStoryIDExists(id)){

            System.out.println("UserStories.UserStory-ID bereits vergeben.");
        }
        else {

            stories.add(new UserStory(id, description.substring(1, description.length()-1),priority));
        }
    }

    public static void createTask(int id, String description) throws UserStoryException {

        if(taskIDExists(id)){

            System.out.println("UserStories.Task-ID bereits vergeben.");
        }
        else {

            Task.tasks.add(new Task(id, description.substring(1, description.length()-1)));
        }
    }

    public static void assign(int userStoryID, int taskID) throws UserStoryException {

        try{
            UserStory story = getUserStory(userStoryID);
            story.addTask(Task.getTask(taskID));
        } catch (UserStoryException e) {
            throw new UserStoryException(e.getMessage());
        }
    }

    public static UserStory getUserStory(int id) throws UserStoryException {

        for (UserStory story : stories) {
            if(story.id==id){
                return story;
            }
        }
        throw new UserStoryException("User Story nicht vorhanden.");
    }

    public static boolean userStoryIDExists(int id) {

        if(!stories.isEmpty()) {
            for (UserStory story : stories) {
                if (story.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean taskIDExists(int id) {

        if(!Task.tasks.isEmpty()) {
            for (Task task : Task.tasks) {
                if (task.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void save() throws UserStoryException {

        try (FileOutputStream fos = new FileOutputStream(SAVEFILEPATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(stories);

            System.out.println("User Stories und Tasks wurden erfolgreich gespeichert.");
        } catch (IOException e) {
            throw new UserStoryException("Fehler beim Speichern der User Stories", e);
        }
    }

    public static void load() throws UserStoryException {

        File file = new File(SAVEFILEPATH);
        if (!file.exists()) {
            throw new UserStoryException("Datei nicht gefunden");
        }

        if (file.length() == 0) {
            stories = new ArrayList<>();
            return;
        }

        try (FileInputStream fis = new FileInputStream(SAVEFILEPATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();
            if (obj instanceof List) {
                stories.clear();
                stories.addAll((List<UserStory>) obj);
                System.out.println("User Stories und Tasks wurden erfolgreich geladen.");
            } else {
                throw new UserStoryException("unerwarteter Fehler beim Laden der User Stories");
            }
        }
        catch (StreamCorruptedException e) {
            throw new UserStoryException("Dateiformat ungültig – Liste von User Stories erwartet.", e);
        }
        catch (IOException e) {
            throw new UserStoryException("IO Fehler beim Laden der User Stories", e);
        }
        catch (ClassNotFoundException e) {
            throw new UserStoryException("class not found exception", e);
        }
    }
}