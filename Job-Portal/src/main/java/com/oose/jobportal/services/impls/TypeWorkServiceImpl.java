package com.oose.jobportal.services.impls;

import com.oose.jobportal.models.entities.TypeWork;
import com.oose.jobportal.repositories.TypeWorkRepo;
import com.oose.jobportal.services.TypeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeWorkServiceImpl implements TypeWorkService {

    @Autowired
    TypeWorkRepo typeWorkRepo;

    @Override
    public TypeWork findByID(int id) {
        return typeWorkRepo.findById(id).orElseThrow();
    }

    @Override
    public List<TypeWork> findAll() {
        return typeWorkRepo.findAll();
    }
}
