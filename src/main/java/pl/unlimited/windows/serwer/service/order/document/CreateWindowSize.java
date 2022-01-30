package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.repository.WindowSizeRepository;

@Component
class CreateWindowSize {

    private WindowSizeRepository windowSizeRepository;

    public CreateWindowSize(WindowSizeRepository windowSizeRepository) {
        this.windowSizeRepository = windowSizeRepository;
    }

    public WindowSizeDto createWindowDto(WindowSizeDomain windowSizeDomain) {
        WindowSize windowSizeEntity = WindowSizeDomain.toEntityWindowSize(windowSizeDomain);
        WindowSize createdWindowSize = windowSizeRepository.save(windowSizeEntity);

        return createdWindowSize.toDto();
    }
}
