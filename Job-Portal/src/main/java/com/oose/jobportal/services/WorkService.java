package com.oose.jobportal.services;

import com.oose.jobportal.models.entities.Work;

import java.util.Date;
import java.util.List;

public interface WorkService {
    List<Work> findTop10Work(Date current);
    Work save(Work work);

    List<Work> findAll();
}
