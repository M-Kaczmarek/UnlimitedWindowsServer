package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "packages")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Package {
    @XmlElement(name = "parcels")
    private Parcel parcels;
    @XmlElement
    private String payerType;
    @XmlElement
    private Receiver receiver;
    @XmlElement
    private String ref1;
    @XmlElement
    private String ref2;
    @XmlElement
    private String ref3;
    @XmlElement
    private String reference;
    @XmlElement
    private Sender sender;
    @XmlElement
    private Service services;
    @XmlElement
    private String thirdPartyFID;
}
