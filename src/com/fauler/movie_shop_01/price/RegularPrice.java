package com.fauler.movie_shop_01.price;

import com.fauler.movie_shop_01.Movie;

public class RegularPrice extends Price {
    @Override
    public int get_priceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented- 2) * 15;
        }
        return result;
    }
}
