package com.fauler.movie_shop_01.price;

import com.fauler.movie_shop_01.Movie;

public class NewReleasePrice extends Price {
    @Override
    public int get_priceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoint(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
