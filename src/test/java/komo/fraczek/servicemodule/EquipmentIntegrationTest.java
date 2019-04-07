package komo.fraczek.servicemodule;


import com.google.common.io.Resources;
import komo.fraczek.servicemodule.config.DbConfig;
import komo.fraczek.servicemodule.controller.EquipmentController;
import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.Parameter;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentController.class)
@ContextConfiguration(classes = {DbConfig.class})
@ActiveProfiles("Test")
@AutoConfigureMockMvc(secure = false)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:TestData.sql")
public class EquipmentIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentIntegrationTest.class);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    void test_create() throws Exception{
        String equipmentPayloadString = getRequestData("equipmentPayload.json");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/equipments/")).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        logger.debug(contentAsString);
        logger.debug("status" + mvcResult.getResponse().getStatus());
    }

    @Test
    void test_EquipmentRepository(){
        Equipment equipment = createEquipmentFake("EquipmentFakeName");
        Equipment save = equipmentRepository.save(equipment);
        assertEquals(equipment.getName(),save.getName());
    }

    @Test
    void test_CategoryRepository(){
        Category category = createCategoryFake();
        Category save = categoryRepository.save(category);
        assertEquals(category.getName(), save.getName());
    }


    private String getRequestData(String filename) throws IOException {
        URL url = Resources.getResource(String.format("json/%s", filename));
        return Resources.toString(url, StandardCharsets.UTF_8);
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
