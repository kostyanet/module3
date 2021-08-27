package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CLIService {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private AppService appService = null;

    private static void showMenu() {
        System.out.println("Available operations:");
        System.out.println("0 -- exit");
        System.out.println("1 -- find SMS by text");
        System.out.println("2 -- find top 5 customers");
        System.out.println("3 -- find most profitable service");
        System.out.println("4 -- find most popular device");
    }

    public CLIService(AppService appService) {
        this.appService = appService;
    }

    public void run() {
        showMenu();
        listenInput();
    }

    private void listenInput() {
        while (true) {
            try {
                handleInput(reader.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleInput(String input) {
        switch (input) {
            case "0": System.exit(0);
            case "1":
                findSmsByText();
                showMenu();
                break;
            case "2":
                appService.printTopConsumers();
                showMenu();
                break;
            case "3":
                appService.printMostProfitableService();
                showMenu();
                break;
            case "4":
                appService.printMostPopularDevice();
                showMenu();
                break;

            default:
                throw new IllegalArgumentException("Unsupported command.");
        }
    }

    private void findSmsByText() {
        System.out.println("Please, enter text: ");
        try {
            appService.printMessagesByText(reader.readLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
