package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.entity.SubCategory;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select new peaksoft.dto.response.SubCategoryResponse(s.id,s.name,s.categories.name)from SubCategory s")
    List<SubCategoryResponse>getAllSub();
    @Query("select  new peaksoft.dto.response.SubCategoryResponse(s.id,s.name,s.categories.name) from SubCategory s where s.id=:id")
    Optional<SubCategoryResponse> findByIdSub(Long id);
    @Query("select new peaksoft.dto.response.SubCategoryResponse(s.id,s.name,s.categories.name) from SubCategory s where s.categories.name ilike %:word% order by s.name")
    List<SubCategoryResponse> orderByName(String word);




}