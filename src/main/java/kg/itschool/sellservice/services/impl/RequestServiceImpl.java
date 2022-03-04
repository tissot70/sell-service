package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.dao.RequestRepo;
import kg.itschool.sellservice.models.entities.Code;
import kg.itschool.sellservice.models.entities.Request;
import kg.itschool.sellservice.services.RequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public void saveRequest(Code code, boolean value) {
        Request request = new Request();
        request.setAddDate(LocalDateTime.now());
        request.setCode(code);
        request.setSuccess(value);
    }

    @Override
    public int countOfFailed(Code code) {
        return requestRepo.countByCodeAndSuccess(code, false);
    }
}
