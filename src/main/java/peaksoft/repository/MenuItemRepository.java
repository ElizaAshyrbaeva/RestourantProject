package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian,m.restaurant.name)from MenuItem m")
    List<MenuItemResponse>getAllMenu();
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian,m.restaurant.name)from MenuItem m where m.id=:id")
    Optional<MenuItemResponse>findByIdMenu(Long id);
}