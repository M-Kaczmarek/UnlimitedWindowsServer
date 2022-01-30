package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.WindowSizeNotFoundException;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.repository.WindowSizeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WindowSizeFacade {

    private WindowSizeRepository windowSizeRepository;
    private CreateWindowSize createWindowSize;
    private UpdateWindowSizeService updateWindowSizeService;

    public WindowSizeFacade(WindowSizeRepository windowSizeRepository, CreateWindowSize createWindowSize, UpdateWindowSizeService updateWindowSizeService) {
        this.windowSizeRepository = windowSizeRepository;
        this.createWindowSize = createWindowSize;
        this.updateWindowSizeService = updateWindowSizeService;
    }

    public List<WindowSizeDto> findAllWindowSizes() {
        return windowSizeRepository.findAll().stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

    public WindowSizeDto createWindowSize(final WindowSizeDto windowSizeDto) {
        return createWindowSize.createWindowDto(WindowSizeDomain.fromDto(windowSizeDto));
    }

    public void updateWindowSize(final Long id, final WindowSizeDto windowSizeDto) {
        updateWindowSizeService.updateWindowSize(id, WindowSizeDomain.fromDto(windowSizeDto));
    }

    public WindowSizeDto getWindowSizeById(Long id) {
        WindowSize windowSize = windowSizeRepository.findById(id).orElseThrow(() -> new WindowSizeNotFoundException("Window is with id: " + id + " not found", ErrorCode.WINDOW_SIZE_NOT_FOUND));

        return windowSize.toDto();
    }
}
