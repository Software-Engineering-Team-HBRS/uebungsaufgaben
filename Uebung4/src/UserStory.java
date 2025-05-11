public class UserStory {

    int id;
    String description;
    Priority priority;
    Task[] tasks;

    public UserStory(int id, String description, Priority priority) throws UserStoryException {

        this.id = id;
        this.description = description;
        this.priority = priority;
    }
}
