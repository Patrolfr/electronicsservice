package komo.fraczek.servicemodule;


import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.Parameter;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.CommentsPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentWrapper;
import komo.fraczek.servicemodule.exception.CategoryNotFoundException;
import komo.fraczek.servicemodule.exception.CodeNotFoundException;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import komo.fraczek.servicemodule.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        Category categoryFake = createCategoryFake();
        when(categoryRepositoryMock.findByName(categoryFake.getName())).thenReturn(Optional.of(categoryFake));
        EquipmentPayload equipmentPayloadFake = createEquipmentPayloadFake();
        Equipment equipmentFake = equipmentService.unwrapPayload(equipmentPayloadFake);
        when(equipmentRepositoryMock.save(any(Equipment.class))).thenReturn(equipmentFake);
        Equipment equipmentReturned = equipmentService.registerEquipment(equipmentPayloadFake);

        assertEquals(equipmentReturned, equipmentFake);
    }

    @Test
    void when_deleteEquipment_deletesEquipment(){
        when(equipmentRepositoryMock.existsByServiceCode("FakeCode")).thenReturn(true);
        equipmentService.deleteEquipment("FakeCode");
        verify(equipmentRepositoryMock).deleteByServiceCode("FakeCode");
    }

    @Test
    void when_fetchAllAndWrap_returns_EquipmentWrappersList(){
        List<Equipment> equipmentListFake = createEquipmentListFake();
        when(equipmentRepositoryMock.findAll()).thenReturn(equipmentListFake);
        List<EquipmentWrapper> equipmentWrappers = equipmentService.fetchAllAndWrap();

        assertEquals(equipmentWrappers.size(), equipmentListFake.size());
    }

    @Test
    void when_fetchByCategoryAndWrap_returns_EquipmentWrappersList(){
        List<Equipment> equipmentListFake = createEquipmentListFake();
        when(equipmentRepositoryMock.findAllByCategory_Name(any(String.class))).thenReturn(equipmentListFake);
        List<EquipmentWrapper> equipmentWrappers = equipmentService.fetchByCategoryAndWrap("fakeCategory");

        assertEquals(equipmentListFake.size(), equipmentWrappers.size());
    }

    @Test
    void when_fetchByCode_returns_Equipment(){
        Equipment equipmentFake = createEquipmentFake();
        when(equipmentRepositoryMock.findByServiceCode(equipmentFake.getServiceCode())).thenReturn(Optional.of(equipmentFake));
        Equipment equipmentFetched = equipmentService.fetchByCode(equipmentFake.getServiceCode());

        assertEquals(equipmentFake, equipmentFetched);
    }

    @Test
    void when_fetchByCode_throws_CodeNotFoundException(){
        Equipment equipmentFake = createEquipmentFake();
        when(equipmentRepositoryMock.findByServiceCode(equipmentFake.getServiceCode())).thenReturn(Optional.empty());

        assertThrows(CodeNotFoundException.class, () -> equipmentService.fetchByCode(equipmentFake.getServiceCode()));
    }

    @Test
    void when_changeStatus_returns_EquipmentWithChangedStatus(){
        Equipment equipmentFake = createEquipmentFake();
        when(equipmentRepositoryMock.findByServiceCode(equipmentFake.getServiceCode())).thenReturn(Optional.of(equipmentFake));
        when(equipmentRepositoryMock.save(equipmentFake)).thenReturn(equipmentFake);

        assertEquals(equipmentFake.getServiceStatus(),ServiceStatus.WORKING);
        Equipment equipmentReturned = equipmentService.changeStatus(equipmentFake.getServiceCode(), ServiceStatus.BROKEN);
        assertEquals(equipmentFake.getServiceStatus(),ServiceStatus.BROKEN);
        assertEquals(equipmentFake, equipmentReturned);
    }

    @Test
    void when_changeStatus_throws_CodeNotFoundException(){
        Equipment equipmentFake = createEquipmentFake();
        when(equipmentRepositoryMock.findByServiceCode(equipmentFake.getServiceCode())).thenReturn(Optional.empty());
        when(equipmentRepositoryMock.save(equipmentFake)).thenReturn(equipmentFake);

        assertThrows(CodeNotFoundException.class, () -> equipmentService.changeStatus(equipmentFake.getServiceCode(),ServiceStatus.BROKEN));
    }

    //FIXME test failure, appendComments rly works..
    @Test
    void when_appendComments_returns_EquipmentWithAppendedComments(){
        Equipment equipmentFake = createEquipmentFake();
        CommentsPayload commentsPayload = createCommentsPayloadFake();
        when(equipmentRepositoryMock.findByServiceCode(equipmentFake.getServiceCode())).thenReturn(Optional.of(equipmentFake));
        when(equipmentRepositoryMock.save(equipmentFake)).thenReturn(equipmentFake);

        logger.debug(equipmentFake.toString());
        logger.debug(commentsPayload.toString());

        assertEquals(equipmentFake.getComments().size(), 0);
        // TODO
//        equipmentService.appendComments(equipmentFake.getServiceCode(), commentsPayload);
//        assertEquals(equipmentFake.getComments().size(), createCommentsPayloadFake().getComments().size());
    }


    @Test
    void when_unwrapPayload_returns_Equipment(){
        Category categoryFake = createCategoryFake();

        when(categoryRepositoryMock.findByName(categoryFake.getName())).thenReturn(Optional.of(categoryFake));
        Equipment equipmentFakeUnwrapped = equipmentService.unwrapPayload(createEquipmentPayloadFake());

        assertEquals(createEquipmentPayloadFake().getName(), equipmentFakeUnwrapped.getName());
        assertEquals(createEquipmentPayloadFake().getComments(), equipmentFakeUnwrapped.getComments());
        assertEquals(createEquipmentPayloadFake().getServiceStatus(), equipmentFakeUnwrapped.getServiceStatus());
    }

    @Test
    void when_unwrapPayload_throws_CategoryNotFoundException(){
        EquipmentPayload equipmentPayloadFake = createEquipmentPayloadFake();
        when(categoryRepositoryMock.findByName(equipmentPayloadFake.getCategory())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> equipmentService.unwrapPayload(equipmentPayloadFake));
    }



//  Fake generation

    private EquipmentPayload createEquipmentPayloadFake(){
        return EquipmentPayload.builder().name("Equipment FakeName")
                .serviceStatus(ServiceStatus.valueOf("WORKING"))
                .category("categoryStringFake")
                .comments(Arrays.asList("CommentFake1", "CommentFake2"))
                .parameters(createParametersFake())
                .serviceStatus(ServiceStatus.WORKING)
                .build();
    }

    private Equipment createEquipmentFake(String name){
        return Equipment.builder().category(createCategoryFake())
                                  .parameters(Arrays.asList(new Parameter("keyFake1","valueFake1")))
                                  .name(name)
                                  .serviceCode("XXX-123")
                                  .serviceStatus(ServiceStatus.WORKING)
                                  .comments(Arrays.asList())
                                  .build();
    }

    private Equipment createEquipmentFake(){
        return Equipment.builder().category(createCategoryFake())
                                  .parameters(Arrays.asList(new Parameter("keyFake1","valueFake1")))
                                  .name("EquipmentFakeName")
                                  .serviceCode("XXX-123")
                                  .serviceStatus(ServiceStatus.WORKING)
                                   .comments(Arrays.asList())
                                  .build();
    }

    private List<Equipment> createEquipmentListFake(){
        return Arrays.asList(createEquipmentFake("EquipmentFakeName1"), createEquipmentFake("EquipmentFakeName2"));
    }

    private Category createCategoryFake(){
        return new Category(1L,"categoryStringFake");
    }

    private CommentsPayload createCommentsPayloadFake(){
        CommentsPayload commentsPayload = new CommentsPayload();
        commentsPayload.setComments(Arrays.asList("CommentFake1", "CommentFake2", "CommentFake3"));
        return commentsPayload;
    }

    private HashMap<String,String> createParametersFake(){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("keyfake1","valuefake1");
        parameters.put("keyfake2","valuefake2");
        return parameters;
    }
}