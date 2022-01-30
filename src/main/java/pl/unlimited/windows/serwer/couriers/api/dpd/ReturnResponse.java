package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "Return")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnResponse {
    @XmlElement(name = "Status")
    private String status;
    @XmlElement(name = "SessionId")
    private String sessionId;
    @XmlElement(name = "Packages")
    private PackagesResponse packageResponse;

}
