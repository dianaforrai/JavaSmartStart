import java.util.ArrayList;
import java.util.List;

public class RemoteControlInvoker {
    private List<Command> commandHistory = new ArrayList<>();

    public void setCommand(Command command) {
        commandHistory.add(command);
    }

    public void pressButton() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.get(commandHistory.size() - 1);
            command.execute();
        }
    }

    public void pressAllButtons() {
        for (Command command : commandHistory) {
            command.execute();
        }
    }
}
