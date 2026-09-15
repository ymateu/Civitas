package com.ymateu.civitas;

import com.ymateu.civitas.engine.GameState;
import com.ymateu.civitas.events.Event;
import com.ymateu.civitas.events.EventChoice;
import com.ymateu.civitas.events.EventManager;
import com.ymateu.civitas.government.President;
import com.ymateu.civitas.society.Society;

import java.util.Scanner;

public class CivitasApp {

    public static void main(String[] args) {

        GameState state = new GameState();

        President president = new President();
        president.setPopularity(50);

        Society society = new Society();
        society.setGovernmentApproval(20);

        state.setPresident(president);
        state.setSociety(society);

        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            clearScreen();

            System.out.println("===== CIVITAS =====");
            System.out.println("Popularidade: " + state.getPresident().getPopularity());
            System.out.println("Aprovacao do governo: " + state.getSociety().getGovernmentApproval());
            System.out.println();
            System.out.println("1 - Iniciar evento");
            System.out.println("2 - Aumentar aprovacao");
            System.out.println("3 - Diminuir aprovacao");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            String input = scanner.nextLine();

            switch (input) {

                case "1":
                    clearScreen();

                    Event event = eventManager.generateEvent(state);

                    if (event == null) {
                        System.out.println("Nenhum evento pode ocorrer neste momento.");
                        break;
                    }

                    System.out.println("===== EVENTO =====");
                    System.out.println(event.getTitle());
                    System.out.println(event.getDescription());
                    System.out.println();

                    EventChoice[] choices = event.getChoices();

                    for (int i = 0; i < choices.length; i++) {
                        System.out.println((i + 1) + " - " + choices[i].getDescription());
                    }

                    System.out.print("Escolha: ");

                    try {
                        int choice = Integer.parseInt(scanner.nextLine());

                        if (choice >= 1 && choice <= choices.length) {
                            choices[choice - 1].execute(state);
                        } else {
                            System.out.println("Escolha invalida.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Digite um numero valido.");
                    }

                    break;

                case "2":
                    state.getSociety().setGovernmentApproval(
                            state.getSociety().getGovernmentApproval() + 10
                    );
                    break;

                case "3":
                    state.getSociety().setGovernmentApproval(
                            state.getSociety().getGovernmentApproval() - 10
                    );
                    break;

                case "4":
                    running = false;
                    clearScreen();
                    System.out.println("Saindo do Civitas...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

        scanner.close();
    }

    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}