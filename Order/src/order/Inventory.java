
package order;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<Product> stock = new ArrayList<>();

    //put items into the inventory
    public Inventory() {
        Product slippers = new Product("fzslp", "Fuzzy Slippers", 19.99, false);
        stock.add(slippers);
        Product hat = new Product("frem", "Furry Ear Muffs", 11.79, false);
        stock.add(hat);
        Product earmuffs = new Product("fzbl", "Fuzzy Blankets", 8.49, true);
        stock.add(earmuffs);
        Product rug = new Product("frrg", "Furry Rugs", 28.49, true);
        stock.add(rug);
}
    public void displayInventory (){  
        for(Product product : stock){
            System.out.println(product.toString());
        }
    }
}
