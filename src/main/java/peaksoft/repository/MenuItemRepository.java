package peaksoft.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.entity.MenuItem;
import java.util.List;
import java.util.Optional;
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.inStock=true")
    List<MenuItemResponse> getAllMenu();
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m where m.id=:id")
    Optional<MenuItemResponse>findByIdMenu(Long id);
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price asc ")
    List<MenuItemResponse>sortByAsc();
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price desc ")
    List<MenuItemResponse>sortByDesc();
    boolean existsByName(String name);
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)" +
            "from MenuItem m where  m.inStock=true or lower(m.name) ilike lower(concat('%',:word,'%')) or  lower(m.subcategory.name)ilike lower(concat('%',:word,'%')) " +
            "or lower(m.subcategory.categories.name) ilike lower(concat('%',:word,'%') ) ")
    List<MenuItemResponse>globalSearch(String word);
    Page<MenuItemResponse> findAllBy(Pageable pageable);

}