package com.zoo.app;

import com.zoo.animals.Animal;
import com.zoo.animals.Elephant;
import com.zoo.animals.Giraffe;
import com.zoo.animals.Lion;
import com.zoo.feeding.FeedingService;

public class ZooApplication {

    public static void main(String[] args) {
        System.out.println("🦁 Welcome to the Modular Zoo Management System! 🦁");
        System.out.println("=".repeat(55));
        System.out.println();


        System.out.println("🏗️  Creating zoo animals...");
        Lion lion = new Lion("Simba");
        Elephant elephant = new Elephant("Dumbo");
        Giraffe giraffe = new Giraffe("Gerald");

        Lion lioness = new Lion("Nala");
        Elephant babyElephant = new Elephant("Ellie");

        System.out.printf("✅ Created: %s%n", lion);
        System.out.printf("✅ Created: %s%n", elephant);
        System.out.printf("✅ Created: %s%n", giraffe);
        System.out.printf("✅ Created: %s%n", lioness);
        System.out.printf("✅ Created: %s%n", babyElephant);
        System.out.println();

        System.out.println("🏗️  Initializing feeding service...");
        FeedingService feedingService = new FeedingService();
        System.out.println("✅ Feeding service ready!");
        System.out.println();

        System.out.println("🔊 Let's hear what our animals sound like:");
        System.out.println("-".repeat(40));
        demonstrateAnimalSounds(lion, elephant, giraffe, lioness, babyElephant);
        System.out.println();

        System.out.println("🍽️  Individual feeding demonstration:");
        System.out.println("-".repeat(40));
        feedingService.feed(lion);
        feedingService.feed(elephant);
        feedingService.feed(giraffe);
        System.out.println();

        feedingService.feedAll(lion, elephant, giraffe, lioness, babyElephant);

        System.out.println("📅 Scheduled feeding demonstration:");
        System.out.println("-".repeat(40));
        feedingService.performFeedingSchedule(lioness, babyElephant, giraffe);

        System.out.println();
        System.out.println("🎉 Zoo operations completed successfully!");
        System.out.println("Thank you for visiting our modular zoo! 🦁🐘🦒");
    }


    private static void demonstrateAnimalSounds(Lion lion, Elephant elephant, Giraffe giraffe, Lion lioness, Elephant babyElephant, Animal... animals) {
        for (Animal animal : animals) {
            System.out.printf("🦁 %s says: '%s'%n", animal, animal.sound());
        }
    }
}