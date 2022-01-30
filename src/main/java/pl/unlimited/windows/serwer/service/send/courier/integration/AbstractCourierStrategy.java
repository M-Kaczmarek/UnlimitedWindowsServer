package pl.unlimited.windows.serwer.service.send.courier.integration;

import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.model.TransportDocument;
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;

public interface  AbstractCourierStrategy {

     CourierResponse generatePackageNumber(final PackageDataDto packageDat);

     byte[] generateLabel(final TransportDocument transportDocument);
}
