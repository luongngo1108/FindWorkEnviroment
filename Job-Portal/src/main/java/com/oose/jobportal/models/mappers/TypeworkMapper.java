package com.oose.jobportal.models.mappers;

import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.dtos.TypeWorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.models.entities.TypeWork;

import java.util.ArrayList;
import java.util.List;

public class TypeworkMapper {
    public static TypeWorkDto mappingToDto(TypeWork typeWork) {
        TypeWorkDto typeWorkDto = new TypeWorkDto();

        if (typeWork == null) {
            typeWorkDto.setTypeworkID(-1);
            typeWorkDto.setNametypework("");
            typeWorkDto.setPrice(0);
            return typeWorkDto;
        }

        typeWorkDto.setTypeworkID(typeWork.getTypeworkID());
        typeWorkDto.setNametypework(typeWork.getNametypework());
        typeWorkDto.setPrice(typeWork.getPrice());

        return typeWorkDto;
    }

    public static List<TypeWorkDto> mappingToListDto(List<TypeWork> typeWorkList) {
        List<TypeWorkDto> typeWorkDtoList = new ArrayList<>();

        if (typeWorkList == null)
        {
            typeWorkDtoList.add(mappingToDto(null));
            return typeWorkDtoList;
        }

        for(TypeWork typeWork : typeWorkList) {
            typeWorkDtoList.add(mappingToDto(typeWork));
        }
        return typeWorkDtoList;
    }
}
