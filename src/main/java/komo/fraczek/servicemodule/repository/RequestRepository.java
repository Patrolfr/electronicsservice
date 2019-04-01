package komo.fraczek.servicemodule.repository;


import komo.fraczek.servicemodule.domain.EquipServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<EquipServiceRequest, Long> {

    Optional<EquipServiceRequest> findById(Long id);

    Optional<EquipServiceRequest> findByServiceCode(String serviceCode);

    boolean existsByServiceCode(String serviceCode);

}
