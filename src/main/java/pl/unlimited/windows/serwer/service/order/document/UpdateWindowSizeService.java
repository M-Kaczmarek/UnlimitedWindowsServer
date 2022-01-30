package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.WindowSizeNotFoundException;
import pl.unlimited.windows.serwer.repository.WindowSizeRepository;

@Component
class UpdateWindowSizeService {

    private WindowSizeRepository windowSizeRepository;

    public UpdateWindowSizeService(WindowSizeRepository windowSizeRepository) {
        this.windowSizeRepository = windowSizeRepository;
    }

    public void updateWindowSize(final Long id, final WindowSizeDomain windowSizeDomain) {
        windowSizeRepository.findById(id)
                .orElseThrow(() ->
                        new WindowSizeNotFoundException("Window is with id: " + id + " not found", ErrorCode.WINDOW_SIZE_NOT_FOUND));
        windowSizeDomain.setId(id);
        windowSizeRepository.save(WindowSizeDomain.toEntityWindowSize(windowSizeDomain));
    }
}
