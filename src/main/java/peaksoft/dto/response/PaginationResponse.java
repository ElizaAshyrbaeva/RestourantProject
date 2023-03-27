package peaksoft.dto.response;

import lombok.Builder;
import lombok.Data;
import peaksoft.dto.request.CategoryRequest;

import java.util.List;
@Data
public class PaginationResponse {
    private List<CategoryRequest> getAll;
    private int currentPage;
    private int pageSize;
    }
