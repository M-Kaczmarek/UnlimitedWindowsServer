package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "codDedicatedAccount")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CodDedicatedAccount {
    @XmlElement
    private String accountNumber;
    @XmlElement
    private String amount;
    @XmlElement
    private String currency;
}
