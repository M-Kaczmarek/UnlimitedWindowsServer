package pl.unlimited.windows.serwer.service.order.document;

import org.junit.jupiter.api.Test;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.service.order.document.OrderDocumentDomain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

class OrderDocumentDomainUnitTest {

    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowIsNull(){
        //given
        Double width = 8.0;
        var windowSizeDto = new OrderDocumentDto.OrderDocumentDtoBuilder()
                .id(anyLong())
                .name(anyString())
                .window(null)
                .build();

        //when + then
        assertThatCode(() -> OrderDocumentDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage("Window is Required");
    }

}