package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.repositories.DetailWorkRepo;
import com.oose.jobportal.services.DetailWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailWorkServiceImpl implements DetailWorkService {
    @Autowired
    DetailWorkRepo detailWorkRepo;

    @Override
    public DetailWork findByID(int id) {
        return detailWorkRepo.findById(id).orElse(null);
    }

    @Override
    public List<DetailWork> findAll() {
        return detailWorkRepo.findAll();
    }

    @Override
    public DetailWork save(DetailWork detailWork) {
        return detailWorkRepo.save(detailWork);
    }
}
