package com.oose.jobportal.services;

import com.oose.jobportal.models.entities.DetailWork;

import java.util.List;

public interface DetailWorkService {
    DetailWork findByID(int id);

    List<DetailWork> findAll();

    DetailWork save(DetailWork detailWork);
}
