package org.abondar.experimental.springws;



import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abondar on 23.07.16.
 */
public class Stock implements Serializable {
    private static final long serialVersionUID =1l;
    private static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";

    private String code;
    private double price;
    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public Stock(){}

    public Stock(String code, double price) {
        this.code = code;
        this.price = price;
    }


    public  String getDateFormatted() {
        return dateFormat.format(date);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
