package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "parcels")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parcel {
    @XmlElement
    private String reference;
    @XmlElement
    private String weight;
    @XmlElement
    private String sizeX;
    @XmlElement
    private String sizeY;
    @XmlElement
    private String sizeZ;
    @XmlElement
    private String content;
    @XmlElement
    private String customerData1;
    @XmlElement
    private String customerData2;
    @XmlElement
    private String customerData3;
}
