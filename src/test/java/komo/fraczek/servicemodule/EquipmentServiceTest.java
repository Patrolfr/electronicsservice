package komo.fraczek.servicemodule;


import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentWrapper;
import komo.fraczek.servicemodule.exception.CategoryNotFoundException;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import komo.fraczek.servicemodule.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EquipmentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceTest.class);

    private EquipmentService equipmentService;

    private CategoryRepository categoryRepositoryMock;

    private EquipmentRepository equipmentRepositoryMock;

    @BeforeEach
    void setUp(){
        equipmentRepositoryMock = mock(EquipmentRepository.class);
        categoryRepositoryMock = mock(CategoryRepository.class);
        equipmentService = new EquipmentService(categoryRepositoryMock, equipmentRepositoryMock);
    }

    @Test
    void when_registerEquipment_returns_Equipment(){
        when(categoryRepositoryMock.findByName(any(String.class))).thenReturn(Optional.of(createCategoryFake()));
        EquipmentPayload equipmentPayloadFake = createEquipmentPayloadFake();
        Equipment equipmentFake = equipmentService.unwrapPayload(equipmentPayloadFake);
        when(equipmentRepositoryMock.save(any(Equipment.class))).thenReturn(equipmentFake);
        Equipment equipmentReturned = equipmentService.registerEquipment(equipmentPayloadFake);
        assertEquals(equipmentReturned, equipmentFake);
    }



    @Test
    void when_unwrapPayload_returns_Equipment(){
        when(categoryRepositoryMock.findByName(any(String.class))).thenReturn(Optional.of(createCategoryFake()));
        Equipment equipmentFakeUnwrapped = equipmentService.unwrapPayload(createEquipmentPayloadFake());
        assertEquals(createEquipmentPayloadFake().getName(), equipmentFakeUnwrapped.getName());
        assertEquals(createEquipmentPayloadFake().getComments(), equipmentFakeUnwrapped.getComments());
        assertEquals(createEquipmentPayloadFake().getServiceStatus(), equipmentFakeUnwrapped.getServiceStatus());
    }

    @Test
    void when_unwrapPayload_throws_Exception(){
        EquipmentPayload equipmentPayloadFake = createEquipmentPayloadFake();
        when(categoryRepositoryMock.findByName(any(String.class))).thenThrow(new CategoryNotFoundException("categoryFake"));
        assertThrows(RuntimeException.class, () -> equipmentService.unwrapPayload(equipmentPayloadFake));
    }




    private EquipmentPayload createEquipmentPayloadFake(){
        EquipmentPayload payload = EquipmentPayload.builder().name("Equipment Stub")
                .serviceStatus(ServiceStatus.valueOf("WORKING"))
                .category("categoryStringFake")
                .comments(Arrays.asList("CommentFake1"))
                .parameters(createParametersFake())
                .serviceStatus(ServiceStatus.WORKING)
                .build();
        return payload;
    }

    private Category createCategoryFake(){
        return new Category(1L,"categoryStringFake");
    }

    private HashMap<String,String> createParametersFake(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("keyfake1","valuefake1");
        parameters.put("keyfake2","valuefake2");
        return parameters;
    }
}
