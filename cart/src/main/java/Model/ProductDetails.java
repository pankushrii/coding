package Model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. <br />
 * User: PANKAJ CHOUDHARY <br />
 * Date: 11/8/19 <br />
 * Time: 9:58 AM <br />
 */
@XmlType
@XmlRootElement
public class ProductDetails implements Serializable {
    private static final long serialVersionUID = -2764070985347707824L;

    private String product;
    private String origin;
    private BigDecimal price;
    private String currency;
    private Float rating;
    private Integer inventory;
    private String category;
    private String arrival;
    HashMap<String, String> discount;

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

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public HashMap<String, String> getDiscount() {
        return discount;
    }

    public void setDiscount(HashMap<String, String> discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "product='" + product + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", rating=" + rating +
                ", inventory=" + inventory +
                ", category='" + category + '\'' +
                ", arrival='" + arrival + '\'' +
                ", discount=" + discount +
                '}';
    }
}
