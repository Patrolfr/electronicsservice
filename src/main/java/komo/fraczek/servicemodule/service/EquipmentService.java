package komo.fraczek.servicemodule.service;

import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.EquipServiceRequest;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.dto.Payload;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    private final RequestRepository requestRepository;

    private final CategoryRepository categoryRepository;


    public final EquipServiceRequest registerServiceRequest(final Payload equipmentPayload){
       logger.trace(equipmentPayload.toString());
       EquipServiceRequest equipServiceRequest = EquipServiceRequest.ofEquipmentAndOwner(unwrapPayload(equipmentPayload), equipmentPayload.getPhone());
       equipServiceRequest.setServiceCode(getCode());

        logger.trace(equipServiceRequest.toString());
        logger.trace(equipServiceRequest.getEquipment().toString());
        EquipServiceRequest equipServiceRequestt = requestRepository.save(equipServiceRequest);
       logger.trace(equipServiceRequestt.toString());
        return equipServiceRequestt;
    }

    private Equipment unwrapPayload(Payload equipmentPayload){
        Category category = categoryRepository.findByName(equipmentPayload.getCategory()).orElseThrow(() -> new RuntimeException("Category not found."));
        logger.trace(category.toString());
        Equipment equipment = Equipment.ofNameAndCategoryAndParamsHashMap(equipmentPayload.getName(), category, equipmentPayload.getParameters() );
        return equipment;
    }

    private   String getCode(){
        String code = RandomStringUtils.randomAlphabetic(3) + "-" + RandomStringUtils.randomNumeric(3);
        return  !requestRepository.existsByServiceCode(code) ? code : getCode();
    }
}
