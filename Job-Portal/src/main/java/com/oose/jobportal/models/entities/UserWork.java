package com.oose.jobportal.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_work")
public class UserWork {
    @EmbeddedId
    private UserWorkKey userworkKey = new UserWorkKey();

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(nullable = false, name = "userID")
    private User user;

    @ManyToOne
    @MapsId("workID")
    @JoinColumn(nullable = false, name = "workID")
    private Work work;
}
