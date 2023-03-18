package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.StopList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StopListRepository extends JpaRepository<StopList, Long> {
    @Query("select new peaksoft.dto.response.StopListResponse(s.id,s.reason,s.date,s.menuitem.name)from  StopList s")
    List<StopListResponse>getAllList();
    @Query("select new peaksoft.dto.response.StopListResponse(s.id,s.reason,s.date,s.menuitem.name)from  StopList s where  s.id=:id")
    Optional<StopListResponse> findByIdList(Long id);
}