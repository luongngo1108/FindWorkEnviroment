package com.oose.jobportal.services;

import com.oose.jobportal.models.entities.TypeWork;

import java.util.List;

public interface TypeWorkService {
    TypeWork findByID(int id);

    List<TypeWork> findAll();
}
