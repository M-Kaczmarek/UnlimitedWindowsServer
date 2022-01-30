package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "cod")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cod {
    @XmlElement
    private String amount;
    @XmlElement
    private String currency;
}
