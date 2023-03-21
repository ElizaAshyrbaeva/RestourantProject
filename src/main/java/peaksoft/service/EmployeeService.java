package peaksoft.service;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.request.EmployeeRequestToken;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import java.util.List;
public interface EmployeeService {
    SimpleResponse saveEmployee(EmployeeRequest employeeRequest);
    List <EmployeeResponse>getAll();
    EmployeeResponse findById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,EmployeeRequest request);
    SimpleResponse application(EmployeeRequest request);

    SimpleResponse application(Long id, Boolean accepted);



}
