package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.entity.Restaurant;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select new peaksoft.dto.response.RestaurantResponse(r.id,r.name,r.location,r.restType,r.service)from Restaurant r")
     List<RestaurantResponse> getAllSB() ;
    @Query("select new peaksoft.dto.response.RestaurantResponse(r.id,r.name,r.location,r.restType,r.service)from Restaurant r where  r.id=:id")
    Optional<RestaurantResponse> getByRestId(Long id);

    boolean existsByName(String name);
}