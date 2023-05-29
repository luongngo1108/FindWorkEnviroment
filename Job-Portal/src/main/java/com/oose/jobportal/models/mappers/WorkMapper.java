package com.oose.jobportal.models.mappers;

import com.oose.jobportal.models.dtos.WorkDto;
import com.oose.jobportal.models.entities.Work;
import com.oose.jobportal.services.DetailWorkService;
import com.oose.jobportal.services.TypeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkMapper {
    private static DetailWorkService detailWorkService;
    private static TypeWorkService typeWorkService;

    @Autowired
    public WorkMapper(DetailWorkService detailWorkService, TypeWorkService typeWorkService) {
        WorkMapper.detailWorkService = detailWorkService;
        WorkMapper.typeWorkService = typeWorkService;
    }

    public static WorkDto mappingToDto(Work work) {
        WorkDto workDto = new WorkDto();

        if(work != null) {
            workDto.setWorkID(work.getWorkID());
            workDto.setDate(work.getDate());
            workDto.setQuantity(work.getQuantity());
            workDto.setWorkname(work.getWorkname());
            workDto.setDetailworkID(work.getDetailwork().getDetailworkID());
            workDto.setTypeworkID(work.getType_work().getTypeworkID());
            workDto.setImage(work.getImage());
            workDto.setInvolved(work.getInvolved());
            workDto.setCity(work.getCity());
            workDto.setDistrict(work.getDistrict());
            workDto.setAddress(work.getAddress());
        } else {
            workDto.setWorkID(-1);
            workDto.setDate(null);
            workDto.setQuantity(0);
            workDto.setWorkname("");
            workDto.setDetailworkID(0);
            workDto.setTypeworkID(0);
            workDto.setImage("");
            workDto.setInvolved(0);
            workDto.setCity("");
            workDto.setDistrict("");
            workDto.setAddress("");
        }

        return workDto;
    }

    public static List<WorkDto> mappingToListWork(List<Work> workList) {
        List<WorkDto> workDtoList = new ArrayList<>();

        if (workList == null)
        {
            workDtoList.add(mappingToDto(null));
            return workDtoList;
        }

        for(Work work : workList) {
            workDtoList.add(mappingToDto(work));
        }

        return workDtoList;
    }

    public static Work mappingToEntity(WorkDto workDto) {
        Work work = new Work();

        if (workDto != null) {
            work.setDate(workDto.getDate());
            work.setQuantity(workDto.getQuantity());
            work.setWorkname(workDto.getWorkname());
            work.setDetailwork(detailWorkService.findByID(workDto.getDetailworkID()));
            work.setType_work(typeWorkService.findByID(workDto.getTypeworkID()));
            work.setCity(workDto.getCity());
            work.setDistrict(workDto.getDistrict());
            work.setAddress(workDto.getAddress());
        } else {
            work.setDate(null);
            work.setQuantity(0);
            work.setWorkname("");
            work.setDetailwork(null);
            work.setType_work(null);
            work.setCity("");
            work.setDistrict("");
            work.setAddress("");
        }

        return work;
    }
}
