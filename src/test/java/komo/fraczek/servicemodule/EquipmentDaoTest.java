package komo.fraczek.servicemodule;

import komo.fraczek.servicemodule.config.DbConfig;
import komo.fraczek.servicemodule.config.TestSecurityConfiguration;
import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.Parameter;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("test")
public class EquipmentDaoTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;


    @Test
    @Transactional
    @Rollback(true)
    void when_equipmentSaved_then_findByServiceCode_shouldReturn_equipment(){
        Equipment equipment = createEquipmentFake("FakeName");
        equipment.setCategory(categoryRepository.save(createCategoryFake()));
        equipmentRepository.save(equipment);
        Optional<Equipment> byServiceCode = equipmentRepository.findByServiceCode(equipment.getServiceCode());

        assertTrue(byServiceCode.isPresent());
        assertEquals(equipment.getName(), byServiceCode.get().getName());
    }

    @Test
    void when_equipmentNotSaved_then_findByServiceCode_shouldReturn_OptionalEmpty(){
        Optional<Equipment> byServiceCode = equipmentRepository.findByServiceCode("FakeCode");

        assertFalse(byServiceCode.isPresent());
    }

    @Test
    @Transactional
    @Rollback(true)
    void when_oneEquipmentSaved_then_findAllByCategoryName_shouldReturn_listOfOneEqipment(){
        Equipment equipment = createEquipmentFake("FakeName");
        categoryRepository.save(createCategoryFake());
        equipmentRepository.save(equipment);
        List<Equipment> allByCategoryName = equipmentRepository.findAllByCategoryName(createCategoryFake().getName());

        assertEquals(1, allByCategoryName.size());
        assertEquals(allByCategoryName.get(0).getName(),equipment.getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    void when_noneEquipmentSaved_then_findAllByCategoryName_shouldReturn_EmptyList(){
        List<Equipment> allByCategoryName = equipmentRepository.findAllByCategoryName(createCategoryFake().getName());

        assertTrue(allByCategoryName.isEmpty());
    }



    private Category createCategoryFake(){
        return new Category(1L,"categoryStringFake");
    }

    private Equipment createEquipmentFake(String name) {
        return Equipment.builder().category(createCategoryFake())
                .parameters(Arrays.asList(new Parameter("keyFake1", "valueFake1")))
                .name(name)
                .serviceCode("XXX-123")
                .serviceStatus(ServiceStatus.WORKING)
                .comments(Arrays.asList())
                .build();
    }
}
