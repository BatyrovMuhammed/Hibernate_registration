package org.example;

import org.example.models.Client;
import org.example.services.ClientService;
import org.hibernate.internal.util.io.StreamCopier;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {

        ClientService clientService = new ClientService();
        Scanner scanner = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    press r to register
                    press f to show all client
                    press l to log in
                    """);
            char operation = sc.next().charAt(0);
            switch (operation) {
                case 'r' -> {
                    System.out.println("first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("email: ");
                    String email = scanner.nextLine();
                    System.out.println("phoneNumber: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("password: ");
                    String password = scanner.nextLine();
                    clientService.register(new Client(firstName, lastName, email, phoneNumber, password));
                }
                case 'f' -> clientService.findAll().forEach(System.out::println);
                case 'l' -> {
                    System.out.println("email");
                    String email = scanner.nextLine();
                    System.out.println("password");
                    String password = scanner.nextLine();
                    try {
                        clientService.login(email, password);
                        System.out.println("SUCCESSFULLY!!!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> throw new IllegalArgumentException(
                        "you pressed wrong letter"
                );
            }
        }
    }
}
