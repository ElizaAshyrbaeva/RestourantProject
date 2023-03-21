package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class StopListServiceImpl implements StopListService {
    private final StopListRepository repository;
    private final MenuItemRepository menuItemRepository;

    public StopListServiceImpl(StopListRepository repository, MenuItemRepository menuItemRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse save(Long id,StopListRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("not found"));
            StopList stopList = new StopList();
            stopList.setReason(request.reason());
            stopList.setDate(request.date());
            stopList.setMenuitem(menuItem);
            repository.save(stopList);
            return SimpleResponse.builder().status(HttpStatus.OK).massage(String.format("Successfully saved %s"+menuItem.getName())).build();

    }

    @Override
    public List<StopListResponse> getAll() {
        return repository.getAllList();
    }

    @Override
    public SimpleResponse delete(Long id) {
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("delete").build();
    }

    @Override
    public SimpleResponse update(Long id, StopListRequest request) {
        StopList list = repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("This Id : %s not found",id)));
       list.setReason(request.reason());
       list.setDate(request.date());
       repository.save(list);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully update!").build();
    }

    @Override
    public StopListResponse findById(Long id) {
        return repository.findByIdList(id).orElseThrow(()->new NoSuchElementException(String.format("This ID: %s not found! ",id)));
    }

}
