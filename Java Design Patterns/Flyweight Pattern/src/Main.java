import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

// Enum for Text Style
enum TextStyle {
    FONT, SIZE, COLOR
}

// Flyweight Interface
interface TextCharacter {
    void render(Point position);
    void edit(String content);
}

// Concrete Flyweight
class TextCharacterImpl implements TextCharacter {
    private final String character; // intrinsic state
    private TextStyle style;        // extrinsic state can vary

    public TextCharacterImpl(String character, TextStyle style) {
        this.character = character;
        this.style = style;
    }

    @Override
    public void render(Point position) {
        System.out.println("Rendering character '" + character + "' at " +
                "(" + position.x + "," + position.y + ") with style " + style);
    }

    @Override
    public void edit(String content) {
        System.out.println("Editing character '" + character + "' to content '" + content + "' with style " + style);
    }

    public void setStyle(TextStyle style) {
        this.style = style;
    }
}

// Flyweight Factory
class TextCharacterFactory {
    private final Map<String, TextCharacter> characterPool = new HashMap<>();

    public TextCharacter getCharacter(String character, TextStyle style) {
        // Use character as key; if already exists, return existing instance
        if (!characterPool.containsKey(character)) {
            characterPool.put(character, new TextCharacterImpl(character, style));
        }
        // Return instance (shared)
        return characterPool.get(character);
    }
}

