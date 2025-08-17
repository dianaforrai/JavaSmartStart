import java.util.List;

public class Exercise2 {

    public static String toJson(List<Person> people) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            json.append("""
                    {
                        "name": "%s",
                        "age": %s
                    }""".formatted(p.getName(),
                    p.getAge() == null ? "null" : p.getAge()));
            if (i < people.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", null),
                new Person("David", 35)
        );

        String jsonOutput = toJson(people);
        System.out.println(jsonOutput);
    }
}
