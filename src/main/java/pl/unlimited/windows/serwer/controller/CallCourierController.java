package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.CallCourierApi;
import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.couriers.api.dpd.*;
import pl.unlimited.windows.serwer.couriers.api.dpd.Package;
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;
import pl.unlimited.windows.serwer.service.send.SendPackageService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Map;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class CallCourierController implements CallCourierApi {

    private final SendPackageService service;

    public CallCourierController(SendPackageService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Map<String, String>> generatePackageNumber(Long courierId, Long transportId, PackageDataDto packageData) {
        return ResponseEntity.ok(service.generatePackage(packageData, transportId, courierId));
    }

    @Override
    public ResponseEntity<byte[]> generateLabel(Long courierId, Long transportId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
       var label = service.generateLabel(courierId, transportId);

        return new ResponseEntity<>(label, headers, HttpStatus.OK);

    }
}
