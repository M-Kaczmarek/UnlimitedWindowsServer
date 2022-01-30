package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Service {
    @XmlElement
    private Cod postalCode;
    @XmlElement
    private CodDedicatedAccount codDedicatedAccount;
    @XmlElement
    private DeclaredValue declaredValue;

}
