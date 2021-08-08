package pl.unlimited.windows.serwer.service;


import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.repository.OrderDocumentRepository;

@Component
class CreateOrderDocumentService {
    private OrderDocumentRepository orderDocumentRepository;

    public CreateOrderDocumentService(OrderDocumentRepository orderDocumentRepository) {
        this.orderDocumentRepository = orderDocumentRepository;
    }

    public OrderDocumentDto createOrderDocument(final OrderDocumentDto orderDocumentDto){
        OrderDocumentDomain doc =  OrderDocumentDomain.fromDto(orderDocumentDto);

        return null;
    }
}
