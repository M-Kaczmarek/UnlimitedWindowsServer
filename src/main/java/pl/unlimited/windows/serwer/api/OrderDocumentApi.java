package pl.unlimited.windows.serwer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

@RequestMapping("/api/orderDocument")
public interface OrderDocumentApi {

    @PostMapping
    ResponseEntity<OrderDocumentDto> createOrderDocument(@RequestBody OrderDocumentDto orderDocumentDto);
}
