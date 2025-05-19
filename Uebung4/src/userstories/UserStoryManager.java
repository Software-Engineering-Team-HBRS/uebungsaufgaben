package userstories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStoryManager {

    private final List<UserStory> stories = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private String saveFilePath = "Uebung4/user_stories.txt";

    public void SetSaveFilePath(String path) {
        saveFilePath = path;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public UserStoriesView getStories() {

        return new UserStoriesView(stories);
    }

    public TasksView getTasks() {

        return new TasksView(tasks);
    }

    public String createStory(int id, Priority priority, String description) throws UserStoryException {
        if(userStoryIDExists(id)) {
            throw new UserStoryException("User Story ID " + id + " bereits vergeben.");
        }
        stories.add(new UserStory(id, description, priority));
        return "User Story " + id + " wurde erfolgreich erstellt.";
    }

    public String createTask(int id, String description) throws UserStoryException {
        if(taskIDExists(id)) {
            throw new UserStoryException("Task ID " + id + " ist bereits vergeben.");
        }
        tasks.add(new Task(id, description));
        return "Task " + id + " wurde erfolgreich erstellt.";
    }

    public String assign(int userStoryID, int taskID) throws UserStoryException {
        UserStory story = getUserStory(userStoryID);
        story.addTask(getTask(taskID));
        return "Task " + taskID + " wurde erfolgreich zu User Story " + userStoryID + " zugewiesen";
    }

    private UserStory getUserStory(int id) throws UserStoryException {

        for (UserStory story : stories) {
            if(story.id == id){
                return story;
            }
        }
        throw new UserStoryException("User Story " + id + " nicht vorhanden.");
    }

    private Task getTask(int id) throws UserStoryException {

        for (Task task : tasks) {
            if(task.id == id){
                return task;
            }
        }
        throw new UserStoryException("Task " + id + " nicht vorhanden.");
    }

    public boolean userStoryIDExists(int id) {

        for (UserStory story : stories) {
            if (story.id == id) {
                return true;
            }
        }
        return false;
    }

    public boolean taskIDExists(int id) {

        for (Task task : tasks) {
            if (task.id == id) {
                return true;
            }
        }
        return false;
    }

    public String save() throws UserStoryException {

        try (FileOutputStream fos = new FileOutputStream(saveFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

             oos.writeObject(stories);
             oos.writeObject(tasks);

             return "User Stories und Tasks wurden erfolgreich gespeichert.";

        } catch (IOException e) {
            throw new UserStoryException("Fehler beim Speichern der User Stories", e);
        }
    }

    public String load() throws UserStoryException {

        File file = new File(saveFilePath);
        if (!file.exists()) {
            throw new UserStoryException("Datei nicht gefunden");
        }
        stories.clear();
        tasks.clear();

        if (file.length() > 0) {
            try (FileInputStream fis = new FileInputStream(saveFilePath);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                Object storiesObj = ois.readObject();
                Object tasksObj = ois.readObject();

                if (storiesObj instanceof List && tasksObj instanceof  List) {
                    stories.addAll((List<UserStory>) storiesObj);
                    tasks.addAll((List<Task>) tasksObj);
                } else {
                    throw new UserStoryException("unerwarteter Fehler beim Laden der User Stories und Tasks");
                }
            }
            catch (StreamCorruptedException e) {
                throw new UserStoryException("Dateiformat ungültig – Liste von User Stories und Liste von Tasks erwartet", e);
            }
            catch (IOException e) {
                throw new UserStoryException("IO Fehler beim Laden der User Stories und Tasks", e);
            }
            catch (ClassNotFoundException e) {
                throw new UserStoryException("class not found exception", e);
            }
        }

        return "User Stories und Tasks wurden erfolgreich geladen.";
    }
}