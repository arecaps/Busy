
package order;

import java.util.ArrayList;
import java.util.Scanner;

public class GetItem {
    
    public LineItem order;
    public static ArrayList<LineItem> cart = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    public GetItem(){
        System.out.println("Enter the code of the product you want to order,\n"
                + "If you dont know the product code, press any key and Enter to return to the main menu.");
        String choice = sc.next();
        for (Product item : Inventory.stock) {
            if (choice.equals(item.getItemCode())) {
                order = new LineItem(item.getProduct(), item.getPrice(), item.getTaxable());
                cart.add(order);
            }
        }
    }}