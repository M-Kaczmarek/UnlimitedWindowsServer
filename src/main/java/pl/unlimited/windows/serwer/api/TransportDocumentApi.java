package pl.unlimited.windows.serwer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;

import java.util.List;

@RequestMapping("/api/transport/documents")
@Tag(name = "Dokument transportowy", description = "Endpointy zarządzające dokumentami transportowymi")
public interface TransportDocumentApi {

    @GetMapping
    @Operation(
            summary = "Pobieranie wszytkich dokumentów transportowe",
            description = "Pobierz wszytskie dokumenty transportu",
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
    ResponseEntity<List<TransportDocumentDto>> getAllTransportDocuments();
}
