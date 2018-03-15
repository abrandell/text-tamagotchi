package dev.ab.tamagotchi;

import java.util.Random;
import java.util.Scanner;

public class Tamagotchi {

    private int age = 0;
    private int hunger = 50;
    private int happiness = 50;
    private int energy = 50;
    private boolean sick = false;
    private String name;
    private String sex;
    private int daysPassedSick;

    Tamagotchi(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public void start(Scanner uInput) {
        System.out.println(name + " was born! It's a " + sex + "!");

        while (age <= 100) {
            if (daysPassedSick == 5) {
                System.out.println(name + " has died from a sickness.");
                break;
            }
            //energy lowers whenever a user enters a command, also show alerts (ie Depression, starvation, sickness, etc.
            lowerEnergy();
            showAlerts();

            System.out.println("Type 'help' to view available commands.");
            System.out.print("\nEnter a command: ");
            String userCommand = uInput.nextLine();
            commands(userCommand);

            if (age == 100) {
                System.out.println(name + " has died of old age.");
                break;
            }

            if (happiness == 0) {
                System.out.println(name + "has committed suicide.");
                break;
            }
        }
        System.out.println("Goodbye, thanks for playing!");
    }

    private void feed() {
        if (hunger >= 25) {
            hunger -= 25;
            System.out.println("You have fed " + name + ".");
        } else {
            System.out.println(name + " isn't hungry.");
        }
    }

    private void play() {
        if (happiness <= 75) {
            happiness += 25;
            System.out.println("You played with " + name + ".");
        } else {
            System.out.println(sexPronoun() + " doesn't wanna play anymore.");
        }
    }

    private void giveMedicine() {
        if (sick) {
            sick = false;
            System.out.println("You gave " + name + " medicine.");
            daysPassedSick = 0;
        } else {
            System.out.println(name + " isn't sick!");
        }
    }

    private void sleep() {
        if (sick) {
            daysPassedSick++;
        }

        if (energy <= 50) {
            energy = 100;
            age++;
            System.out.println(name + " had a good nights sleep. " + sexPronoun() + " is now older by one day.");

            /* Status changes after new day/age. */
            raiseHunger();
            happinessChance();
            sickChance();
        } else {
            System.out.println(sexPronoun() + " isn't tired!");
        }

    }

    private void sickChance() {
        Random chanceOfSickness = new Random();

        if (chanceOfSickness.nextBoolean()) {
            sick = true;
        }
    }

    private void raiseHunger() {
        if (hunger <= 75) {
            hunger += 25;
        }
    }

    private void happinessChance() {
        Random happyLowChance = new Random();
        if (happyLowChance.nextBoolean()) {
            happiness -= 25;
        }
    }

    private void lowerEnergy() {
        energy -= 5;
    }

    private String sexPronoun() {
        if (sex.equalsIgnoreCase("male")) {
            return "He";
        } else {
            return "She";
        }
    }

    private void showAlerts() {
        if (hunger >= 75) {
            System.out.println(name + " is starving!");
        }
        if (energy <= 25) {
            System.out.println(name + " is exhausted!");
        }
        if (happiness <= 25) {
            System.out.println(name + " is depressed!");
        }
        if (sick) {
            System.out.println(name + " is sick!");
        }
    }

    private void getStats() {
        String healthStatus;
        if (sick) {
            healthStatus = "Sick";
        } else {
            healthStatus = "Healthy";
        }

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Happiness " + happiness);
        System.out.println("Hunger: " + hunger);
        System.out.println("Energy: " + energy);
        System.out.println("Health: " + healthStatus);
        System.out.println("");
    }

    private void showCommandList() {
        System.out.println("---Available commands---");
        System.out.println("1. 'Stats'");
        System.out.println("2. 'Feed'");
        System.out.println("3. 'Play'");
        System.out.println("4. 'Medicine'");
        System.out.println("5. 'Sleep'");
        System.out.println("6. 'Quit'");
        System.out.println("");
    }

    private void commands(String command) {
        System.out.println("");
        switch (command.toLowerCase().trim()) {
            case "help":
                showCommandList();
                break;
            case "stats":
                getStats();
                break;
            case "feed":
                feed();
                break;
            case "play":
                play();
                break;
            case "medicine":
                giveMedicine();
                break;
            case "sleep":
                sleep();
                break;
            case "quit":
                System.out.println("Goodbye, thanks for playing!");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command! Type help to view available commands.");
                break;
        }

    }
}

