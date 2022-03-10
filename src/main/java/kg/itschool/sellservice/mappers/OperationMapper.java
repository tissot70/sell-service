package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.operation.OperationResponse;
import kg.itschool.sellservice.models.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationResponse operationToOperationResponse(Operation operation);

    Operation operationResponseToOperation(OperationResponse operationResponse);
}
