package userstories.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStoryManager {

    private List<UserStory> stories = new ArrayList<>();
    private String saveFilePath = "Uebung4/user_stories.txt";

    public void SetSaveFilePath(String path) {
        saveFilePath = path;
    }

    public void showStories() {

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

    public void showTasks() {

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

    public void createStory(int id, String description, Priority priority) throws UserStoryException {

        if(userStoryIDExists(id)){

            System.out.println("UserStories.UserStory-ID bereits vergeben.");
        }
        else {

            stories.add(new UserStory(id, description.substring(1, description.length()-1),priority));
        }
    }

    public void createTask(int id, String description) throws UserStoryException {

        if(taskIDExists(id)){

            System.out.println("UserStories.Task-ID bereits vergeben.");
        }
        else {

            Task.tasks.add(new Task(id, description.substring(1, description.length()-1)));
        }
    }

    public void assign(int userStoryID, int taskID) throws UserStoryException {

        try{
            UserStory story = getUserStory(userStoryID);
            story.addTask(Task.getTask(taskID));
        } catch (UserStoryException e) {
            throw new UserStoryException(e.getMessage());
        }
    }

    public UserStory getUserStory(int id) throws UserStoryException {

        for (UserStory story : stories) {
            if(story.id==id){
                return story;
            }
        }
        throw new UserStoryException("User Story nicht vorhanden.");
    }

    public boolean userStoryIDExists(int id) {

        if(!stories.isEmpty()) {
            for (UserStory story : stories) {
                if (story.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean taskIDExists(int id) {

        if(!Task.tasks.isEmpty()) {
            for (Task task : Task.tasks) {
                if (task.id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public void save() throws UserStoryException {

        try (FileOutputStream fos = new FileOutputStream(saveFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
             oos.writeObject(stories);

            System.out.println("User Stories und Tasks wurden erfolgreich gespeichert.");
        } catch (IOException e) {
            throw new UserStoryException("Fehler beim Speichern der User Stories", e);
        }
    }

    public void load() throws UserStoryException {

        File file = new File(saveFilePath);
        if (!file.exists()) {
            throw new UserStoryException("Datei nicht gefunden");
        }

        if (file.length() == 0) {
            stories = new ArrayList<>();
            return;
        }

        try (FileInputStream fis = new FileInputStream(saveFilePath);
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