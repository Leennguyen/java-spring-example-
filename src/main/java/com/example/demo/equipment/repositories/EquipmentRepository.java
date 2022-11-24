package com.example.demo.equipment.repositories;

import com.example.demo.equipment.models.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends MongoRepository<Equipment, String>
{
    List<Equipment> findAllByProviderId(String providerId);

    Optional<Equipment> findById(String equipmentId);

    Equipment save(Equipment equipment);
}
