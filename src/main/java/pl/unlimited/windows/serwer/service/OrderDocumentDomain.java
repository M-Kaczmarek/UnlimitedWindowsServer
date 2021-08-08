package pl.unlimited.windows.serwer.service;

import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.model.dto.WindowDto;

class OrderDocumentDomain {
    private WindowDomain window;

    private OrderDocumentDomain(OrderDocumentDto orderDocumentDto) {
        this.window = WindowDomain.fromDto(orderDocumentDto.getWindow());
    }

    public static OrderDocumentDomain fromDto(OrderDocumentDto orderDocumentDto){
        return new OrderDocumentDomain(orderDocumentDto);
    }

    public WindowDomain getWindow() {
        return window;
    }
}
