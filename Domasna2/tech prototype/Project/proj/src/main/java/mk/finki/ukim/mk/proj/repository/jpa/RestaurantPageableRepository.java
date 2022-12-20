package mk.finki.ukim.mk.proj.repository.jpa;

import mk.finki.ukim.mk.proj.model.Restaurants;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface RestaurantPageableRepository extends PagingAndSortingRepository<Restaurants, Integer> {
    Page<Restaurants> findAll(Pageable pageable);
}
