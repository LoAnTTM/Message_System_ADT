import java.util.Scanner;

public class MainMenu implements IMainMenu{
    Scanner scanner = new Scanner(System.in);
    MessageSystem systemB = new MessageSystem();
    MessageSystem systemA = new MessageSystem();
    MenuSystemA menuSystemA = new MenuSystemA(systemA, systemB);
    MenuSystemB menuSystemB = new MenuSystemB(systemA, systemB);

    public void ShowMenu() {
        System.out.println("\n===MENU MESSAGE SYSTEM=== ");
        System.out.println("1. System A");
        System.out.println("2. System B");
        System.out.println("3. Exit");
    }

    public void SelectMenu() {
        int choice;
        do {
            ShowMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
//                    menuSystemA.ShowMenu();
                    menuSystemA.SelectMenu();
                    break;
                case 2:
//                    menuSystemB.ShowMenu();
                    menuSystemB.SelectMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.SelectMenu();
    }
}
