import userstories.UserStoryClient;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserStoryClient client = new UserStoryClient();

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            client.processInput(input);
        }
    }
}
