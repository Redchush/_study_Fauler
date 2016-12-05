package com.fauler.movie_shop_01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.CustomerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CustomerTest {


    private static final String customerName = "kenni";
    private static final String movieBase = "movie";

    private Customer customer;
    private String expectedReport;

    public CustomerTest(Customer customer, String expectedReport) {
        this.customer = customer;
        this.expectedReport = expectedReport;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]
                {
                        {CustomerFactory.getCustomer(customerName, movieBase,
                                new ArrayList<>(Collections.nCopies(2, CustomerFactory.childrens)),
                                new ArrayList<>(Collections.nCopies(2, CustomerFactory.one))),
                                "<h1>Операции аренды для <em>kenni</em></h1><p>\n" +
                                        "movie0 15.0<br>\n" +
                                        "movie1 15.0<br>\n" +
                                        "<p>Ваша задолженность составляет <em>30.0</em><p>\n" +
                                        "Вы заработали <em>2</em> очков за активность<p>" },
                        {
                            CustomerFactory.getCustomer(customerName, movieBase,
                                    new ArrayList<>(Collections.nCopies(3, CustomerFactory.regular)),
                                    new ArrayList<>(Collections.nCopies(3, ()-> 3))),
                            "<h1>Операции аренды для <em>kenni</em></h1><p>\n" +
                                    "movie0 17.0<br>\n" +
                                    "movie1 17.0<br>\n" +
                                    "movie2 17.0<br>\n" +
                                    "<p>Ваша задолженность составляет <em>51.0</em><p>\n" +
                                    "Вы заработали <em>3</em> очков за активность<p>"

                        },
                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        new ArrayList<>(Collections.nCopies(3, CustomerFactory.new_release)),
                                        new ArrayList<>(Collections.nCopies(3, ()-> 4))),
                                "<h1>Операции аренды для <em>kenni</em></h1><p>\n" +
                                        "movie0 12.0<br>\n" +
                                        "movie1 12.0<br>\n" +
                                        "movie2 12.0<br>\n" +
                                        "<p>Ваша задолженность составляет <em>36.0</em><p>\n" +
                                        "Вы заработали <em>6</em> очков за активность<p>"

                        },
                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                      CustomerFactory.allCodes,
                                      new ArrayList<>(Collections.nCopies(3, ()-> 4))),
                                "<h1>Операции аренды для <em>kenni</em></h1><p>\n" +
                                        "movie0 12.0<br>\n" +
                                        "movie1 30.0<br>\n" +
                                        "movie2 32.0<br>\n" +
                                        "<p>Ваша задолженность составляет <em>74.0</em><p>\n" +
                                        "Вы заработали <em>4</em> очков за активность<p>"
                        },

                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        CustomerFactory.allCodes,
                                        new ArrayList<>(Collections.nCopies(3, ()-> 2))),
                                "<h1>Операции аренды для <em>kenni</em></h1><p>\n" +
                                        "movie0 6.0<br>\n" +
                                        "movie1 15.0<br>\n" +
                                        "movie2 2.0<br>\n" +
                                        "<p>Ваша задолженность составляет <em>23.0</em><p>\n" +
                                        "Вы заработали <em>4</em> очков за активность<p>"
                        }

                };
        return Arrays.asList(data);
    }

    @Test
    public void statement() throws Exception {
        final String statement = customer.htmlStatement();
        assertEquals(statement, expectedReport);
    }



}