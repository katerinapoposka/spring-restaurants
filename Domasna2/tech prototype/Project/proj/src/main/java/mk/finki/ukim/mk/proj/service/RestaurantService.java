package mk.finki.ukim.mk.proj.service;

import mk.finki.ukim.mk.proj.model.CuisineType;
import mk.finki.ukim.mk.proj.model.Restaurants;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurants> getAllRestaurants();
    Optional<Restaurants> getRestaurantById(Long id);
    List<Restaurants> getRestaurantByName(String name);
    List<Restaurants> getRestaurantByCuisine(String cuisine);
    List<CuisineType> getCuisines();
}
