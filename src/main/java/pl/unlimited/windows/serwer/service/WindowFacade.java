package pl.unlimited.windows.serwer.service;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.WindowNotFoundException;
import pl.unlimited.windows.serwer.model.Window;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.repository.WindowRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WindowFacade {

    private WindowRepository windowRepository;
    private CreateWindowService createWindowService;
    private UpdateWindowService updateWindowService;

    public WindowFacade(WindowRepository windowRepository, CreateWindowService createWindowService, UpdateWindowService updateWindowService) {
        this.windowRepository = windowRepository;
        this.createWindowService = createWindowService;
        this.updateWindowService = updateWindowService;
    }

    public List<WindowDto> findAllWindows() {
        return windowRepository.findAll().stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    public WindowDto findWindowById(final Long id) {
        Window window = windowRepository.findById(id).orElseThrow(() -> new WindowNotFoundException("Window with id: " + id + " not found", ErrorCode.WINDOW_NOT_FOUND));
        return window.toDto();
    }

    public WindowDto createWindow(final WindowDto windowDto) {
        return createWindowService.createWindow(WindowDomain.fromDto(windowDto));
    }

    public void updateWindows(Long id, WindowDto windowDto) {
        updateWindowService.updateWindow(id, WindowDomain.fromDto(windowDto));
    }
}
