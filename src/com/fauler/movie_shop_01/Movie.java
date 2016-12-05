package com.fauler.movie_shop_01;

public class Movie {

    public static final int CHILDRENS =2;
    public static final int REGULAR =0;
    public static final int NEW_RELEASE =1;

    private String _title;
    private int _priceCode;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_priceCode() {
        return _priceCode;
    }

    public void set_priceCode(int _priceCode) {
        this._priceCode = _priceCode;
    }

    public double getCharge(int daysRented) {
        double result = 0;
        switch (_priceCode) {
            case REGULAR:
                result += 2;
                if (daysRented > 2) {
                    result += (daysRented- 2) * 15;
                }
                break;
            case NEW_RELEASE:
                result += daysRented * 3;
                break;
            case CHILDRENS:
                result += 15;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 15;
                }
                break;
        }
        return result;
    }

    public int getFrequentRenterPoint(int daysRented) {
        return _priceCode == NEW_RELEASE && daysRented > 1 ? 2 : 1;
    }
}
