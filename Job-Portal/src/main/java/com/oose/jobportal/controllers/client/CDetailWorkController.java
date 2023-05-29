package com.oose.jobportal.controllers.client;

import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.models.mappers.DetailWorkMapper;
import com.oose.jobportal.services.DetailWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client/detailwork")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class CDetailWorkController {
    @Autowired
    private DetailWorkService detailWorkService;

    @PostMapping("/createDetail")
    public ResponseEntity<?> createDetailWork( @RequestBody DetailWorkDto detailWorkDto) {

        DetailWork detailWork = DetailWorkMapper.mappingToEntity(detailWorkDto);

        return ResponseEntity.ok(detailWorkService.save(detailWork).getDetailworkID());
    }
}
