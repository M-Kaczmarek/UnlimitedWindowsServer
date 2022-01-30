package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.repository.WindowRepository;

@Component
class CreateWindowService {
    private WindowRepository windowRepository;

    public CreateWindowService(WindowRepository windowRepository) {
        this.windowRepository = windowRepository;
    }

    public WindowDto createWindow(WindowDomain windowDomain) {
        return windowRepository.save(WindowDomain.toEntityWindow(windowDomain)).toDto();
    }
}
