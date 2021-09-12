package pl.unlimited.windows.serwer.service;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.repository.OrderDocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDocumentFacade {

    private CreateOrderDocumentService createOrderDocumentService;
    private OrderDocumentRepository orderDocumentRepository;

    public OrderDocumentFacade(CreateOrderDocumentService createOrderDocumentService, OrderDocumentRepository orderDocumentRepository) {
        this.createOrderDocumentService = createOrderDocumentService;
        this.orderDocumentRepository = orderDocumentRepository;
    }

    public OrderDocumentDto createOrderDocument(OrderDocumentDto orderDocument) {
        return createOrderDocumentService.createOrderDocument(orderDocument);
    }

    public List<OrderDocumentDto> getOrderDocument() {
        return orderDocumentRepository.findAll()
                .stream()
                .map(entity -> entity.toDto())
                .collect(Collectors.toList());
    }

    public void updateOrderDocument(Long id, OrderDocumentDto orderDocumentDto) {

    }
}
