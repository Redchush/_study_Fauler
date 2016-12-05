package util;

import com.fauler.movie_shop_01.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CustomerFactory {

    public static final String DEFAULT_MOVIE_BASE_NAME = "movie";

    public static Supplier<Integer> randomRentalDays = ()-> (int) (Math.random() * 5 + 1);
    public static Supplier<Integer> randomCode = ()-> (int) (Math.random() * 3);

    public static Supplier<Integer> one = ()-> 1;
    public static Supplier<Integer> new_release = () -> Movie.NEW_RELEASE;
    public static Supplier<Integer> childrens = () -> Movie.CHILDRENS;
    public static Supplier<Integer> regular = () -> Movie.REGULAR;

    public static List<Supplier<Integer>> allCodes = new ArrayList<>();
    static {
        allCodes.add(new_release);
        allCodes.add(childrens);
        allCodes.add(regular);
    }


    public static Customer getCustomer(String customerName,
                                       String baseMovieName,
                                       List<Supplier<Integer>> codeSuppliers,
                                       List<Supplier<Integer>> rentalDaysSuppliers){

        List<Movie> movies = getMovie(baseMovieName, codeSuppliers);
        List<Rental> rentals = getRentalList(movies, rentalDaysSuppliers);
        Customer result = new Customer(customerName);
        rentals.forEach(rental -> result.addRental(rental));
        return result;
    }


    public static Customer getCustomer(String name, int rentalsCount,
                                        Supplier<Integer> codeSupplier,
                                        Supplier<Integer> rentalDaysSupplier ){
        List<Movie> movies = getMovieList(rentalsCount, null, codeSupplier);
        List<Rental> rentals = getRentalList(movies, rentalDaysSupplier);
        Customer result = new Customer(name);
        rentals.forEach(rental -> result.addRental(rental));
        return result;
    }

    public static Customer getRandomCustomer(String name, int rentalsCount){
       return getCustomer(name, rentalsCount, randomCode, randomRentalDays);
    }


    public static List<Movie> getMovie(String baseName, List<Supplier<Integer>> codeSuppliers){
        baseName = (baseName == null) ? DEFAULT_MOVIE_BASE_NAME : baseName;
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < codeSuppliers.size(); i++) {
            result.add(new Movie(baseName + i, codeSuppliers.get(i).get()));
        }
        return result;
    }


    public static List<Movie> getMovieList(int count, String baseName, Supplier<Integer> codeSupplier){
        baseName = (baseName == null) ? DEFAULT_MOVIE_BASE_NAME
                : baseName;
        List<Movie> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Movie movie = new Movie(baseName + i, codeSupplier.get());
            result.add(movie);
        }
        return result;
    }

    public static List<Rental> getRentalList(List<Movie> movieList, List<Supplier<Integer>> rentalDaysSuppliers){
        Iterator<Supplier<Integer>> iterator = rentalDaysSuppliers.iterator();
        return movieList.stream().map(s-> new Rental(s, iterator.next().get()))
                                 .collect(Collectors.toList());
    }


    public static List<Rental> getRentalList(List<Movie> movieList, Supplier<Integer> rentalDaysSupplier){
        List<Rental> result = new ArrayList<>();
        movieList.forEach(s-> {
                    final Rental rental = new Rental(s, rentalDaysSupplier.get());
                    result.add(rental);
                });
        return result;
    }
}
