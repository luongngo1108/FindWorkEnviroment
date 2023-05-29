package com.oose.jobportal.models.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class PaymentDto {
    private int paymentID;
    private Date time;
    private int paymentmethodID;
}
