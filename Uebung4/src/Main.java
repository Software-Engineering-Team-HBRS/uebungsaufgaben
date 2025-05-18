import UserStories.UserStoryManager;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String input = sc.nextLine().trim();
            UserStoryManager.processInput(input);
        }
    }
}
