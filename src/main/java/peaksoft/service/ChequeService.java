package peaksoft.service;

import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface ChequeService {
    SimpleResponse save(ChequeRequest request);

    ChequeResponse findById(Long id);

    SimpleResponse deleteById(Long id);

    SimpleResponse update(Long id, ChequeRequest request);

    List<ChequeResponse> findAll();
    Double totalSum(Long id);

    Double avg(Long id);}
