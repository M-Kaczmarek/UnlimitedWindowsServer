package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Builder
@Getter
@XmlRootElement(name = "Package")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackageResponse {
    @XmlElement(name = "Status")
    private String status;
    @XmlElement(name = "PackageId")
    private String packageId;
    @XmlElement(name = "Parcels")
    private List<ParcelsResponse> parcelsResponseList;
}
