package order;

import java.text.NumberFormat;

public class Checkout {

    public static final double SALES_TAX = .07;

    public static void checkOut() {
        double orderTotal = 0;
        String lineItem;
        double tax = 0;
        NumberFormat cash = NumberFormat.getCurrencyInstance();
        System.out.print("Quantity:\tProduct:\t\tPrice each:\tItem subtotal:\tTaxable:\n");
        for (LineItem item : GetItem.cart) {
            orderTotal += (item.price * item.quantity);
            lineItem = item.quantity + "\t\t" + item.product + "\t\t$" + item.price
                    + "\t\t" + cash.format(item.price * item.quantity) + "\t\t";
            System.out.print(lineItem);
            if (item.taxable) {
                System.out.print("Yes\n");
            } else {
                System.out.print("No\n");
            }
        }

        System.out.println("Your subtotal is " + cash.format(orderTotal));
        for (LineItem item : GetItem.cart) {
            if (item.taxable) {
                tax += ((item.price * item.quantity) * SALES_TAX);
            }
        }
        System.out.println("Your tax is " + cash.format(tax));
        System.out.println("Your total is " + cash.format(orderTotal + tax));
    }
}
