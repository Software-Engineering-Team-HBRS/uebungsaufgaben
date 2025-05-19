package userstories;

import java.util.List;

public class UserStoriesView {
    private final List<UserStory> stories;

    public UserStoriesView(List<UserStory> stories) {
        this.stories = stories;
    }

    public int getAmount() {
        return stories.size();
    }

    @Override
    public String toString() {
        if(stories.isEmpty()) {
            return "Keine User Stories vorhanden.";
        }
        String result = "Die folgenden User Stories sind im System gespeichert:\n";
        for (UserStory story : stories) {
            result += story.toString() + "\n";

            if(story.hasTasks()) {

                result += "\tZugeordnete Tasks:\n";
                for (Task task : story.tasks) {

                    result += "\t\t" + task.toString() + "\n";
                }
            }
            else {
                result += "\tKeine Tasks zugeordnet.\n";
            }
        }
        return result;
    }
}
