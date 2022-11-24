package com.example.demo.equipment.services;

import com.example.demo.equipment.models.Equipment;

import java.util.List;

public interface EquipmentService {

    List<Equipment> findAllByProviderId(String providerId);

    Equipment deleteEquipment(String equipmentId);

    Equipment findEquipmentById(String equipmentId);

    Equipment saveEquipment(Equipment equipment);

    Equipment createNewEquipment(Equipment equipment, String providerId);


    Equipment saveEquipmentAfterUpdate(Equipment oldEquipment, String providerId, Equipment equipment);

    Equipment findById(String equipmentId);

    Equipment getEquipmentById(String equipmentId);
}
