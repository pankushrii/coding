import Model.CurrencyConvertor;
import Model.ProductDetails;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import util.JsonFormatUtility;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA. <br />
 * User: PANKAJ CHOUDHARY <br />
 * Date: 11/8/19 <br />
 * Time: 9:39 AM <br />
 */
public class AmazingKart {
//    private static final Logger log = Logger.getLogger(AmazingKart.class);

    public static void main(String[] args) {
        AmazingKart amazingKart = new AmazingKart();
        List<ProductDetails> productDetailsList = amazingKart.getProductDeatils();
        //  System.out.println("Before Promotion \n " + productDetailsList);
        if (args[0].trim().equalsIgnoreCase("promotionSetA")) {
            amazingKart.applyPromotionA(productDetailsList);
        }
        if (args[0].trim().equalsIgnoreCase("promotionSetB")) {
            amazingKart.applyPromotionB(productDetailsList);
        }
        //Write JSON file
        try {
            File dir = new File("output/");
            dir.mkdirs();
            File outputFile = new File(dir, "output.json");
            outputFile.createNewFile();

            FileWriter file = new FileWriter(outputFile);
            file.write(JsonFormatUtility.getJsonStringFromObject(productDetailsList));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private BigDecimal currencyConversionAPI(String fromCurrency, String toCurrency, BigDecimal amount) {
        String url = "https://api.exchangeratesapi.io/latest";
        BigDecimal convertedToINR = BigDecimal.ZERO;
        WebResource webResource = createIasRestClient(url, 10);
        String productApiResponse = webResource.accept(MediaType.TEXT_PLAIN).header(HttpHeaders.USER_AGENT, HttpHeaders.ACCEPT_CHARSET)
                .get(String.class);
        CurrencyConvertor currencyConvertor = JsonFormatUtility.getObjectFromJsonString(productApiResponse, CurrencyConvertor.class);
        if (currencyConvertor.getRates().get(fromCurrency) != null && currencyConvertor.getRates().get(toCurrency) != null) {
            BigDecimal otherCurrency = new BigDecimal(currencyConvertor.getRates().get(fromCurrency));
            BigDecimal INR = new BigDecimal(currencyConvertor.getRates().get(toCurrency));
            convertedToINR = INR.divide(otherCurrency, 2, RoundingMode.HALF_UP).multiply(amount);
        }
        return convertedToINR;
    }

    private List<ProductDetails> getProductDeatils() {
        AmazingKart amazingKart = new AmazingKart();
        String productDetailsURL = "https://api.jsonbin.io/b/5d31a1c4536bb970455172ca/latest";
        WebResource webResource = amazingKart.createIasRestClient(productDetailsURL, 10);
        String productApiResponse = webResource.accept(MediaType.APPLICATION_JSON).header(HttpHeaders.USER_AGENT, HttpHeaders.ACCEPT_CHARSET)
                .get(String.class);
        List<ProductDetails> productDetailsList = JsonFormatUtility.parseGsonArray(productApiResponse, ProductDetails[].class);
        return productDetailsList;
    }

    private void applyPromotionA(List<ProductDetails> productDeatilsList) {
        System.out.println("applyPromotionA");
        boolean isFlat = false;
        for (ProductDetails productDetails : productDeatilsList) {
            HashMap<String, String> map = new HashMap();
            int DISCOUNT_VALUE_7 = 7;
            int DISCOUNT_VALUE_4 = 4;
            int DISCOUNT_VALUE_8 = 8;
            int DISCOUNT_VALUE_100 = 100;
            BigDecimal finalDiscountAmount = BigDecimal.ZERO;
            String discountTag = "";
            if (!productDetails.getCurrency().equalsIgnoreCase("INR")) {
                BigDecimal INR_Currency = currencyConversionAPI(productDetails.getCurrency(), "INR", productDetails.getPrice());
                productDetails.setPrice(INR_Currency);
                productDetails.setCurrency("INR");
            }
            if (productDetails.getOrigin().equalsIgnoreCase("AFRICA")) {
                finalDiscountAmount = new BigDecimal(DISCOUNT_VALUE_7).divide(new BigDecimal(100),
                        2, RoundingMode.HALF_UP).multiply(productDetails.getPrice());
                discountTag = "get " + DISCOUNT_VALUE_7 + "% Off";
            }
            if (productDetails.getRating() == 2.0) {
                BigDecimal discountAmount = new BigDecimal(DISCOUNT_VALUE_4).divide(new BigDecimal(100),
                        2, RoundingMode.HALF_UP).multiply(productDetails.getPrice());
                if (discountAmount.compareTo(finalDiscountAmount) > 0) {
                    finalDiscountAmount = discountAmount;
                }
            }

            if (productDetails.getRating() < 2.0) {
                BigDecimal discountAmount = new BigDecimal(DISCOUNT_VALUE_8).divide(new BigDecimal(100),
                        2, RoundingMode.HALF_UP).multiply(productDetails.getPrice());
                if (discountAmount.compareTo(finalDiscountAmount) > 0) {
                    finalDiscountAmount = discountAmount;
                    discountTag = "get " + 7 + "% Off";
                }
            }
            if ((productDetails.getCategory().equalsIgnoreCase("electronics")
                    || productDetails.getCategory().equalsIgnoreCase("furnishing"))
                    && productDetails.getPrice().compareTo(new BigDecimal(500)) > 0) {
                if (finalDiscountAmount.compareTo(new BigDecimal(100)) < 0) {
                    finalDiscountAmount = new BigDecimal(DISCOUNT_VALUE_100);
                    isFlat = true;
                }
            }
            map.put("amount", finalDiscountAmount.toString());
            map.put("discountTag",discountTag );
            productDetails.setDiscount(map);
        }

    }


    private void applyPromotionB(List<ProductDetails> productDeatilsList) {
        System.out.println("applyPromotionB");
        int DISCOUNT_VALUE_7 = 7;
        int DISCOUNT_VALUE_8 = 8;
        int DISCOUNT_VALUE_12 = 12;

        for (ProductDetails productDetails : productDeatilsList) {
            HashMap<String, String> map = new HashMap();
            BigDecimal discountAmount = BigDecimal.ZERO;
            if (productDetails.getInventory() > 20) {
                discountAmount = new BigDecimal(DISCOUNT_VALUE_12).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).multiply(productDetails.getPrice());
                map.put("amount", discountAmount.toString());
                map.put("discountTag", "get " + 12 + "% Off");
            }
            if (productDetails.getArrival() != null && productDetails.getArrival().equalsIgnoreCase("NEW")) {
                discountAmount = new BigDecimal(DISCOUNT_VALUE_7).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).multiply(productDetails.getPrice());
                map.put("amount", discountAmount.toString());
                map.put("discountTag", "get " + 7 + "% Off");
            }
            productDetails.setDiscount(map);
        }

    }

    private WebResource createIasRestClient(String url, int timeoutSeconds) {
     //   log.info("Creating a new  REST Client with URL" + url);
        Client httpClient = Client.create();
        httpClient.setConnectTimeout(timeoutSeconds * 1000);
        httpClient.setReadTimeout(timeoutSeconds * 1000);
        //httpClient.addFilter(new LoggingFilter());
        return httpClient.resource(url);
    }

}
