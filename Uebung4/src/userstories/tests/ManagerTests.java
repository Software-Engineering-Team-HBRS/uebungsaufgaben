package userstories.tests;

import org.junit.jupiter.api.*;
import userstories.Priority;
import userstories.UserStoryException;
import userstories.UserStoryManager;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTests {
    static final String SAVE_FILE_TEST = "saveFileTest";
    static final String TEST_SAVE_FILE_PATH = "manager_tests_user_stories.txt";
    UserStoryManager manager;

    @BeforeEach
    void setUp(TestInfo info) throws Exception{
        manager = new UserStoryManager();

        if (info.getTags().contains(SAVE_FILE_TEST)) {
            manager.SetSaveFilePath(TEST_SAVE_FILE_PATH);
            deleteSaveFileIfExists();
        }
    }

    @AfterEach
    public void tearDown(TestInfo info) throws Exception {
        if (info.getTags().contains(SAVE_FILE_TEST)) {
            deleteSaveFileIfExists();
        }
    }

    void addSomeStories() throws Exception {
        manager.createStory(1, Priority.must, "user story beschreibung.,ßäüö#!?^%\n/{} _-|`~\\\t<[\"']>");
        manager.createStory(0, Priority.could, "");
        manager.createStory(-5, Priority.won_t, null);
    }

    void addSomeTasks() throws Exception{
        manager.createTask(1, "task beschreibung.,ßäüö#!?^%\n/{} _-|`~\\\t<[\"']>");
        manager.createTask(0, "");
        manager.createTask(-10, null);
    }

    @Test
    void testCreateStory() throws Exception {
        assert manager.getStories().getAmount() == 0;
        addSomeStories();
        assert manager.getStories().getAmount() == 3;
        assert manager.userStoryIDExists(1);
        assert manager.userStoryIDExists(0);
        assert manager.userStoryIDExists(-5);

        assertThrows(UserStoryException.class, () -> { manager.createStory(1, Priority.could, "andere Beschreibung"); });
        assert manager.getStories().getAmount() == 3;
    }

    @Test
    void testCreateTask() throws Exception {
        assert manager.getTasks().getAmount() == 0;
        addSomeTasks();
        assert manager.getTasks().getAmount() == 3;
        assert manager.taskIDExists(1);
        assert manager.taskIDExists(0);
        assert manager.taskIDExists(-10);

        assertThrows(UserStoryException.class, () -> { manager.createTask(-10, "bla bla"); });
        assert manager.getTasks().getAmount() == 3;
    }

    @Test
    void testStories() throws Exception {
        assertEquals("Keine User Stories vorhanden.", manager.getStories().toString());
        addSomeStories();
        assertEquals("Die folgenden User Stories sind im System gespeichert:\n" +
                "ID: 1, Beschreibung: user story beschreibung.,ßäüö#!?^%\n/{} _-|`~\\\t<[\"']>, Priorität: must\n" +
                "\tKeine Tasks zugeordnet.\n" +
                "ID: 0, Beschreibung: , Priorität: could\n" +
                "\tKeine Tasks zugeordnet.\n" +
                "ID: -5, Beschreibung: null, Priorität: won_t\n" +
                "\tKeine Tasks zugeordnet.\n", manager.getStories().toString());
    }

    @Test
    void testTasks() throws Exception {
        assertEquals("Keine Tasks vorhanden.", manager.getTasks().toString());
        addSomeTasks();
        assertEquals("Die folgenden Tasks sind im System gespeichert:\n" +
                "ID: 1, Beschreibung: task beschreibung.,ßäüö#!?^%\n/{} _-|`~\\\t<[\"']>\n" +
                "ID: 0, Beschreibung: \n" +
                "ID: -10, Beschreibung: null\n", manager.getTasks().toString());
    }

    @Test
    void testAssign() throws Exception {
        assertThrows(UserStoryException.class, () -> { manager.assign(1, 1); });
        addSomeStories();
        assertThrows(UserStoryException.class, () -> { manager.assign(1, 1); });
        addSomeTasks();
        assertEquals("Task 1 wurde erfolgreich zu User Story 1 zugewiesen", manager.assign(1, 1));
        assertThrows(UserStoryException.class, () -> { manager.assign(1, 1); });
        assertEquals("Task -10 wurde erfolgreich zu User Story 0 zugewiesen", manager.assign(0, -10));
        assertEquals("Task 0 wurde erfolgreich zu User Story -5 zugewiesen", manager.assign(-5, 0));
        assertThrows(UserStoryException.class, () -> { manager.assign(3, 1); });
        assertThrows(UserStoryException.class, () -> { manager.assign(1, 3); });
    }

    @Test
    @Tag(SAVE_FILE_TEST)
    void testSaveAndLoad() throws Exception {
        addSomeStories();
        addSomeTasks();

        assertEquals("User Stories und Tasks wurden erfolgreich gespeichert.", manager.save());
        assert manager.getStories().getAmount() == 3;
        assert manager.getTasks().getAmount() == 3;

        File file = new File(manager.getSaveFilePath());
        assert file.exists();

        manager = new UserStoryManager();
        manager.SetSaveFilePath(TEST_SAVE_FILE_PATH);
        assert manager.getStories().getAmount() == 0;
        assert manager.getTasks().getAmount() == 0;
        assert !manager.userStoryIDExists(1);
        assert !manager.taskIDExists(1);

        assertEquals("User Stories und Tasks wurden erfolgreich geladen.", manager.load());
        assert manager.getStories().getAmount() == 3;
        assert manager.getTasks().getAmount() == 3;
        assert manager.userStoryIDExists(1);
        assert manager.taskIDExists(1);
    }

    @Test
    @Tag(SAVE_FILE_TEST)
    void testSaveAndLoad_Empty() throws Exception {
        assertEquals("User Stories und Tasks wurden erfolgreich gespeichert.", manager.save());
        assert manager.getStories().getAmount() == 0;
        assert manager.getTasks().getAmount() == 0;

        File file = new File(manager.getSaveFilePath());
        assert file.exists();

        addSomeTasks();
        addSomeStories();
        assert manager.getStories().getAmount() == 3;
        assert manager.getTasks().getAmount() == 3;

        assertEquals("User Stories und Tasks wurden erfolgreich geladen.", manager.load());
        assert manager.getStories().getAmount() == 0;
        assert manager.getTasks().getAmount() == 0;
    }

    @Test
    @Tag(SAVE_FILE_TEST)
    void testLoad_FileCorrupt() throws Exception {
        try (FileWriter writer = new FileWriter(manager.getSaveFilePath())) {
            writer.write("corrupted file");
        }

        UserStoryException exception = assertThrows(UserStoryException.class, () -> manager.load());
        assertEquals("Dateiformat ungültig – Liste von User Stories und Liste von Tasks erwartet", exception.getMessage());
    }

    @Test
    @Tag(SAVE_FILE_TEST)
    void testLoad_FileMissing() throws Exception {
        UserStoryException exception = assertThrows(UserStoryException.class, () -> manager.load());
        assertEquals("Datei nicht gefunden", exception.getMessage());
    }

    private void deleteSaveFileIfExists() throws Exception {
        File file = new File(manager.getSaveFilePath());
        if (file.exists()) {
            if (!file.delete()) {
                throw new Exception("failed to delete save file!");
            }
        }
    }
}