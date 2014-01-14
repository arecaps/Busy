package order;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {

    public static Inventory inventory;
    public static GetOrder order;
    public static int quantity;
    public static ArrayList<GetOrder> cart = new ArrayList<>();
    public static final double SALES_TAX = .07;

    public static void getAnOrder() {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        System.out.println("Enter the code of the product you want to order,\n"
                + "If you dont know the product code, press any key and Enter to return to the main menu.");
        String choice = sc.next();
        switch (choice.toLowerCase()) {
            case "fzslp":
                break;
            case "frem":
                i =1;
                break;
            case "fzbl":
                i = 2;
                break;
            case "frrg":
                i = 3;
                break;
            default:
                return;
        }
        order = new GetOrder(inventory.stock.get(i).getProduct(), inventory.stock.get(i).getPrice(), inventory.stock.get(i).getTaxable());
        cart.add(order);
    }

    public static void checkOut() {
        double orderTotal = 0;
        String lineItem;
        double tax = 0;
        System.out.print("Quantity:\tProduct:\t\tPrice each:\tItem subtotal:\tTaxable:\n");
        for (GetOrder item : cart) {
            orderTotal += (item.price * item.quantity);
            lineItem = item.quantity + "\t\t" + item.product + "\t\t" + item.price + "\t\t" + (item.price * item.quantity) + "\t\t";
            System.out.print(lineItem);
            if (item.taxable) {
                System.out.print("Yes\n");
            } else {
                System.out.print("No\n");
            }
        }

        System.out.println("Your subtotal is $" + orderTotal);
        for (GetOrder item : cart) {
            if (item.taxable) {
                tax += ((item.price * item.quantity) * SALES_TAX);
            }
        }
        System.out.println("Your tax is $" + tax);
        System.out.println("Your total is $" + (orderTotal + tax));
    }

    public static void main(String[] args) {
        inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Fuzzy Furry Store!");
        while (true) {
            if (cart.isEmpty()) {
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
                    getAnOrder();
                    break;
                case "C":
                    checkOut();
                    System.exit(0);
                    break;
            }
        }

    }
}
