package kg.itschool.sellservice.mappers;

import kg.itschool.sellservice.models.dtos.code.CodeResponse;
import kg.itschool.sellservice.models.entities.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {
    CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);

    CodeResponse codeToCodeResponse(Code code);
    Code codeResponseToCode(CodeResponse codeResponse);
}
