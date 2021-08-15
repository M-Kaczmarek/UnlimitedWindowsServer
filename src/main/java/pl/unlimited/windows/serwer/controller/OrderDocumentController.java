package pl.unlimited.windows.serwer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.OrderDocumentApi;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.service.OrderDocumentFacade;

@RestController
public class OrderDocumentController implements OrderDocumentApi {

    private final OrderDocumentFacade orderDocumentFacade;

    public OrderDocumentController(final OrderDocumentFacade orderDocumentFacade) {
        this.orderDocumentFacade = orderDocumentFacade;
    }

    @Override
    public ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto){
        return ResponseEntity.ok(orderDocumentDto);
    }
}
