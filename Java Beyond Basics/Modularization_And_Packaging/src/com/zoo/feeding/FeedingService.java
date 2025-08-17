package com.zoo.feeding;

import com.zoo.animals.Animal;

public class FeedingService {


    public void feed(Animal animal) {
        System.out.printf("Feeding %s... %s says '%s' while eating!%n",
                animal.toString(), animal.getName(), animal.sound());
    }


    public void feedAll(Animal... animals) {
        System.out.println("=== Feeding Time at the Zoo ===");
        for (Animal animal : animals) {
            feed(animal);
        }
        System.out.println("=== All animals have been fed ===\n");
    }


    public void performFeedingSchedule(Animal... animals) {
        System.out.println("🍽️  Starting daily feeding schedule...");
        System.out.println("📋 Animals to feed: " + animals.length);
        System.out.println();

        for (int i = 0; i < animals.length; i++) {
            Animal animal = animals[i];
            System.out.printf("⏰ Feeding session %d/%d:%n", i + 1, animals.length);
            System.out.printf("🦁 Approaching %s's enclosure...%n", animal.getName());

            System.out.printf("🔊 %s makes a sound: '%s'%n", animal.getName(), animal.sound());

            feed(animal);

            if (i < animals.length - 1) {
                System.out.println("   Moving to next enclosure...\n");
            }
        }

        System.out.println("✅ Daily feeding schedule completed successfully!");
    }
}
