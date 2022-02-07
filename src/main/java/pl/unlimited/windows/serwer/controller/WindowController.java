package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.WindowApi;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.service.order.document.WindowFacade;
import pl.unlimited.windows.serwer.service.order.document.WindowSizeFacade;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class WindowController implements WindowApi {

    private WindowSizeFacade windowSizeFacade;
    private WindowFacade windowFacade;

    public WindowController(WindowSizeFacade windowSizeFacade, WindowFacade windowFacade) {
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
    public ResponseEntity<Void> updateWindow(Long id, WindowDto windowDto) {
        windowFacade.updateWindows(id, windowDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteWindow(Long id) {
         windowFacade.deleteById(id);
         return ResponseEntity.noContent().build();
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
    public ResponseEntity<Void> updateWindowSize(Long id, WindowSizeDto windowSize) {
        windowSizeFacade.updateWindowSize(id, windowSize);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<WindowSizeDto> getWindowSizeById(Long id) {
        return ResponseEntity.ok(windowSizeFacade.getWindowSizeById(id));
    }
}
