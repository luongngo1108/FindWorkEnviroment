package com.oose.jobportal.models.mappers;

import com.oose.jobportal.models.dtos.DetailWorkDto;
import com.oose.jobportal.models.entities.DetailWork;
import com.oose.jobportal.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DetailWorkMapper {
    private static PaymentService paymentService;

    @Autowired
    public DetailWorkMapper(PaymentService paymentService) {
        DetailWorkMapper.paymentService = paymentService;
    }

    public static DetailWorkDto mappingToDto(DetailWork detailWork) {
        DetailWorkDto detailWorkDto = new DetailWorkDto();

        if (detailWork != null) {
            detailWorkDto.setDetailworkID(detailWork.getDetailworkID());
            detailWorkDto.setDescription(detailWork.getDescription());
            detailWorkDto.setHours(detailWork.getHours());
            detailWorkDto.setIncome(detailWork.getIncome());
            detailWorkDto.setPaymentID(detailWork.getPayment().getPaymentID());
            detailWorkDto.setContact(detailWork.getContact());
        } else {
            detailWorkDto.setDetailworkID(-1);
            detailWorkDto.setDescription("");
            detailWorkDto.setHours(0);
            detailWorkDto.setIncome(0);
            detailWorkDto.setPaymentID(-1);
            detailWorkDto.setContact("");
        }

        return detailWorkDto;
    }

    public static List<DetailWorkDto> mappingToListDto(List<DetailWork> detailWorkList) {
        List<DetailWorkDto> detailWorkDtoList = new ArrayList<>();

        if (detailWorkList == null)
        {
            detailWorkDtoList.add(mappingToDto(null));
            return detailWorkDtoList;
        }

        for(DetailWork detailWork : detailWorkList) {
            detailWorkDtoList.add(mappingToDto(detailWork));
        }

        return detailWorkDtoList;
    }

    public static DetailWork mappingToEntity(DetailWorkDto detailWorkDto) {
        DetailWork detailWork = new DetailWork();

        if (detailWorkDto != null) {
            detailWork.setContact(detailWorkDto.getContact());
            detailWork.setDescription(detailWorkDto.getDescription());
            detailWork.setHours(detailWorkDto.getHours());
            detailWork.setIncome(detailWorkDto.getIncome());
            detailWork.setPayment(paymentService.findByID(detailWorkDto.getPaymentID()));
        } else {
            detailWork.setContact("");
            detailWork.setDescription("");
            detailWork.setHours(0);
            detailWork.setIncome(0);
        }

        return detailWork;
    }
}
