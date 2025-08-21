import java.util.ArrayList;
import java.util.List;

public class ChatRoomMediator implements ChatMediator {
    private List<Participant> participants;

    public ChatRoomMediator() {
        participants = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, Participant sender) {
        for (Participant participant : participants) {
            // Message should not be received by the sender
            if (participant != sender) {
                participant.receive(message, sender);
            }
        }
    }

    @Override
    public void addUser(Participant participant) {
        participants.add(participant);
    }
}
