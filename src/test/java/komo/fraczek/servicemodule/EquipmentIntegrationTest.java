package komo.fraczek.servicemodule;


import com.google.common.io.Resources;
import komo.fraczek.servicemodule.config.DbConfig;
import komo.fraczek.servicemodule.config.TestSecurityConfiguration;
import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.Parameter;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(SpringExtension.class)
//@WebMvcTest(EquipmentController.class) //FIXME
@ContextConfiguration(classes = {DbConfig.class, TestSecurityConfiguration.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@EnableAutoConfiguration
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:TestData.sql")
public class EquipmentIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentIntegrationTest.class);

    @Autowired
    MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;


//    TODO finish integration tests
    @Test
    void test_create() throws Exception{
        String equipmentPayloadString = getRequestData("equipmentPayload.json");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/equipments/")).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        logger.debug(contentAsString);
        logger.debug(mvcResult.getResponse().getStatus() + "");
    }




    private String getRequestData(String filename) throws IOException {
        URL url = Resources.getResource(String.format("json/%s", filename));
        return Resources.toString(url, StandardCharsets.UTF_8);
    }


}
