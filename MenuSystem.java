abstract class MenuSystem {
//    MessageSystem systemB = new MessageSystem();
    MessageSystem systemB = null;
//    MessageSystem systemA = new MessageSystem();
    MessageSystem systemA = null;

    public MenuSystem(MessageSystem systemA, MessageSystem systemB) {
        this.systemA = systemA;
        this.systemB = systemB;
    }
    abstract void connectTo();
    abstract void checkConnection();
    abstract void disconnectTo();
    abstract void sendMessage();
    abstract void receiveMessages();
    abstract void processMessages();
    abstract void outgoingMessages();
    abstract void incomingMessages();
    abstract void checkMessages();
}
