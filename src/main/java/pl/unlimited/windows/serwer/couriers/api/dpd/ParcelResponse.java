package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "Parcel")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParcelResponse {
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "ParcelId")
    private String parcelId;
    @XmlElement(name = "Reference")
    private String reference;
    @XmlElement(name = "Waybill")
    private String waybill;

}
