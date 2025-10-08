package com.sergey.demoprojectspringboot.service.addService;

import com.sergey.demoprojectspringboot.dto.requestDto.RequestAddEmployeeDTO;
import com.sergey.demoprojectspringboot.dto.responceDto.ResponseEmployeeDTO;
import com.sergey.demoprojectspringboot.entity.ConfirmationCode;
import com.sergey.demoprojectspringboot.entity.Department;
import com.sergey.demoprojectspringboot.entity.Employee;
import com.sergey.demoprojectspringboot.exception.AlreadyExistException;
import com.sergey.demoprojectspringboot.exception.BadRequestException;
import com.sergey.demoprojectspringboot.exception.NotFoundException;
import com.sergey.demoprojectspringboot.repository.EmployeeRepositoryDataBase;
import com.sergey.demoprojectspringboot.service.util.EmployeeConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepositoryDataBase employeeRepository;
    private DepartmentService departmentService;
    private EmployeeConverter converter;
    private CodeConfirmationService codeConfirmationService;


    @Transactional
    public ResponseEmployeeDTO employeeRegistration(RequestAddEmployeeDTO request) {
        Optional<Employee> emailOptional = employeeRepository.findByEmail(request.getEmail());
        Optional<Department> departmentOptional = departmentService.findDepartmentByName(request.getDepartmentName());


        if (emailOptional.isPresent()) {
            throw new AlreadyExistException(" Email Already Exist ");
        }

        Department department = departmentOptional.get();

        Employee newEmployee = converter.fromDto(request);

        newEmployee.setRole(Employee.Role.USER);

        newEmployee.setStatus(Employee.Status.ACTIVE);

        addEmployeeToDepartment(department, newEmployee);

        newEmployee = employeeRepository.save(newEmployee);

        codeConfirmationService.confirmationCodeManager(newEmployee);

        return converter.toDto(newEmployee);


    }

    public void deactivateEmployee() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Employee Not Found"));

        employee.setStatus(Employee.Status.INACTIVE);
        employee.setDeactivateAt(LocalDateTime.now());

        employeeRepository.save(employee);
    }

    public void reactivateEmployee() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee employee = employeeRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Employee Not Found"));
        if (!employee.getStatus().equals(Employee.Status.INACTIVE)) {
            throw new AlreadyExistException("Employee Already Active");
        }
        employee.setStatus(Employee.Status.ACTIVE);
        employee.setDeactivateAt(null);
        employeeRepository.save(employee);


    }

    public ResponseEmployeeDTO deleteEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new NotFoundException(" Employee " + " with " + id + " id " + " not found ");
        }
        if(employeeOptional.get().getId() == 1){
            throw new BadRequestException("Employee " + " with " + id + " id " + " cannot be deleted");
        }

        Employee employee = employeeOptional.get();
        employeeRepository.delete(employee);

        return converter.toDto(employee);

    }

    public List<ResponseEmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<ResponseEmployeeDTO> responceEmployeeDTOList = employees.stream().map(employee -> new ResponseEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getRole(),employee.getStatus())).toList();
        if (responceEmployeeDTOList.isEmpty()) {
            throw new NotFoundException(" Employees not found ");
        }
        return responceEmployeeDTOList;
    }

//    public ResponseEmployeeDTO findById(Integer id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        if(employeeOptional.isEmpty()){
//            throw  new NotFoundException(" Employee with this" + id + " id not found ");
//        }
//        return converter.toDto(employeeOptional.get());
//    }

    public ResponseEmployeeDTO getUserById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee with id = " + id + " not found"));

        return converter.toDto(employee);
    }

    public List<ResponseEmployeeDTO> findByName(String name) {
        List<Employee> employeesOptionalList = employeeRepository.findByNameContainingIgnoreCase(name);
        List<ResponseEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getName().equalsIgnoreCase(name)).map(employee -> new ResponseEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getRole(), employee.getStatus())).toList();
        if (employeesOptionalList.isEmpty()) {
            throw new NotFoundException(" Employee with this  " + name + " name not found ");
        }
        return responceEmployeeDTOList;
    }

    public List<ResponseEmployeeDTO> findBySurname(String surname) {
        List<Employee> employeesOptionalList = employeeRepository.findBySurname(surname);
        List<ResponseEmployeeDTO> responceEmployeeDTOList = employeesOptionalList.stream().filter(employee -> employee.getSurname().equalsIgnoreCase(surname)).map(employee -> new ResponseEmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getEmail(), employee.getRole(), employee.getStatus())).toList();
        if (employeesOptionalList.isEmpty()) {
            throw new NotFoundException(" Employee with this  " + surname + " surname not found ");
        }
        return responceEmployeeDTOList;
    }

    public ResponseEmployeeDTO findByEmail(String email) {
        Optional<Employee> employeesOptional = employeeRepository.findByEmail(email);
        if (employeesOptional.isEmpty()) {
            throw new NotFoundException(" Employee with this " + email + " email not found ");
        }
        return converter.toDto(employeesOptional.get());
    }

    public List<ConfirmationCode> findCodesByEmployee(String email){
        Employee employee = getUserByEmailOrThrow(email);
        return codeConfirmationService.findCodesByUser(employee);

    }

    private Employee getUserByEmailOrThrow(String email){
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
    }


    public Optional<Employee> findByIdForService(Integer id) {
        return employeeRepository.findById(id);
    }

    public ResponseEmployeeDTO updateEmployeeNameById(Integer id, String name) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setName(name);

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }

    public ResponseEmployeeDTO updateEmployeeSurnameById(Integer id, String surname) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);
        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setSurname(surname);

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }

    public ResponseEmployeeDTO updateEmployeeEmailById(Integer id, String email) {
        Optional<Employee> employeeForUpdate = employeeRepository.findById(id);

        if (!isEmailAlreadyExist(email)) {
            throw new AlreadyExistException("Email already exist");
        }

        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee with this " + id + " id not found ");
        }

        Employee employee = employeeForUpdate.get();
        employee.setEmail(email);

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }

    private boolean isEmailAlreadyExist(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }

    public ResponseEmployeeDTO addEmployeeToDepartment(Department department, Employee employee) {
        Optional<Department> departmentOptional = departmentService.findDepartmentByIdForService(department.getId());
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException("Department with name " + department.getName() + " not found ");
        }
        Department departmentForInvite = departmentOptional.get();

        employee.setDepartment(departmentForInvite);

        departmentForInvite.getEmployees().add(employee);

        Employee employeeSave = employeeRepository.save(employee);

        return converter.toDto(employeeSave);

    }

    public ResponseEmployeeDTO addEmployeeToAnotherDepartment(Integer departmentId, Integer employeeId) {
        Optional<Employee> employeeForUpdate = findByIdForService(employeeId);
        Optional<Department> departmentOptional = departmentService.findDepartmentByIdForService(departmentId);

        if (employeeForUpdate.isEmpty()) {
            throw new NotFoundException(" Employee " + " with " + employeeId + " id " + " not found ");
        }
        if (departmentOptional.isEmpty()) {
            throw new NotFoundException(" Department " + " with " + departmentId + " id " + " not found ");
        }

        Employee employee = employeeForUpdate.get();

        employee.setDepartment(departmentOptional.get());

        employeeRepository.save(employee);

        return converter.toDto(employee);
    }



}







