package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import peaksoft.dto.request.CategoryRequest;

import java.util.List;
@Data
@AllArgsConstructor
public class PaginationResponse {
    private List<MenuItemResponse> getAll;
    private int currentPage;
    private int pageSize;

    public PaginationResponse() {

    }
}
