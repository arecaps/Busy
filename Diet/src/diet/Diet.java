
package diet;

import java.util.ArrayList;
import static java.util.Collections.sort;

public class Diet {
 
    public static void main(String[] args) {
         
        ArrayList<Food> snacks = new ArrayList<>();
        
        Food food = new Food("Apple", 65);
        snacks.add(food);
        food = new Food("Cake", 175);
        snacks.add(food);
        food = new Food("Chips", 140);
        snacks.add(food);
        food = new Food("Tofu", 20);
        snacks.add(food);
        
        for(Food snack : snacks)
            System.out.println(snack.getName());
        System.out.println();
        
        sort(snacks);
        for(Food snack : snacks)
            System.out.println(snack.getName());
    }
    
}
