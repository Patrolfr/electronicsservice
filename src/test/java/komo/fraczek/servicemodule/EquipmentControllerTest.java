package komo.fraczek.servicemodule;


import komo.fraczek.servicemodule.controller.EquipmentController;
import komo.fraczek.servicemodule.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentController.class)
@RequiredArgsConstructor
public class EquipmentControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentControllerTest.class);

    final MockMvc mockMvc;

    @MockBean
    final EquipmentService equipmentService;

    @Test
    void test_create() throws Exception{


        String content= "{\n" +
                "  \"name\": \"TestEquipment\",\n" +
                "  \"category\": \"tv\",\n" +
                "  \"serviceCode\": \"tst-121\",\n" +
                "  \"parameters\": {\n" +
                "    \"testkey1\": \"testvalue1\",\n" +
                "    \"testkey2\": \"testvalue2\"\n" +
                "  },\n" +
                "  \"comments\": [\n" +
                "    \"testcomment1\"\n" +
                "  ],\n" +
                "  \"serviceStatus\": \"WORKING\"\n" +
                "}";

//        logger.debug("\n\n\n\n\n\n\n\n"+content);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.POST, "/eqipments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andReturn();
    }


}
