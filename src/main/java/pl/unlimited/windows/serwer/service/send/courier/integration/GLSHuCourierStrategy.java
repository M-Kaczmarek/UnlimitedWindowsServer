package pl.unlimited.windows.serwer.service.send.courier.integration;

import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.model.TransportDocument;
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;

public class GLSHuCourierStrategy implements AbstractCourierStrategy{

    @Override
    public CourierResponse generatePackageNumber(PackageDataDto packageDat) {
        return null;
    }

    @Override
    public byte[] generateLabel(final TransportDocument transportDocument) {
        return null;
    }
}
