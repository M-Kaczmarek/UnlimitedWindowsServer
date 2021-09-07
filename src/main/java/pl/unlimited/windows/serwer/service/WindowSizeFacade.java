package pl.unlimited.windows.serwer.service;

import org.springframework.stereotype.Component;
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

    public WindowSizeFacade(WindowSizeRepository windowSizeRepository, CreateWindowSize createWindowSize) {
        this.windowSizeRepository = windowSizeRepository;
        this.createWindowSize = createWindowSize;
    }

    public List<WindowSizeDto> getWindowSizes(){
        List<WindowSize> sizeRepositoryAll = windowSizeRepository.findAll();
        List<WindowSizeDto> windowSizeDtos = sizeRepositoryAll.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
        return windowSizeDtos;
    }

    public WindowSizeDto createWindowSize(final WindowSizeDto windowSizeDto){
        return createWindowSize.createWindowDto(WindowSizeDomain.fromDto(windowSizeDto));
    }

    public WindowSizeDto getWindowSizeById(Long id) {
        WindowSize windowSize = windowSizeRepository.findById(id).orElseThrow(() -> new WindowSizeNotFoundException("Window is with id: " + id + " not found", ErrorCode.WINDOW_SIZE_NOT_FOUND));

        return windowSize.toDto();
    }
}
