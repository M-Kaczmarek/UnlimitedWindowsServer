package pl.unlimited.windows.serwer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.WindowSizeApi;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.service.WindowFacade;
import pl.unlimited.windows.serwer.service.WindowSizeFacade;

import java.net.URI;
import java.util.List;

@RestController
public class WindowSizeController implements WindowSizeApi {

    private WindowSizeFacade windowSizeFacade;
    private WindowFacade windowFacade;

    public WindowSizeController(WindowSizeFacade windowSizeFacade, WindowFacade windowFacade) {
        this.windowSizeFacade = windowSizeFacade;
        this.windowFacade = windowFacade;
    }

    @Override
    public ResponseEntity<List<WindowDto>> getWindows() {
        return ResponseEntity.ok(windowFacade.findAllWindows());
    }

    @Override
    public ResponseEntity<WindowDto> createWindow(WindowDto windowDto) {
        WindowDto createdWindow = windowFacade.createWindow(windowDto);

        return ResponseEntity.created(URI.create("/api/windows/" + createdWindow.getId())).body(createdWindow);
    }

    @Override
    public ResponseEntity<WindowDto> getWindowById(Long id) {
        return ResponseEntity.ok(windowFacade.findWindowById(id));
    }

    @Override
    public ResponseEntity<List<WindowSizeDto>> getWindowSizes() {
        return ResponseEntity.ok(windowSizeFacade.findAllWindowSizes());
    }

    @Override
    public ResponseEntity<WindowSizeDto> createWindowSize(WindowSizeDto windowSizeDto) {
        WindowSizeDto createdWindowSize = windowSizeFacade.createWindowSize(windowSizeDto);

        return ResponseEntity.created(URI.create("/api/windows/sizes/" + createdWindowSize.getId())).body(createdWindowSize);
    }

    @Override
    public ResponseEntity<WindowSizeDto> getWindowSizeById(Long id) {
        return ResponseEntity.ok(windowSizeFacade.getWindowSizeById(id));
    }
}
