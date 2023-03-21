package peaksoft.service;

import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;

import java.time.LocalDate;
import java.util.List;

public interface ChequeService {
    SimpleResponse save(ChequeRequest request);

    ChequeResponse findById(Long id);

    SimpleResponse deleteById(Long id);

    SimpleResponse update(Long id, ChequeRequest request);

    List<ChequeResponse> findAll();
    SimpleResponse totalSum(Long id,LocalDate date);

    SimpleResponse avg(LocalDate date);
}
