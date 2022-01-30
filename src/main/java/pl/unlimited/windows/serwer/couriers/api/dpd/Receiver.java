package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "receiver")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receiver {
    @XmlElement
    private String address;
    @XmlElement
    private String city;
    @XmlElement
    private String company;
    @XmlElement
    private String countryCode;
    @XmlElement
    private String email;
    @XmlElement
    private String fid;
    @XmlElement
    private String name;
    @XmlElement
    private String phone;
    @XmlElement
    private String postalCode;
}
