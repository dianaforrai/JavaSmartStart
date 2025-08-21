public class Client {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoomMediator();

        User alice = new User("Alice", mediator);
        User bob = new User("Bob", mediator);
        User charlie = new User("Charlie", mediator);

        GroupChat developers = new GroupChat("Developers", mediator);

        alice.send("Hi everyone!");
        bob.send("Hello Alice!");
        developers.send("Reminder: code review at 3 PM.");
        charlie.send("Got it, thanks!");
    }
}
