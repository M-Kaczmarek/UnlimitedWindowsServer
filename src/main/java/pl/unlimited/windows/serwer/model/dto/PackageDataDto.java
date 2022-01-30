package pl.unlimited.windows.serwer.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PackageDataDto {
    private String documentNumber;
    private SenderDto senderDto;
    private ReceiverDto receiverDto;

}
