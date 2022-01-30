package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "authDataV1")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthData {
    @XmlElement
    private String login;
    @XmlElement
    private String  masterFid;
    @XmlElement
    private String password;
}
