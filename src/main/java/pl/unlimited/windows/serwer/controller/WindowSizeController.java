package pl.unlimited.windows.serwer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.WindowSizeApi;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.service.WindowSizeFacade;

import java.net.URI;
import java.util.List;

@RestController
public class WindowSizeController implements WindowSizeApi {

    private WindowSizeFacade windowSizeFacade;

    public WindowSizeController(WindowSizeFacade windowSizeFacade) {
        this.windowSizeFacade = windowSizeFacade;
    }

    @Override
    public ResponseEntity<List<WindowSizeDto>> getWindowSizes() {
        return ResponseEntity.ok(windowSizeFacade.getWindowSizes());
    }

    @Override
    public ResponseEntity<WindowSizeDto> createWindowSize(WindowSizeDto windowSizeDto) {
        WindowSizeDto createdWindowSize = windowSizeFacade.createWindowSize(windowSizeDto);
        return ResponseEntity.created(URI.create("/api/windows/sizes/" + createdWindowSize.getId())).body(createdWindowSize); //TODO status code could be created and return URI
    }

    @Override
    public ResponseEntity<WindowSizeDto> getWindowSizeById(Long id) {
        return ResponseEntity.ok(windowSizeFacade.getWindowSizeById(id));
    }
}
