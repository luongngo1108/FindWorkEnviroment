package com.oose.jobportal.controllers.client;

import com.oose.jobportal.constants.Domain;
import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.dtos.TypeWorkDto;
import com.oose.jobportal.models.dtos.WorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.models.entities.TypeWork;
import com.oose.jobportal.models.entities.Work;
import com.oose.jobportal.models.mappers.DetailWorkMapper;
import com.oose.jobportal.models.mappers.TypeworkMapper;
import com.oose.jobportal.models.mappers.WorkMapper;
import com.oose.jobportal.services.DetailWorkService;
import com.oose.jobportal.services.TypeWorkService;
import com.oose.jobportal.services.WorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/client/client")
@CrossOrigin(Domain.CROSS_ORIGIN)
@RequiredArgsConstructor
public class ClientController {
    @GetMapping("/checkPermit")
    public ResponseEntity<?> checkPermit() {
        return ResponseEntity.ok(true);
    }
}
