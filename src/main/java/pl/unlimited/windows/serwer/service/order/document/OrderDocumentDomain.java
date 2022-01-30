package pl.unlimited.windows.serwer.service.order.document;

import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.OrderDocument;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.model.dto.WindowDto;

class OrderDocumentDomain {
    private WindowDomain window;

    private OrderDocumentDomain(OrderDocumentDto orderDocumentDto) {
        isWindow(orderDocumentDto.getWindow());
        this.window = WindowDomain.fromDto(orderDocumentDto.getWindow());;
    }

    public static OrderDocumentDomain fromDto(OrderDocumentDto orderDocumentDto){
        return new OrderDocumentDomain(orderDocumentDto);
    }

    public static OrderDocument toEntityModel(OrderDocumentDomain orderDocumentDomain){
        return new OrderDocument(null, WindowDomain.toEntityWindow(orderDocumentDomain.getWindow()));
    }

    public WindowDomain getWindow() {
        return window;
    }

    private void isWindow(WindowDto window){
        if(window == null){
            throw new OrderDocumentInvalidRequestException("Window is Required", ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
    }
}
