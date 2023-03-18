package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;

import java.util.NoSuchElementException;

@Service
@Transactional
public class StopListServiceImpl implements StopListService {
    private final StopListRepository repository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public StopListServiceImpl(StopListRepository repository, MenuItemRepository menuItemRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse save(Long id, StopListRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND!!!"));
        StopList stopList = new StopList();
        stopList.setReason(request.reason());
        stopList.setDate(request.date());
        stopList.setMenuitem(menuItem);
        repository.save(stopList);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Save...").build();
    }
}
