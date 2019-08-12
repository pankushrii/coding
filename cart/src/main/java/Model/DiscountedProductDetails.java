package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA. <br />
 * User: PANKAJ CHOUDHARY <br />
 * Date: 11/8/19 <br />
 * Time: 2:16 PM <br />
 */
public class DiscountedProductDetails implements Serializable {
    String product;
    String origin;
    BigDecimal price;
    String currency;
    Float rating;
    Integer inventory;
    String category;
    HashMap<String, String> discount = null;//new HashMap<String, String>();

    public HashMap<String, String> getDiscount() {
        return discount;
    }

    public void setDiscount(HashMap<String, String> discount) {
        this.discount = discount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DiscountedProductDetails{" +
                "product='" + product + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", rating=" + rating +
                ", inventory=" + inventory +
                ", category='" + category + '\'' +
                ", discount=" + discount +
                '}';
    }
}
