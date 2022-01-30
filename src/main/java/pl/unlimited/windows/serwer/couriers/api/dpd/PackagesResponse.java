package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Builder
@Getter
@XmlRootElement(name = "Packages")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackagesResponse {
    @XmlElement(name = "Package")
    private List<PackageResponse> packageResponseList;
}
