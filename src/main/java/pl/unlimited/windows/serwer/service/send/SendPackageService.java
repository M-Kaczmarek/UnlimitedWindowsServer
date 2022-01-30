package pl.unlimited.windows.serwer.service.send;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.couriers.api.dpd.*;
import pl.unlimited.windows.serwer.couriers.api.dpd.Package;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentNotFoundException;
import pl.unlimited.windows.serwer.error.exception.TransportDocumentNotFoundException;
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;
import pl.unlimited.windows.serwer.repository.TransportDocumentRepository;
import pl.unlimited.windows.serwer.service.send.courier.integration.AbstractCourierStrategy;
import pl.unlimited.windows.serwer.service.send.courier.integration.DPDCourierStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class SendPackageService {

    private final TransportDocumentRepository transportDocumentRepository;

    public SendPackageService(TransportDocumentRepository transportDocumentRepository) {
        this.transportDocumentRepository = transportDocumentRepository;
    }

    public CourierResponse generatePackage(PackageDataDto packageDataDto, final Long transportId, final Long courierId){
        var transportDocument = transportDocumentRepository.findById(transportId).orElseThrow(() -> new TransportDocumentNotFoundException("Transport document is with id: " + transportId + " not found", ErrorCode.TRANSPORT_DOCUMENT_NOT_FOUND));

        CourierResponse result = null;
//        AbstractCourierStrategy courierStrategy = null;
        //TODO Add new exception
        if(courierId == null){
            throw new IllegalArgumentException();
        }

        if (courierId == 1L) {
            AbstractCourierStrategy courierStrategy = new DPDCourierStrategy();
            result = courierStrategy.generatePackageNumber(packageDataDto);
            transportDocument.setGeneratedPackageNumber(((DPDCourierResponse) result).getReturnResponse().getPackageResponse().getPackageResponseList().get(0).getParcelsResponseList().get(0).getParcelResponse().getWaybill());
            transportDocumentRepository.save(transportDocument);
        }else{
          return null;
        }

        return result;
    }

    @SneakyThrows
    public byte[] generateLabel(Long courierId, Long transportId) {
        AbstractCourierStrategy courierStrategy = null;
        byte [] result = null;
        //TODO Add new exception
        if(courierId == null){
            throw new IllegalArgumentException();
        }

        if (courierId == 1L) {
          final var transportDocument =  transportDocumentRepository.findById(transportId).orElseThrow(() -> new TransportDocumentNotFoundException("Transport document is with id: " + transportId + " not found", ErrorCode.TRANSPORT_DOCUMENT_NOT_FOUND));
            courierStrategy = new DPDCourierStrategy();
            result =  courierStrategy.generateLabel(transportDocument);
        }else{
        }

        return result;
    }
}
