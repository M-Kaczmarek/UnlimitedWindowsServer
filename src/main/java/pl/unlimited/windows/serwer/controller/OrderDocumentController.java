package pl.unlimited.windows.serwer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.OrderDocumentApi;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.service.OrderDocumentFacade;

import java.util.List;

@RestController
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
    public ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto){
        OrderDocumentDto createdOrderDocument = orderDocumentFacade.createOrderDocument(orderDocumentDto);

        return ResponseEntity.ok(createdOrderDocument); //TODO status code could be created
    }
}
