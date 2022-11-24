package com.example.demo.equipment.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "equipment")
@NoArgsConstructor
public class Equipment {
    @Id
    private String id;
    private String providerId;
    private String imgScr;
    private String nameEquipment;
    private String priceEquipment;
    private String description;
    private boolean deleted;
    public Equipment(Equipment equipmentFromFrontEnd, String providerId) {
        this.id = UUID.randomUUID().toString();
        this.imgScr = equipmentFromFrontEnd.getImgScr();
        this.nameEquipment = equipmentFromFrontEnd.getNameEquipment();
        this.priceEquipment = equipmentFromFrontEnd.getPriceEquipment();
        this.description = equipmentFromFrontEnd.getDescription();
        this.providerId = providerId;
        this.deleted = false;
    }
    public void delete() {
        this.deleted = true;
    }
}
