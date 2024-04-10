package com.example.healthcare_api.dto;

import lombok.Data;

@Data
public class ResultMedicineDTO {
    private Long resultId;
    private Long medicineId;
    private Integer quantity;
    private String description;

    private ResultDTO result;
    private MedicineDTO medicine;
}
