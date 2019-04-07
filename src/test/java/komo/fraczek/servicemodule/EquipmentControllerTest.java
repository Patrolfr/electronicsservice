package komo.fraczek.servicemodule;


import com.google.common.io.Resources;
import komo.fraczek.servicemodule.controller.EquipmentController;
import komo.fraczek.servicemodule.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EquipmentService equipmentService;


    @Test
    void test_create() throws Exception{

        String equipmentPayloadString = getRequestData("equipmentPayload.json");
        logger.debug("\n***\n "+equipmentPayloadString);




    }


    protected String getRequestData(String filename) throws IOException {
        URL url = Resources.getResource(String.format("json/%s", filename));
        return Resources.toString(url, StandardCharsets.UTF_8);
    }

}
