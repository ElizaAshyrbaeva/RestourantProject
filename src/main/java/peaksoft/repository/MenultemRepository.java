package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Menultem;
@Repository
public interface MenultemRepository extends JpaRepository<Menultem, Long> {
}