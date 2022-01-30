package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.OrderDocumentApi;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.service.order.document.OrderDocumentFacade;

import java.net.URI;
import java.util.List;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class OrderDocumentController implements OrderDocumentApi {

    private final OrderDocumentFacade orderDocumentFacade;

    public OrderDocumentController(final OrderDocumentFacade orderDocumentFacade) {
        this.orderDocumentFacade = orderDocumentFacade;
    }

    @Override
    public ResponseEntity<List<OrderDocumentDto>> getOrderDocuments() {
        return ResponseEntity.ok(orderDocumentFacade.getOrderDocument());
    }

    @Override
    public ResponseEntity<OrderDocumentDto> getOrderDocumentById(Long id) {
        return ResponseEntity.ok(orderDocumentFacade.getOrderDocumentById(id));
    }

    @Override
    public ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto){
        OrderDocumentDto createdOrderDocument = orderDocumentFacade.createOrderDocument(orderDocumentDto);

        return ResponseEntity.created(URI.create("/api/orders/documents/" + createdOrderDocument.getId())).body(createdOrderDocument);
   }

    @Override
    public ResponseEntity<Void> updateOrderDocument(Long id, OrderDocumentDto orderDocumentDto) {
      orderDocumentFacade.updateOrderDocument(id, orderDocumentDto);

      return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteOrderDocument(Long id) {
        orderDocumentFacade.deleteOrderDocument(id);
        return ResponseEntity.noContent().build();
    }
}
