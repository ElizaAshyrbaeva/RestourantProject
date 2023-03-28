package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exceptions.NotFoundException;
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
        boolean exists = repository.existsByMenuitem(menuItem);
        if (exists && menuItem.getList().getDate().equals(request.date())){
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage(String.format("bbds"+menuItem.getName())).build();
        }
            StopList stopList = new StopList();
            stopList.setReason(request.reason());
            stopList.setDate(request.date());
            stopList.setMenuitem(menuItem);
            repository.save(stopList);
            return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved ").build();

        }

    @Override
    public List<StopList> getAll() {
        return repository.findAll();
    }
    @Override
    public SimpleResponse delete(Long id) {
        repository.findById(id).orElseThrow(()->new NotFoundException("StopList with this id not found:"+id));
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("delete").build();
    }
    @Override
    public SimpleResponse update(Long id, StopListRequest request) {
        StopList list = repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("This Id : %s not found",id)));
        MenuItem menuItem = menuItemRepository.findById(request.menuId()).orElseThrow(() -> new NoSuchElementException(String.format("Menu item id : %s " + request.menuId() + " is no exist!")));
        list.setMenuitem(menuItem);
        list.setReason(request.reason());
       list.setDate(request.date());
       repository.save(list);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully update!").build();
    }

    @Override
    public StopList findById(Long id) {
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException(String.format
                        ("This Id: %s not found! ",id)));
    }

}
