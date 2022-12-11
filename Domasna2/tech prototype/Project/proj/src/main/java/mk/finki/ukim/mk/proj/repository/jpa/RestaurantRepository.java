package mk.finki.ukim.mk.proj.repository.jpa;

import mk.finki.ukim.mk.proj.model.CuisineType;
import mk.finki.ukim.mk.proj.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurants, Long> {
    List<Restaurants> findByNameLikeIgnoreCase(String name);
    List<Restaurants> findByCuisine(CuisineType cuisine);
}
