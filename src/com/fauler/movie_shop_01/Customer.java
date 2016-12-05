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
        Enumeration rentals = _rentals.elements();
        String result = "Учет аренды для " + get_name() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            //show results for its renal
            result += "\t" + each.get_movie().get_title() + "\t"
                    + String.valueOf(each.getCharge()) + "\n";
        }
        //add footer
        result += "Сумма задолженности составляет " +
                String.valueOf(getTotalCharge()) + "\n";
        result += "Вы заработали " + String.valueOf(getTotalFrequentRenterPoints()) + " очков за " +
                "активность";
        return result;
    }

    public String htmlStatement(){
        Enumeration rentals = _rentals.elements();
        String result = "<h1>Операции аренды для <em>" + get_name() + "</em></h1><p>\n";
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            //show result for each
            result+=each.get_movie().get_title() + " " +
                    String.valueOf(each.getCharge()) + "<br>\n";

        }
        //add footer
        result+="<p>Ваша задолженность составляет <em>" + String.valueOf(getTotalCharge()) +"</em><p>\n";
        result += "Вы заработали <em>" + String.valueOf(getTotalFrequentRenterPoints()) + "</em> очков за " +
                "активность<p>";
        return result;
    }

    public double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    public int getTotalFrequentRenterPoints(){
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }


}
