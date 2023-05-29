package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.Work;
import com.oose.jobportal.repositories.WorkRepo;
import com.oose.jobportal.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    WorkRepo workRepo;
    @Override
    public List<Work> findTop10Work(Date current) {
        return workRepo.findTop10Work(current);
    }

    @Override
    public Work save(Work work) {
        return workRepo.save(work);
    }

    @Override
    public List<Work> findAll() {
        return workRepo.findAll();
    }
}
