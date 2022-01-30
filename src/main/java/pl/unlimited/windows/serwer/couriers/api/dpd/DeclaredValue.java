package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "declaredValue")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeclaredValue {
    @XmlElement
    private String amount;
    @XmlElement
    private String currency;
}
