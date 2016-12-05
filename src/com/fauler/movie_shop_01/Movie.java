package com.fauler.movie_shop_01;

import com.fauler.movie_shop_01.price.ChildrenPrice;
import com.fauler.movie_shop_01.price.NewReleasePrice;
import com.fauler.movie_shop_01.price.Price;
import com.fauler.movie_shop_01.price.RegularPrice;

public class Movie {

    public static final int CHILDRENS =2;
    public static final int REGULAR =0;
    public static final int NEW_RELEASE =1;

    private Price price;
    private String _title;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
         set_priceCode(_priceCode);
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_priceCode() {
        return getPrice().get_priceCode();
    }

    public void set_priceCode(int _priceCode) {
        switch (_priceCode){
            case REGULAR:
                setPrice(new RegularPrice());
                break;
            case NEW_RELEASE:
                setPrice(new NewReleasePrice());
                break;
            case CHILDRENS:
                setPrice(new ChildrenPrice());
                break;
            default:
                throw new IllegalArgumentException("Icorrect Price Code");
        }
    }

    public int getFrequentRenterPoint(int daysRented) {
        return price.getFrequentRenterPoint(daysRented);
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }
}
