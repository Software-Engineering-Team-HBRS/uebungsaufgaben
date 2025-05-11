import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserStoryManager {

    private static final Scanner sc = new Scanner(System.in);
    private static List<UserStory> stories;
    private static final String SAVEFILEPATH = "user_stories.txt";

    public static void main(String[] args) throws UserStoryException {

        //Initial Load
        load();

        while(true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (!isValidInput(input)) {
                System.out.println("Ungültiger Befehl.");
                continue;
            }

            String[] command = input.split(" ");

            switch(command[0]) {
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
                    createStory(Integer.parseInt(command[1]),command[2], Priority.valueOf(command[3]));
                    break;
                /*case "task":
                    try{
                        createTask(command[1],command[2]);
                    }
                    catch(UserStoryException e){

                    }
                    break;
                case "assign":
                    try{
                        assign(command[1],command[2]);
                    }
                    catch(UserStoryException e){

                    }
                    break;*/
            }

        }
    }

    public static boolean isEmpty(){

        return stories.isEmpty();
    }

    public static void showStories() {

        if(!stories.isEmpty()) {

            System.out.println("Die folgenden User Stories sind im System gespeichert:");

            for (UserStory story : stories) {

                System.out.println(story);
                System.out.println("Zugeordnete Tasks:");
                for (Task task : story.tasks) {
                    System.out.println(task);
                }
            }
        }
        else{
            System.out.println("Keine User Stories vorhanden.");
        }
    }

    public static void createStory(int id, String description, Priority priority) throws UserStoryException {

        if(userStoryExists(id)){

            System.out.println("UserStory existiert bereits.");
        }
        else {

            stories.add(new UserStory(id, description,priority));
        }
    }

    public static boolean userStoryExists(int id) {
        if(!stories.isEmpty()) {
            for (UserStory story : stories) {
                if (story.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void showTasks() {

        if(!stories.isEmpty()) {

            System.out.println("Die folgenden Tasks sind im System gespeichert:");

            for (UserStory story : stories) {

                for (Task task : story.tasks) {
                    System.out.println(task);
                }
            }
        }
        System.out.println("Keine Tasks vorhanden.");
    }

    public static void save() throws UserStoryException {

        try (FileOutputStream fos = new FileOutputStream(SAVEFILEPATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(stories);
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

    public static boolean isValidInput(String input) {
        return input.matches("^stories$") ||
                input.matches("^tasks$") ||
                input.matches("^load$") ||
                input.matches("^save$") ||
                input.matches("^story \\d+ \\S+ (must|should|could|won_t)$") ||
                input.matches("^task \\d+ \\S+$") ||
                input.matches("^assign \\d+ \\d+$");
    }
}