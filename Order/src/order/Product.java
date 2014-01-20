package order;

public class Product {

    private String itemCode;
    private String description;
    private double price;
    private boolean taxable;

    public Product(String itemCode, String description, double price, boolean taxable) {
        this.itemCode = itemCode;
        this.description = description;
        this.price = price;
        this.taxable = taxable;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getProduct() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean getTaxable() {
        return taxable;
    }

    @Override
    public String toString() {
        String item = "Code:\t\t" + itemCode
                + "\nDescription:\t" + description
                + "\nPrice:\t\t" + price + System.lineSeparator();
        return item;
    }
}
