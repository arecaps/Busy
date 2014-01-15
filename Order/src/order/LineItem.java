
package order;

import java.util.Scanner;

public class LineItem {

    public int quantity;
    String product;
    double price;
    boolean taxable;
            
    
    public LineItem(String product, double price, boolean taxable) {
        this.product = product;
        this.price = price;
        this.taxable = taxable;
        Scanner sc = new Scanner(System.in);
                System.out.print("Please enter the quantity: ");
                quantity = sc.nextInt();
                double itemTotal = quantity*price;
                System.out.println("You added to your cart " + quantity + " " + product
                        +"\nYour subtotal for this item is " + itemTotal);
                
    }
}
