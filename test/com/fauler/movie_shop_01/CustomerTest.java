package com.fauler.movie_shop_01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.CustomerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
                                "Учет аренды для kenni\n" +
                                        "\tmovie0\t15.0\n" +
                                        "\tmovie1\t15.0\n" +
                                        "Сумма задолженности составляет 30.0\n" +
                                        "Вы заработали 2 очков за активность" },
                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        new ArrayList<>(Collections.nCopies(3, CustomerFactory.regular)),
                                        new ArrayList<>(Collections.nCopies(3, ()-> 3))),
                                "Учет аренды для kenni\n" +
                                        "\tmovie0\t17.0\n" +
                                        "\tmovie1\t17.0\n" +
                                        "\tmovie2\t17.0\n" +
                                        "Сумма задолженности составляет 51.0\n" +
                                        "Вы заработали 3 очков за активность"

                        },
                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        new ArrayList<>(Collections.nCopies(3, CustomerFactory.new_release)),
                                        new ArrayList<>(Collections.nCopies(3, ()-> 4))),
                                "Учет аренды для kenni\n" +
                                        "\tmovie0\t12.0\n" +
                                        "\tmovie1\t12.0\n" +
                                        "\tmovie2\t12.0\n" +
                                        "Сумма задолженности составляет 36.0\n" +
                                        "Вы заработали 6 очков за активность"

                        },
                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        CustomerFactory.allCodes,
                                        new ArrayList<>(Collections.nCopies(3, ()-> 4))),
                                "Учет аренды для kenni\n" +
                                        "\tmovie0\t12.0\n" +
                                        "\tmovie1\t30.0\n" +
                                        "\tmovie2\t32.0\n" +
                                        "Сумма задолженности составляет 74.0\n" +
                                        "Вы заработали 4 очков за активность"
                        },

                        {
                                CustomerFactory.getCustomer(customerName, movieBase,
                                        CustomerFactory.allCodes,
                                        new ArrayList<>(Collections.nCopies(3, ()-> 2))),
                                "Учет аренды для kenni\n" +
                                        "\tmovie0\t6.0\n" +
                                        "\tmovie1\t15.0\n" +
                                        "\tmovie2\t2.0\n" +
                                        "Сумма задолженности составляет 23.0\n" +
                                        "Вы заработали 4 очков за активность"
                        }

                };
        return Arrays.asList(data);
    }

    @Test
    public void htmlStatement() throws Exception {
        final String statement = customer.statement();
        assertEquals(statement, expectedReport);
    }


}
