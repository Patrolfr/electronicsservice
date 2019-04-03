package komo.fraczek.servicemodule;


import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import komo.fraczek.servicemodule.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class EquipmentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    EquipmentService equipmentService;

    private CategoryRepository categoryRepositoryMock;

    private EquipmentRepository equipmentRepositoryMock;

    @BeforeEach
    public void setUp(){
        equipmentRepositoryMock = mock(EquipmentRepository.class);
        categoryRepositoryMock = mock(CategoryRepository.class);
        equipmentService = new EquipmentService(categoryRepositoryMock, equipmentRepositoryMock);
    }

    @Test
    public void assertTest(){
        assertTrue(true);
    }





}
