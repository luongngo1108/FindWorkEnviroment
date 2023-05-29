package com.oose.jobportal.models.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserWorkKey implements Serializable {
    private int userID;
    private int workID;
}
