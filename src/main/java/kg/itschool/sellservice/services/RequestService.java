package kg.itschool.sellservice.services;

import kg.itschool.sellservice.models.entities.Code;

public interface RequestService {
    void saveRequest(Code code, boolean value);

    int countOfFailed(Code code);
}
