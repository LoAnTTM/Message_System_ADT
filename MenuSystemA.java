import java.util.Scanner;

public class MenuSystemA extends MenuSystem implements IMainMenu {
//    MessageSystem systemA = new MessageSystem();
//    MessageSystem systemB = new MessageSystem();
    Scanner input = new Scanner(System.in);

    public MenuSystemA(MessageSystem systemA, MessageSystem systemB) {
        super(systemA, systemB);
    }

    @Override
    public void ShowMenu() {
        System.out.println("\n===== MENU SYSTEM A =====");
        System.out.println("|| 1. Connect to System B");
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
            input.nextLine(); // Consume the newline character

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
        systemA.connectTo(systemB);
        System.out.println("Connected to System B");
    }

    @Override
    public void checkConnection() {
        boolean connected = systemA.isConnected();
        System.out.println("Is the system connected? " + connected);
    }

    @Override
    public void disconnectTo() {
        systemA.disconnect();
    }

    @Override
    public void sendMessage() {
        System.out.print("Enter the message to send: ");
        String messageContent = input.nextLine();
        systemA.sendMessage(messageContent);
    }

    @Override
    public void receiveMessages() {
        systemA.receiveMessages();
    }

    @Override
    public void processMessages() {
        systemA.processMessages();
    }

    @Override
    public void outgoingMessages() {
        systemA.readOutgoingMessages();
    }

    @Override
    public void incomingMessages() {
        systemA.readIncomingMessages();
    }

    @Override
    public void checkMessages() {
       systemA.checkMessages();
    }
}
