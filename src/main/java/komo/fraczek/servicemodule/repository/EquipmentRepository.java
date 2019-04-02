package komo.fraczek.servicemodule.repository;


import komo.fraczek.servicemodule.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findById(Long id);

    Optional<Equipment> findByServiceCode(String serviceCode);

    boolean existsByServiceCode(String serviceCode);

}
