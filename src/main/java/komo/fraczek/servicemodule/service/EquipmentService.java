package komo.fraczek.servicemodule.service;

import komo.fraczek.servicemodule.domain.Category;
import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.CommentsPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.exception.CodeNotFoundException;
import komo.fraczek.servicemodule.repository.CategoryRepository;
import komo.fraczek.servicemodule.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    private final CategoryRepository categoryRepository;

    private final EquipmentRepository equipmentRepository;


    public final Equipment registerEquipment(final EquipmentPayload equipmentPayload){

        Equipment equipment = unwrapPayload(equipmentPayload);

        return equipmentRepository.save(equipment);
    }

    public final Equipment fetchByCode(final String code){

        return equipmentRepository.findByServiceCode(code).orElseThrow(() -> new CodeNotFoundException(code));
    }

    public final Equipment changeStatus(final String code, final ServiceStatus serviceStatus){

        Equipment equipment = fetchByCode(code);
        equipment.changeStatus(serviceStatus);

        return equipmentRepository.save(equipment);
    }

    public final Equipment appendComments(final CommentsPayload commentsPayload){

        Equipment equipment = fetchByCode(commentsPayload.getServiceCode());
        equipment.addComments(commentsPayload.getComments());
        return equipmentRepository.save(equipment);
    }



    private Equipment unwrapPayload(EquipmentPayload equipmentPayload){
        Category category = categoryRepository.findByName(equipmentPayload.getCategory()).orElseThrow(() -> new RuntimeException("Category not found."));
        Equipment equipment = Equipment.fromPayloadAndCategory(equipmentPayload,category);
        equipment.setServiceCode(generateNewCode());
        return equipment;
    }

    private String generateNewCode(){
        String code = RandomStringUtils.randomAlphabetic(3) + "-" + RandomStringUtils.randomNumeric(3);
        return  !equipmentRepository.existsByServiceCode(code) ? code : generateNewCode();
    }
}
