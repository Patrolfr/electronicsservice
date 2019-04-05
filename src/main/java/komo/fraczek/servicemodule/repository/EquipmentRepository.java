package komo.fraczek.servicemodule.repository;


import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findById(Long id);

    Optional<Equipment> findByServiceCode(String serviceCode);

    List<Equipment> findAllByCategory_Name(String category);

    boolean existsByServiceCode(String serviceCode);
}
