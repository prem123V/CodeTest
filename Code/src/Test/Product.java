package Test;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double unitPrice;
    private double gstPercentage;
    private int quantity;

    public Product(String name, double unitPrice, double gstPercentage, int quantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.gstPercentage = gstPercentage;
        this.quantity = quantity;
    }

    public double calculateTotalPrice() {
        return unitPrice * quantity;
    }

    public double calculateGstAmount() {
        return (unitPrice * gstPercentage / 100) * quantity;
    }

    public boolean isEligibleForDiscount() {
        return unitPrice >= 500;
    }

    public String getName() {
        return name;
    }
}

   class ShoppingCart {
    public static void main(String[] args) {
        List<Product> basket = new ArrayList<>();
        basket.add(new Product("Leather wallet", 1100, 18, 1));
        basket.add(new Product("Umbrella", 900, 12, 4));
        basket.add(new Product("Cigarette", 200, 28, 3));
        basket.add(new Product("Honey", 100, 0, 2));

        // 1. Identify the product for which we paid the maximum GST amount
        Product maxGstProduct = basket.stream()
                .max((p1, p2) -> Double.compare(p1.calculateGstAmount(), p2.calculateGstAmount()))
                .orElse(null);

        System.out.println("Product with maximum GST amount: " + maxGstProduct.getName());

        // 2. Calculate the total amount to be paid to the shop-keeper
        double totalPrice = basket.stream().mapToDouble(Product::calculateTotalPrice).sum();
        double totalGst = basket.stream().mapToDouble(Product::calculateGstAmount).sum();

        // Apply the 5% discount to eligible products
        double discountedTotalPrice = basket.stream()
                .mapToDouble(product -> product.isEligibleForDiscount() ? product.calculateTotalPrice() * 0.95 : product.calculateTotalPrice())
                .sum();

        System.out.println("Total amount to be paid (including GST): Rs. " + totalPrice);
        System.out.println("Total GST amount: Rs. " + totalGst);
        System.out.println("Total amount to be paid after discount: Rs. " + discountedTotalPrice);
    }
}
