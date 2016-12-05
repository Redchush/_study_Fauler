package com.fauler.movie_shop_01.price;

import com.fauler.movie_shop_01.Movie;

public abstract class Price {

    public abstract int get_priceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoint(int daysRented) {
        return 1;
    }

}