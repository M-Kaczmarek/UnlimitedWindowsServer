package pl.unlimited.windows.serwer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import java.util.List;

@RequestMapping("/api/windows")
@Tag(name = "Okna", description = "Endpointy zarządzające oknami")
public interface WindowApi {
    @GetMapping
    @Operation(
            summary = "Pobieranie wszytkich okien",
            description = "Pobierz wszytskie okna",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = WindowDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<WindowDto>> getWindows();

    @PostMapping
    @Operation(
            summary = "Stworzenie okna",
            description = "Stwórz okkno",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WindowDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<WindowDto> createWindow(@RequestBody WindowDto windowDto);

    @GetMapping("/{id}")
    @Operation(
            summary = "Pobieranie konkretne okien",
            description = "Pobierz okno",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = WindowDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<WindowDto> getWindowById(@PathVariable Long id);

    @PutMapping("/{id}")
    @Operation(
            summary = "Aktualizacja konretnego okna",
            description = "Aktualizuj okno",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Void> updateWindow(@PathVariable Long id, @RequestBody WindowDto windowDto);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Usuwanie konkretnego okna",
            description = "Usuń okno",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Void> deleteWindow(@PathVariable Long id);

    @GetMapping("/sizes")
    @Operation(
            summary = "Pobieranie wszytkich rozmiarów okien",
            description = "Pobierz wszytskie rozmiary okien",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = WindowSizeDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<WindowSizeDto>> getWindowSizes();

    @PostMapping("/sizes")
    @Operation(
            summary = "Stworzenie rozmiar okna",
            description = "Stwórz rozmiar okna",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WindowSizeDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<WindowSizeDto> createWindowSize(@RequestBody WindowSizeDto windowSizeDto);

    @PutMapping("/sizes/{id}")
    @Operation(
            summary = "Aktualizacja rozmiaru okna",
            description = "Aktualizuj rozmiar okna",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = TransportDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Void> updateWindowSize(@PathVariable Long id, @RequestBody WindowSizeDto windowSizeDto);

    @GetMapping("/sizes/{id}")
    @Operation(
            summary = "Pobieranie konkretnego rozmiaru okna",
            description = "Pobierz konkretny rozmiar okna",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = TransportDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<WindowSizeDto> getWindowSizeById(@PathVariable Long id);
}
