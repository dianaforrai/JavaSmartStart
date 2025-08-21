public class GroupChat extends Participant {

    public GroupChat(String name, ChatMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void send(String message) {
        System.out.println("Group \"" + this.name + "\" sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, Participant sender) {
        System.out.println("Group \"" + this.name + "\" received from " + sender.name + ": " + message);
    }
}
