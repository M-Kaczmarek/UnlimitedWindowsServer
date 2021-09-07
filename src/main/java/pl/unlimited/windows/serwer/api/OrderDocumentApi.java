package pl.unlimited.windows.serwer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

import java.util.List;

@RequestMapping("/api/orders/documents")
public interface OrderDocumentApi {

    @GetMapping
    ResponseEntity<List<OrderDocumentDto>> getOrderDocuments();

    @PostMapping
    ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto);
}
