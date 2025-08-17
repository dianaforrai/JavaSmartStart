import java.util.*;

class Person {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public Integer getAge() { return age; }
}

public class Exercise1 {
    public static Optional<String> getOldestPersonName(List<Person> people) {
        return people.stream()
                .filter(p -> p.getAge() != null)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .findFirst();
    }
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", null),
            new Person("David", 35)
        );

        Optional<String> oldestName = getOldestPersonName(people);
        oldestName.ifPresent(name -> System.out.println("Oldest person: " + name));
    }
}
