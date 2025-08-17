public sealed class Animal permits Lion, Elephant, UnknownAnimal {
    public String sound() {
        return "Unknown";
    }
}
