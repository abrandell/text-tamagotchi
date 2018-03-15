package dev.ab.tamagotchi;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        Scanner uInput = new Scanner(System.in);

        System.out.print("Enter a name for your new Tamagotchi: ");
        String name = uInput.nextLine().trim();
        System.out.print("Is " + name + " a male or a female? ");
        String sex = uInput.nextLine().toLowerCase().trim();
        System.out.println("");

        while (!sex.equals("male") && !sex.equals("female")) {
            System.out.println("Unknown gender!");
            System.out.print("Try again with the format male/female: ");
            sex = uInput.nextLine().toLowerCase().trim();
            System.out.println("");
        }

        Tamagotchi pet = new Tamagotchi(name, sex);
        pet.start(uInput);

    }
}
