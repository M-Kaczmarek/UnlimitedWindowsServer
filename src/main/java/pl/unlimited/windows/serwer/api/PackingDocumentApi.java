package pl.unlimited.windows.serwer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;

import java.util.List;

@RequestMapping("/api/packing/documents")
@Tag(name = "Proces pakowania", description = "Endpointy zarządzające procesem pakowania")
public interface PackingDocumentApi {

    @GetMapping
    @Operation(
            summary = "Pobieranie elementów do pakowania",
            description = "Pobierz elementy do pakowania",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = PackingDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<PackingDocumentDto>> getPackingDocument();

    @GetMapping("/all")
    @Operation(
            summary = "Pobranie wszystkich elementów w precesie pakowanie",
            description = "Pobierz elementy z procesu pakowania",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = PackingDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<PackingDocumentDto>> getPackingAllDocuments();

    @GetMapping("/packed/{id}")
    @Operation(
            summary = "Pakowanie zamówienia",
            description = "Spakuj zamówienie",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",  schema = @Schema(implementation = PackingDocumentDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<PackingDocumentDto> packedProduct(@PathVariable Long id);
}
