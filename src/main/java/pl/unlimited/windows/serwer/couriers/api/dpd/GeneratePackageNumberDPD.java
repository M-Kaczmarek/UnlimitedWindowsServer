package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "dpd:generatePackagesNumbersV6")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneratePackageNumberDPD {
    @XmlElement(name = "openUMLFeV5")
    private GenerateRequest generateData;
    @XmlElement
    private String pkgNumsGenerationPolicyV1;
    @XmlElement
    private String langCode;
    @XmlElement(name = "authDataV1")
    private AuthData authData;
}
