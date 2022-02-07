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
import org.springframework.web.bind.annotation.RequestParam;
import pl.unlimited.windows.serwer.model.ProductionSteps;
import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;
import pl.unlimited.windows.serwer.model.dto.ProductionDocumentDto;

import java.util.List;

@RequestMapping("/api/productions/documents")
@Tag(name = "Dokument produkcji", description = "Endpointy zarządzające dokumentami produkcji")
public interface ProductionDocumentApi {

    @GetMapping
    @Operation(
            summary = "Pobieranie wszytkich dokumentów produkcji",
            description = "Pobierz wszytskie dokumenty produkcji",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = ProductionDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<ProductionDocumentDto>> getProductionDocument();

    @GetMapping("/{id}")
    @Operation(
            summary = "Pobieranie konkretnego dokumentu produkcji",
            description = "Pobierz dokument produkcji",
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
    ResponseEntity<ProductionDocumentDto> getProductionDocumentById(@PathVariable Long id);

    @GetMapping("/filter")
    @Operation(
            summary = "Pobieranie dokumentów produkcji dla konkretnego filtru",
            description = "Pobierz dokument produkcji dla konkretnego filtru",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = ProductionDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<ProductionDocumentDto>> getProductionDocumentByDateAndStep(@RequestParam(required = false) String date, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo , @RequestParam(required = false) ProductionSteps step);

    @GetMapping("/done/step/{id}")
    @Operation(
            summary = "Przejść etapu produkcji",
            description = "Wykonaj etap produkcji",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = ProductionDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<ProductionDocumentDto>> doProductionStep(@RequestParam(required = false) ProductionSteps nextStep, @PathVariable Long id);

}
