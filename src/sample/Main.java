package sample;
import java.util.Scanner;

public class Main {
    public static void main(String[] arga) {
        Scanner user = new Scanner(System.in);
        System.out.println("Welcome to Space Ship Sim! \n\nplease select a difficulty. \n\nEasy \nMedium \nHard\n");
        String input = user.nextLine();
        SpaceStation current = new SpaceStation(input);
        while (current.getNewGame()){
            System.out.println("\nInvalid input. \n\nplease select a difficulty. \n\nEasy \nMedium \nHard\n");
            input = user.nextLine();
            current = new SpaceStation(input);
        }
        Menu menu = new Menu(current);
        while (current.getModules() > 0) {
            current.setManagingTrue();
            System.out.println("\n" + menu.update(current) + "\n");
            while(current.getManaging()) {
                menu = new Menu(current);
                input = user.nextLine();
                System.out.println("\n" + current.processInput(input, menu) + "\n");
                menu.update(current);
            }

        }
        if (!input.toLowerCase().equals("quit")){
            System.out.println("\nYour Ship has Exploded.");
            current.processInput("quit", menu);
        }
    }
}