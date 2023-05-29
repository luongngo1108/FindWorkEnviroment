package com.oose.jobportal.models.dtos;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class DetailWorkDto {
    private int detailworkID;
    private String description;
    @Min(1)
    private int hours;
    @Min(1)
    private int income;
    private int paymentID;
    private String contact;
}
