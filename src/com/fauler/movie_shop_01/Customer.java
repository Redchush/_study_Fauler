package com.fauler.movie_shop_01;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg){
        _rentals.addElement(arg);
    }

    public String get_name() {
        return _name;
    }

    public String statement(){
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();

        String result = "Учет аренды для " + get_name() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //добавить очки для активного арендатора
            frequentRenterPoints += each.getFrequentRenterPoints();
            //show results for its renal
            result += "\t" + each.get_movie().get_title() + "\t"
                    + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }
        //add footer
        result += "Сумма задолженности составляет " +
                String.valueOf(totalAmount) + "\n";
        result += "Вы заработали " + String.valueOf(frequentRenterPoints) + " очков за " +
                "активность";
        return result;
    }


}
