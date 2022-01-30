package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "openUMLFeV5")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenerateRequest {
    @XmlElement(name = "packages")
    private Package packages;
}
