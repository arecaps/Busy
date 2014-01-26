
package diet;

public class Food implements Comparable<Food>{
    private String name;
    private int calories;

    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    @Override
    public int compareTo(Food f){
        if(this == f)
            return 0;
        if(f == null)
            return 1;
        if(calories < f.getCalories())
                return -1;
        if(calories == f.getCalories())
                return 0;
        else
            return 1;
        
    }
}
