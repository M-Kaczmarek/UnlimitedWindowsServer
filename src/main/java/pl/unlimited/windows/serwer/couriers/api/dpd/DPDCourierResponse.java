package pl.unlimited.windows.serwer.couriers.api.dpd;

import lombok.*;
import pl.unlimited.windows.serwer.couriers.api.CourierResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Getter
@XmlRootElement(name = "generatePackagesNumbersV6Response")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DPDCourierResponse extends CourierResponse {
    @XmlElement(name = "return")
    private ReturnResponse returnResponse;
}
