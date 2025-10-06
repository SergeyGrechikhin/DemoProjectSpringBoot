package com.sergey.demoprojectspringboot.service.addService;

import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.ConfirmationCodeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Service
public class CodeConfirmationService {

    private final ConfirmationCodeRepository repository;

    private final int EXPIRATION_PERIOD = 1;

    private final String LINK_PATH = "localhost:8080/api/users/code/confirmation?code=";

    public void confirmationCodeManager(Employee employee) {
        String code = generateCode();
        saveConfirmationCode(code, employee);
        sendCodeByEmail(code, employee);
    }

    private void sendCodeByEmail(String code, Employee employee) {

        String linkToSend = LINK_PATH + code;

        // TODO тут будет отправка пользователю письма с кодом

        System.out.printf("Код подтверждения: " + linkToSend);


    }

    private void saveConfirmationCode(String generatedCode, Employee employee) {
        ConfirmationCode newCode = ConfirmationCode.builder()
                .code(generatedCode)
                .employee(employee)
                .expireDataTime(LocalDateTime.now().plusDays(EXPIRATION_PERIOD))
                .isConfirmed(false)
                .build();

        repository.save(newCode);
    }

    private String generateCode() {

        // universal uniq identifier
        // формат 128 bit
        // xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        // где каждый символ 'x' - это либо цифра либо символ от a-f
        // 3f29c3b2-9fc2-11ed-a8fc-0242ac120002

        return UUID.randomUUID().toString();

    }

    public Employee changeConfirmationStatusByCode(String code){
        ConfirmationCode confirmationCode = repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Confirmation code: " + code + " not found"));

        Employee employee = confirmationCode.getEmployee();

        confirmationCode.setConfirmed(true);

        repository.save(confirmationCode);

        return employee;
    }

    public List<ConfirmationCode> findCodesByUser(Employee employee) {
        return repository.findByEmployee(employee);
    }


}
