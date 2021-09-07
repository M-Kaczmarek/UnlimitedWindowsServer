package pl.unlimited.windows.serwer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import java.util.List;

@RequestMapping("/api/windows/sizes")
public interface WindowSizeApi {
    @GetMapping
    ResponseEntity<List<WindowSizeDto>> getWindowSizes();

    @PostMapping
    ResponseEntity<WindowSizeDto> createWindowSize(@RequestBody WindowSizeDto windowSizeDto);

    @GetMapping("/{id}")
    ResponseEntity<WindowSizeDto> getWindowSizeById(@PathVariable Long id);
}
