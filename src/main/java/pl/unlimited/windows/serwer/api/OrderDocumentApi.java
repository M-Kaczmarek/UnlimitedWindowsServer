package pl.unlimited.windows.serwer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

import java.util.List;

@RequestMapping("/api/orders/documents")
@Tag(name = "Dokument sprzedaży", description = "Endpointy zarządzające dokumentami sprzedaży")
public interface OrderDocumentApi {

    @GetMapping
    @Operation(
            summary = "Generowanie numeru przesyłki",
            description = "Wygeneruj numeru przesyłki",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = OrderDocumentDto.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<OrderDocumentDto>> getOrderDocuments();

    @GetMapping("/{id}")
    @Operation(
            summary = "Pobranie konrentego dokumentu sprzedaży",
            description = "Pobierz dokument sprzedaży",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourierResponse.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<OrderDocumentDto> getOrderDocumentById(@PathVariable Long id);

    @PostMapping
    @Operation(
            summary = "Stworzenie dokumentów sprzedaży",
            description = "Stwórz dokumentów sprzedaży",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDocumentDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto);

    @PutMapping("/{id}")
    @Operation(
            summary = "Aktualizacja dokumentów sprzedaży",
            description = "Aktualizuj dokumentów sprzedaży",
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
    ResponseEntity<Void> updateOrderDocument(@PathVariable Long id, @RequestBody OrderDocumentDto orderDocumentDto);

    @DeleteMapping("{id}")
    @Operation(
            summary = "Usuwanie dokumentów sprzedaży",
            description = "Usuń dokument sprzedaży",
            responses = {
                    @ApiResponse(
                            description = "Usunieta dokument sprzedaży",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Void> deleteOrderDocument(@PathVariable Long id);
}
