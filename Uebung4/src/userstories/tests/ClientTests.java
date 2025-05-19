package userstories.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userstories.UserStoryClient;

public class ClientTests {

    UserStoryClient client;

    @BeforeEach
    void setUp() {
        client = new UserStoryClient();
        client.SetSaveFilePath("client_tests_user_stories.txt");
    }

    @Test
    void testInput() {
        assert !client.processInput("");
        assert !client.processInput(null);
        assert !client.processInput("hamburger");

        assert client.processInput("story 1 must someDescription");
        assert client.processInput("story 2 should 3");
        assert !client.processInput("story 1 must someDescription");
        assert !client.processInput("story");
        assert !client.processInput("story one should desc");
        assert !client.processInput("story should 1 desc");
        assert !client.processInput("story 1 desc should");
        assert !client.processInput("story 1 important desc");

        assert client.processInput("task 1 desc");
        assert client.processInput("task 0");
        assert !client.processInput("task 1 blabla");
        assert !client.processInput("task");
        assert !client.processInput("task one desc");
        assert !client.processInput("task desc 1");

        assert client.processInput("assign 1 1");
        assert !client.processInput("assign 1 1");
        assert !client.processInput("assign");
        assert !client.processInput("assign 0");
        assert !client.processInput("assign zero 0");
        assert !client.processInput("assign 0 zero");

        assert client.processInput("stories");
        assert client.processInput("tasks");

        assert client.processInput("save");
        assert client.processInput("load");
    }
}
