package com.example.demo.equipment.controllers;

import com.example.demo.equipment.models.Equipment;
import com.example.demo.equipment.services.EquipmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class EquipmentController {
    private final EquipmentServiceImpl equipmentService;
    @GetMapping("providers/{provider-id}/equipments")
    public ResponseEntity<List<Equipment>> getAllProviderEquipments(
        @PathVariable(value = "provider-id") String providerId
    )
    {
        return new ResponseEntity<>(equipmentService.findAllByProviderId(providerId), HttpStatus.OK);
    }
    @GetMapping("/providers/{provider-id}/equipments/{equipment-id}")
    public ResponseEntity<Equipment> getEquipmentById(
            @PathVariable(value = "provider-id") String providerId,
            @PathVariable(value = "equipment-id") String equipmentId
    )
    {
        Equipment equipmentOptional = equipmentService.findById(equipmentId);
        if (equipmentOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Equipment getEquipmentById = equipmentService.getEquipmentById(equipmentId);
        return new ResponseEntity<>(getEquipmentById, HttpStatus.OK);
    }

    @DeleteMapping(value = "/providers/{provider-id}/equipments/{equipment-id}")
    public ResponseEntity<Object> deleteEquipment(
            @PathVariable(value = "provider-id") String providerId,
            @PathVariable(value = "equipment-id") String equipmentId
    ) {
        Equipment deleteEquipment = equipmentService.deleteEquipment(equipmentId);
        return new ResponseEntity<>(deleteEquipment, HttpStatus.OK);
    }

    @PostMapping(value = "/providers/{provider-id}/equipments")
    public ResponseEntity createNewEquipment(
            @PathVariable(value = "provider-id") String providerId,
            @RequestBody Equipment equipment
    ){
        Equipment savedEquipment = equipmentService.createNewEquipment(equipment, providerId);
        return new ResponseEntity<>(savedEquipment, HttpStatus.OK);
    }

    @PutMapping(value = "/providers/{provider-id}/equipments/{equipment-id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable(value = "provider-id") String providerId,
            @PathVariable(value = "equipment-id") String equipmentId,
            @RequestBody Equipment equipment
    )
    {
        Equipment oldEquipment = equipmentService.findEquipmentById(equipmentId);
        if (oldEquipment.getProviderId().equals(providerId)) {
            Equipment updatedEquipment = equipmentService.saveEquipmentAfterUpdate(oldEquipment, providerId, equipment);
            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
