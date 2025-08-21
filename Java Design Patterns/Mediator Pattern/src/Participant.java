public abstract class Participant {
    protected ChatMediator mediator;
    protected String name;

    public Participant(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.addUser(this);
    }

    public abstract void send(String message);
    public abstract void receive(String message, Participant sender);
}
