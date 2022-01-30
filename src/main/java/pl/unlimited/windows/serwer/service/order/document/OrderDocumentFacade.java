package pl.unlimited.windows.serwer.service.order.document;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentNotFoundException;
import pl.unlimited.windows.serwer.error.exception.WindowSizeNotFoundException;
import pl.unlimited.windows.serwer.model.OrderDocument;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.repository.OrderDocumentRepository;
import pl.unlimited.windows.serwer.repository.WindowSizeRepository;
import pl.unlimited.windows.serwer.service.production.document.ProductionDocumentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDocumentFacade {

    private CreateOrderDocumentService createOrderDocumentService;
    private OrderDocumentRepository orderDocumentRepository;
    private ProductionDocumentService productionDocumentService;
    private WindowFacade windowFacade;

    public OrderDocumentFacade(CreateOrderDocumentService createOrderDocumentService, OrderDocumentRepository orderDocumentRepository, ProductionDocumentService productionDocumentService, WindowFacade windowFacade) {
        this.createOrderDocumentService = createOrderDocumentService;
        this.orderDocumentRepository = orderDocumentRepository;
        this.productionDocumentService = productionDocumentService;
        this.windowFacade = windowFacade;
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
        orderDocumentRepository.findById(id).orElseThrow(() -> new OrderDocumentNotFoundException("Order document is with id: " + id + " not found", ErrorCode.ORDER_DOCUMENT_NOT_FOUND));

        windowFacade.updateWindows(orderDocumentDto.getWindow().getId(), orderDocumentDto.getWindow());

        OrderDocumentDomain orderDocumentDomain = OrderDocumentDomain.fromDto(orderDocumentDto);
       var entityOrderDocument =  OrderDocumentDomain.toEntityModel(orderDocumentDomain);
       entityOrderDocument.setId(id);

        orderDocumentRepository.save(entityOrderDocument);
    }

    @Transactional
    public void deleteOrderDocument(Long id) {
        orderDocumentRepository.findById(id).orElseThrow(() -> new OrderDocumentNotFoundException("Order document is with id: " + id + " not found", ErrorCode.ORDER_DOCUMENT_NOT_FOUND));

        if(productionDocumentService.isProductionDocumentForOrderDocument(id)){
            productionDocumentService.deleteProductionDocumentForOrderDocument(id);
        }
        orderDocumentRepository.deleteById(id);
    }

    public OrderDocumentDto getOrderDocumentById(Long id) {
      return  orderDocumentRepository.findById(id).map(OrderDocument::toDto).orElseThrow(() -> new OrderDocumentNotFoundException("Order document is with id: " + id + " not found", ErrorCode.ORDER_DOCUMENT_NOT_FOUND));
    }
}
