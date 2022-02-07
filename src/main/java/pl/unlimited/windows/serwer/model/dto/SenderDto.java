package pl.unlimited.windows.serwer.model.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class SenderDto {

    private String name;
    private String address;
    private String city;
    private String email;
    private String phoneNumber;
    private String postalCode;

}
