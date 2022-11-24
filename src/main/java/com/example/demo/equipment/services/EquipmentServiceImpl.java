package com.example.demo.equipment.services;

import com.example.demo.equipment.models.Equipment;
import com.example.demo.equipment.repositories.EquipmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> findAllByProviderId(String providerId) {
        return equipmentRepository.findAllByProviderId(providerId);
    }

    @Override
    public Equipment deleteEquipment(String equipmentId) {
        Equipment equipment = findEquipmentById(equipmentId);
        equipment.delete();
        Equipment savedEquipment = saveEquipment(equipment);
        return savedEquipment;
    }

    @Override
    public Equipment findEquipmentById(String equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    @Override
    public Equipment saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment createNewEquipment(Equipment equipment, String providerId) {
        equipment.setProviderId(providerId);
        equipment.setId(UUID.randomUUID().toString());
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment saveEquipmentAfterUpdate(Equipment oldEquipment, String providerId, Equipment equipment) {
        oldEquipment.setProviderId(equipment.getProviderId());
        oldEquipment.setId(equipment.getId());
        oldEquipment.setImgScr(equipment.getImgScr());
        oldEquipment.setNameEquipment(equipment.getNameEquipment());
        oldEquipment.setPriceEquipment(equipment.getPriceEquipment());
        oldEquipment.setDescription(equipment.getDescription());
        return equipmentRepository.save(oldEquipment);
    }

    @Override
    public Equipment findById(String equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    @Override
    public Equipment getEquipmentById(String equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }


}
