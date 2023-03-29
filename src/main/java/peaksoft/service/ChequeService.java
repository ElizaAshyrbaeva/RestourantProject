package peaksoft.service;

import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.exceptions.NotFoundException;

import java.util.List;

public interface ChequeService {
    SimpleResponse save(ChequeRequest request) throws NotFoundException;

    List<ChequeResponse> getAll();

    ChequeResponse getById(Long id) throws NotFoundException;

    SimpleResponse update(Long id, ChequeRequest request) throws NotFoundException;

    SimpleResponse deleteById(Long id) throws NotFoundException;

    Double getAllChequesByUser(Long userId);

    Double getAverageSum(Long restId);
}