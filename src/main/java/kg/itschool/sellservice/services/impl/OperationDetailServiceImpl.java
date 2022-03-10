package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.OperationDetailRepo;
import kg.itschool.sellservice.mappers.OperationDetailMapper;
import kg.itschool.sellservice.models.dtos.operation.OperationDetailResponse;
import kg.itschool.sellservice.services.OperationDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationDetailServiceImpl implements OperationDetailService {
    private final OperationDetailRepo operationDetailRepo;

    public OperationDetailServiceImpl(OperationDetailRepo operationDetailRepo) {
        this.operationDetailRepo = operationDetailRepo;
    }

    @Override
    public void saveOperationDetails(List<OperationDetailResponse> operationDetailResponseList) {
        for (OperationDetailResponse operationDetailResponse: operationDetailResponseList) {
            operationDetailRepo.save(OperationDetailMapper.INSTANCE.operationDetailToOperationDetailResponse(operationDetailResponse));
        }
    }
}