package order;

import java.util.Scanner;

public class Order {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Fuzzy Furry Store!");
        while (true) {
            if (GetItem.cart.isEmpty()) {
                System.out.println("Enter I to view inventory, O to order, or Q to quit.");
            } else {
                System.out.println("Enter I to view inventory, O to order another product, C to checkout, or Q to quit.");
            }
            String choice = sc.next();
            switch (choice.toUpperCase()) {
                case "I":
                    inventory.displayInventory();
                    break;
                case "Q":
                    System.exit(0);
                    break;
                case "O":
                    GetItem.getItem();
                    break;
                case "C":
                    Checkout.checkOut();
                    System.exit(0);
                    break;
            }
        }

    }
}
