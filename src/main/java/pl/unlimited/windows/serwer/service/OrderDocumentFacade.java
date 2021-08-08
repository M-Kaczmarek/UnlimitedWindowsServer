package pl.unlimited.windows.serwer.service;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

@Service
public class OrderDocumentFacade {

    private CreateOrderDocumentService createOrderDocumentService;

    public OrderDocumentDto createOrderDocument(OrderDocumentDto orderDocument){
        return createOrderDocumentService.createOrderDocument(orderDocument);
    }
}
