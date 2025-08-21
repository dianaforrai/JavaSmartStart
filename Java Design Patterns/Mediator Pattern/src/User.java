public class User extends Participant {

    public User(String name, ChatMediator mediator) {
        super(name, mediator);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, Participant sender) {
        System.out.println(this.name + " received from " + sender.name + ": " + message);
    }
}
