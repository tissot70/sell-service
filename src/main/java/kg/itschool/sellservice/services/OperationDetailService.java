package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.dtos.operation.OperationDetailResponse;

import java.util.List;

public interface OperationDetailService {

    void saveOperationDetails(List<OperationDetailResponse> operationDetailResponseList);
}
