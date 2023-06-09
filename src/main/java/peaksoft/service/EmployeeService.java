package peaksoft.service;
import peaksoft.dto.request.AcceptOrRejectRequest;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.EmployeeAllResponse;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import java.util.List;
public interface EmployeeService {
    List <EmployeeAllResponse>getAll();
    EmployeeAllResponse findById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,EmployeeRequest request);
    SimpleResponse acceptOrReject(EmployeeRequest request);
    SimpleResponse acceptOrReject(AcceptOrRejectRequest acceptOrRejectRequest);
    SimpleResponse save(EmployeeRequest request,Long id);
    List<EmployeeAllResponse> getApplications();
    List<EmployeeAllResponse>findAll();
}
