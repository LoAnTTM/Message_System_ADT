import java.util.Scanner;

public class MenuSystemB extends MenuSystem implements IMainMenu {
    Scanner input = new Scanner(System.in);

    public MenuSystemB(MessageSystem systemA, MessageSystem systemB) {
        super(systemA, systemB);
    }

    @Override
    public void ShowMenu() {
        System.out.println("\n===== MENU SYSTEM B =====");
        System.out.println("|| 1. Connect to System A");
        System.out.println("|| 2. Check connection");
        System.out.println("|| 3. Disconnect");
        System.out.println("|| 4. Send Message");
        System.out.println("|| 5. Receive Messages");
        System.out.println("|| 6. Process Messages");
        System.out.println("|| 7. Read Outgoing Messages");
        System.out.println("|| 8. Read Incoming Messages");
        System.out.println("|| 9. Check Messages");
        System.out.println("|| 10. Back to Main Menu");
        System.out.println("=========================\n");
    }
    @Override
    public void SelectMenu() {
        int choice;
        do {
            ShowMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    connectTo();
                    break;
                case 2:
                    checkConnection();
                    break;
                case 3:
                    disconnectTo();
                    break;
                case 4:
                    sendMessage();
                    break;
                case 5:
                    receiveMessages();
                    break;
                case 6:
                    processMessages();
                    break;
                case 7:
                    outgoingMessages();
                    break;
                case 8:
                    incomingMessages();
                    break;
                case 9:
                    checkMessages();
                    break;
                case 10:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);
    }

    @Override
    public void connectTo() {
        systemB.connectTo(this.systemA);
        System.out.println("Connected to System A");
    }

    @Override
    public void checkConnection() {
        boolean connected = systemB.isConnected();
        System.out.println("Is the system connected? " + connected);
    }

    @Override
    public void disconnectTo() {
        systemB.disconnect();
    }

    @Override
    public void sendMessage() {
        System.out.print("Enter the message to send: ");
        String messageContent = input.nextLine();
        systemB.sendMessage(messageContent);
    }

    @Override
    public void receiveMessages() {
        systemB.receiveMessages();
    }

    @Override
    public void processMessages() {
        systemB.processMessages();
    }

    @Override
    public void outgoingMessages() {
        systemB.readOutgoingMessages();
    }

    @Override
    public void incomingMessages() {
        systemB.readIncomingMessages();
    }

    @Override
    public void checkMessages() {
        systemB.checkMessages();
    }
}
