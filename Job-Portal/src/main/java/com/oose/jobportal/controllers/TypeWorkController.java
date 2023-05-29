package com.oose.jobportal.controllers;

import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.dtos.TypeWorkDto;
import com.oose.jobportal.models.entities.TypeWork;
import com.oose.jobportal.models.mappers.TypeworkMapper;
import com.oose.jobportal.services.TypeWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/typework")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class TypeWorkController {
    private final TypeWorkService typeWorkService;

    @GetMapping("/find-typework")
    public ResponseEntity<?> getTypeWork(@Valid @RequestParam int id) {
        if (id != 0) {
            TypeWork typeWork = typeWorkService.findByID(id);
            TypeWorkDto typeWorkDto = TypeworkMapper.mappingToDto(typeWork);
            return ResponseEntity.ok(typeWorkDto);
        }

        List<TypeWork> typeWorkList = typeWorkService.findAll();
        List<TypeWorkDto> typeWorkDtoList = TypeworkMapper.mappingToListDto(typeWorkList);
        return ResponseEntity.ok(typeWorkDtoList);
    }
    @GetMapping("/find-all-type")
    public ResponseEntity<?> findAllType() {
        List<TypeWorkDto> typeWorkDtoList = TypeworkMapper.mappingToListDto(typeWorkService.findAll());
        return ResponseEntity.ok(typeWorkDtoList);
    }
}
