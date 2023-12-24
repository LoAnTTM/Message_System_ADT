import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MessageSystem {
    private Queue<Message> inboxQueue;
    private Queue<Message> outboxQueue;
    private Stack<Message> processingStack;
    private MessageSystem connectedTo;

    public MessageSystem() {
        inboxQueue = new Queue<>();
        outboxQueue = new Queue<>();
        processingStack = new Stack<>();
        connectedTo = null;
    }

    // Method to connect to another system 
    public void connectTo(MessageSystem otherSystem) {
        // Check if the other system is not null and is not the same as this system
        if (otherSystem == null || otherSystem == this) {
            System.out.println("Connection failed. Invalid system specified.");
            return;
        }
        // Check if both systems are currently not connected to any other system
        if (this.connectedTo != null) {
            System.out.println("Connection failed. This systems are already connected.");
            return;
        }
        // Establish the connection
        this.connectedTo = otherSystem;
        otherSystem.connectedTo = this;
        System.out.println("Connected successfully to " + otherSystem);
    }
    // Method to check if the system is connected
    public boolean isConnected( ) {
        return this.connectedTo != null;
    }

    public void disconnect() {
        // Check if all queues and stacks are empty in both systems
        if (!this.isConnected() && !this.connectedTo.isConnected()){
            System.out.println("Error: Not connected to any system. Cannot disconnect.");
            return;
        }
        if(this.inboxQueue.isEmpty() && this.outboxQueue.isEmpty() && this.processingStack.isEmpty()
                && this.connectedTo.inboxQueue.isEmpty() && this.connectedTo.outboxQueue.isEmpty() && this.connectedTo.processingStack.isEmpty()){
            // Disconnect both systems
            this.connectedTo.connectedTo = null;
            this.connectedTo = null;
            System.out.println("Disconnected to other system.");
        } else {
            System.out.println("Reseting system...");
            this.inboxQueue.reset();
            this.outboxQueue.reset();
            this.processingStack.reset();
            this.connectedTo.inboxQueue.reset();
            this.connectedTo.outboxQueue.reset();
            this.connectedTo.processingStack.reset();
            this.connectedTo.connectedTo = null;
            this.connectedTo = null;
            System.out.println("Disconnected to other system.");
        }
    }

    // Method to send messages from the current system to another system
    public void sendMessage(String messageContent) {
        if (this.connectedTo == null) {
            System.out.println("Error: Not connected to any system. Cannot send message.");
            return;
        }

        if (messageContent.length() == 0) {
            System.out.println("Error: Message length is 0. Cannot send an empty message.");
            return;
        }

        if (messageContent.length() > 250) {
            System.out.println("Warning: Message length exceeds 250 characters. Truncating...");
            // Truncate the message into smaller messages with a maximum length of 250 characters
            while (messageContent.length() > 250) {
                String truncatedMessage = messageContent.substring(0, 250);
                this.outboxQueue.offer(new Message(truncatedMessage));
                messageContent = messageContent.substring(250);
            }
        }
        // Send message
        this.outboxQueue.offer(new Message(messageContent));
        System.out.println("Message sent successfully!");
    }

    // Method to receive messages from another system and store them in inboxQueue
    public void receiveMessages() {
        if (this.connectedTo == null) {
            System.out.println("Error: Not connected to any system. Cannot receive messages.");
            return;
        }
        // Request messages from the connected system and check if there are messages in this outboxQueue
        try {
            if (!this.connectedTo.outboxQueue.isEmpty()) {
                while (!this.connectedTo.outboxQueue.isEmpty()) {
                    this.inboxQueue.offer(this.connectedTo.outboxQueue.poll());
                    System.out.println("Messages received successfully!");
                }
            } else {
                System.out.println("System's outboxQueue is empty.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: Unable to receive messages. The connected system's outboxQueue is empty.");
        }
    }

    // Method to read and report outgoing messages in the outboxQueue
    public void readOutgoingMessages() {
        if (this.outboxQueue.isEmpty()) {
            System.out.println("OutboxQueue is empty. No outgoing messages.");
        } else {
            System.out.print("Outgoing Messages:");
            this.outboxQueue.print();
        }
    }

    // Method to read and report incoming messages in the inboxQueue
    public void readIncomingMessages() {
        if (this.inboxQueue.isEmpty()) {
            System.out.println("InboxQueue is empty. No incoming messages.");
        } else {
            System.out.print("Incoming Messages:");
            this.inboxQueue.print();
        }
    }

    // Method to move messages from inboxQueue to processingStack for processing
    public void processMessages() {
        if (inboxQueue.isEmpty()) {
            System.out.println("InboxQueue is empty. No messages to process.");
        } else {
            System.out.print("Processing Messages:");
            while (!inboxQueue.isEmpty()) {
                processingStack.push(inboxQueue.poll());
            }
            System.out.println("Messages moved to the processingStack.");
        }
    }

    // Method check messages to read all the message in stack and clear stack
    public void checkMessages() {
        if (processingStack.isEmpty()) {
            System.out.println("ProcessingStack is empty. No messages to read.");
        } else {
            System.out.print("Check message: ");
            while (!processingStack.isEmpty()) {
                Message message = processingStack.pop();
                System.out.println(message.getContent());
            }
        }
    }
}
