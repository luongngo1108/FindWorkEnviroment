package com.oose.jobportal.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class WorkDto {
    private int workID;
    private Date date;
    @Min(1)
    private int quantity;
    @NotEmpty
    private String workname;
    private int detailworkID;
    private int typeworkID;
    private String image;
    private int involved;
    private String city;
    private String district;
    private String address;
}
