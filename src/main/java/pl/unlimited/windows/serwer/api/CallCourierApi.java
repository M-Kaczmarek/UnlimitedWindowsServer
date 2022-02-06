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
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;

import java.util.Map;

@RequestMapping("/api/courier/generate")
@Tag(name = "Integracja z kurierem", description = "Endpointy komunikujące się z serwerem kuriera")
public interface CallCourierApi {

    @PostMapping("/package/number/{courierId}/{transportId}")
    @Operation(
            summary = "Generowanie numeru przesyłki",
            description = "Wygeneruj numeru przesyłki",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<Map<String, String>> generatePackageNumber(@PathVariable Long courierId, @PathVariable Long transportId, @RequestBody PackageDataDto packageData );

    @PostMapping("/label/{courierId}/{transportId}")
    @Operation(
            summary = "Generowaniu listu przewozowego",
            description = "Wygenreuj list przewozowy",
            responses = {
                    @ApiResponse(
                            description = "Udana operacjia",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation= Byte.class)))),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
                            }
                    )
    ResponseEntity<byte[]> generateLabel(@PathVariable Long courierId, @PathVariable Long transportId);
}
