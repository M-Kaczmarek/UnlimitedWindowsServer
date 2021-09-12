package pl.unlimited.windows.serwer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import java.util.List;

@RequestMapping("/api/windows")
public interface WindowSizeApi {
    @GetMapping
    ResponseEntity<List<WindowDto>> getWindows();

    @PostMapping
    ResponseEntity<WindowDto> createWindow(@RequestBody WindowDto windowDto);

    @GetMapping("/{id}")
    ResponseEntity<WindowDto> getWindowById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Void> updateWindow(@PathVariable Long id, @RequestBody WindowDto windowDto);

    @GetMapping("/sizes")
    ResponseEntity<List<WindowSizeDto>> getWindowSizes();

    @PostMapping("/sizes")
    ResponseEntity<WindowSizeDto> createWindowSize(@RequestBody WindowSizeDto windowSizeDto);

    @PutMapping("/sizes/{id}")
    ResponseEntity<Void> updateWindowSize(@PathVariable Long id, @RequestBody WindowSizeDto windowSizeDto);

    @GetMapping("/sizes/{id}")
    ResponseEntity<WindowSizeDto> getWindowSizeById(@PathVariable Long id);
}
