package com.oose.jobportal.controllers.client;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.dtos.PaymentDto;
import com.oose.jobportal.models.dtos.WorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.models.entities.Payment;
import com.oose.jobportal.models.entities.Work;
import com.oose.jobportal.models.mappers.DetailWorkMapper;
import com.oose.jobportal.models.mappers.PaymentMapper;
import com.oose.jobportal.models.mappers.WorkMapper;
import com.oose.jobportal.services.DetailWorkService;
import com.oose.jobportal.services.PaymentService;
import com.oose.jobportal.services.TypeWorkService;
import com.oose.jobportal.services.WorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client/work")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class CWorkController {
    private final WorkService workService;

    @PostMapping("/createWork")
    public ResponseEntity<?> createWork( @RequestBody WorkDto workDto) {
        Work work = WorkMapper.mappingToEntity(workDto);

        return ResponseEntity.ok(workService.save(work).getWorkID());
    }
}