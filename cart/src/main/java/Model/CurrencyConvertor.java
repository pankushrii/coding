package Model;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. <br />
 * User: PANKAJ CHOUDHARY <br />
 * Date: 11/8/19 <br />
 * Time: 12:37 PM <br />
 */
public class CurrencyConvertor implements Serializable {

    Map<String,String> rates;
    String base;
    String date;

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CurrencyConvertor{" +
                "rates=" + rates +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
