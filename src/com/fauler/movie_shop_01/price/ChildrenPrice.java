package com.fauler.movie_shop_01.price;

import com.fauler.movie_shop_01.Movie;

public class ChildrenPrice extends Price {
    @Override
    public int get_priceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 15;
        if (daysRented > 3) {
            result += (daysRented - 3) * 15;
        }
        return result;
    }
}
