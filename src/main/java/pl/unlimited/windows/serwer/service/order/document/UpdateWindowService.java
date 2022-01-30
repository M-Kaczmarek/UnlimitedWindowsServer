package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.WindowNotFoundException;
import pl.unlimited.windows.serwer.repository.WindowRepository;

@Component
class UpdateWindowService {

    private WindowRepository windowRepository;

    public UpdateWindowService(WindowRepository windowRepository) {
        this.windowRepository = windowRepository;
    }

    public void updateWindow(final Long id, final WindowDomain windowDomain) {
        windowRepository.findById(id).orElseThrow(() -> new WindowNotFoundException("Window with id: " + id + " not found", ErrorCode.WINDOW_NOT_FOUND));

        windowDomain.setId(id);
        windowRepository.save(WindowDomain.toEntityWindow(windowDomain));
    }
}
