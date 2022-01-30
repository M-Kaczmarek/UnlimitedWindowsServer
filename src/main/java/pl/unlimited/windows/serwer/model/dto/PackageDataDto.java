package pl.unlimited.windows.serwer.model.dto;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class PackageDataDto {
    private String documentNumber;
    private SenderDto senderDto;
    private ReceiverDto receiverDto;

}
