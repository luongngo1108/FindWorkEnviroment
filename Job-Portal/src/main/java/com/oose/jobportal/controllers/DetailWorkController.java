package com.oose.jobportal.controllers;

import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.models.mappers.DetailWorkMapper;
import com.oose.jobportal.services.DetailWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/detailwork")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class DetailWorkController {
    private final DetailWorkService detailWorkService;

    @GetMapping("/find-all-detailwork")
    public ResponseEntity<?> getAllDetailWork() {
        List<DetailWorkDto> detailWorkDtoList = DetailWorkMapper.mappingToListDto(detailWorkService.findAll());

        return ResponseEntity.ok(detailWorkDtoList);
    }

    @GetMapping("/find-detailwork")
    public ResponseEntity<?> getDetailWork(@Valid @RequestParam int id) {
        if (id != 0) {
            DetailWork detailWork = detailWorkService.findByID(id);
            DetailWorkDto detailWorkDto = DetailWorkMapper.mappingToDto(detailWork);

            return ResponseEntity.ok(detailWorkDto);
        }
        List<DetailWork> detailWorkList = detailWorkService.findAll();
        List<DetailWorkDto> detailWorkDtoList = DetailWorkMapper.mappingToListDto(detailWorkList);

        return ResponseEntity.ok(detailWorkDtoList);
    }
}
