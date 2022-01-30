package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "Parcels")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParcelsResponse {
    @XmlElement(name = "Parcel")
    private ParcelResponse parcelResponse;
}
