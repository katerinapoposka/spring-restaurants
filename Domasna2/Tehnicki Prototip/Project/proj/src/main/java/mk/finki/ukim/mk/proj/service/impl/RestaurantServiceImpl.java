package mk.finki.ukim.mk.proj.service.impl;

import mk.finki.ukim.mk.proj.model.CuisineType;
import mk.finki.ukim.mk.proj.model.Restaurants;
import mk.finki.ukim.mk.proj.repository.jpa.RestaurantRepository;
import mk.finki.ukim.mk.proj.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurants> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurants> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurants> getRestaurantByName(String name) {
        return restaurantRepository.findByNameLikeIgnoreCase("%"+name+"%");
    }

    @Override
    public List<Restaurants> getRestaurantByCuisine(String cuisine) {
        return restaurantRepository.findByCuisine(CuisineType.valueOf(cuisine));
    }

    @Override
    public List<CuisineType> getCuisines() {
        return List.of(CuisineType.values());
    }
}
