import java.awt.*;

// Client Program
public class FlyweightPatternDemo {
    public static void main(String[] args) {
        TextCharacterFactory factory = new TextCharacterFactory();

        // Create/render some characters
        TextCharacter c1 = factory.getCharacter("A", TextStyle.FONT);
        TextCharacter c2 = factory.getCharacter("B", TextStyle.SIZE);
        TextCharacter c3 = factory.getCharacter("A", TextStyle.COLOR); // reused 'A'

        // Render characters at different positions
        c1.render(new Point(0, 0));
        c2.render(new Point(1, 0));
        c3.render(new Point(2, 0));

        // Edit characters
        c1.edit("A1");
        c2.edit("B1");
        c3.edit("A2"); // 'A' reused
    }
}
