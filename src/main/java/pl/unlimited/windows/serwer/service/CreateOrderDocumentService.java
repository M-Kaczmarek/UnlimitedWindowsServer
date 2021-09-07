package pl.unlimited.windows.serwer.service;


import org.springframework.stereotype.Component;
import pl.unlimited.windows.serwer.model.OrderDocument;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.repository.OrderDocumentRepository;

@Component
class CreateOrderDocumentService {
    private OrderDocumentRepository orderDocumentRepository;

    public CreateOrderDocumentService(OrderDocumentRepository orderDocumentRepository) {
        this.orderDocumentRepository = orderDocumentRepository;
    }

    public OrderDocumentDto createOrderDocument(final OrderDocumentDto orderDocumentDto) {
        OrderDocumentDomain orderDocumentDomain = OrderDocumentDomain.fromDto(orderDocumentDto);
        OrderDocument savedOrderDocument = orderDocumentRepository.save(OrderDocumentDomain.toEntityModel(orderDocumentDomain));
        return savedOrderDocument.toDto();
    }
}
