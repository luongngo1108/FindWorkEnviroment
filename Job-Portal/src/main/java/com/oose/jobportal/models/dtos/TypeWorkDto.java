package com.oose.jobportal.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class TypeWorkDto {
    private int typeworkID;
    private String nametypework;
    private int price;
}
