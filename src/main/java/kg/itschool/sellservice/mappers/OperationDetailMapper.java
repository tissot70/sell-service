package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.operation.OperationDetailResponse;
import kg.itschool.sellservice.models.entities.OperationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationDetailMapper {
    OperationDetailMapper INSTANCE = Mappers.getMapper(OperationDetailMapper.class);

    OperationDetailResponse operationDetailToOperationDetailResponse(OperationDetail operationDetail);

    OperationDetail operationDetailToOperationDetailResponse (OperationDetailResponse operationDetailResponse);

}
