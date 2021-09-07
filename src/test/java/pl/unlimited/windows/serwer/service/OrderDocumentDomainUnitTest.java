package pl.unlimited.windows.serwer.service;

import org.junit.jupiter.api.Test;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class OrderDocumentDomainUnitTest {

    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowIsNull(){
        //given
        Double width = 8.0;
        var windowSizeDto = new OrderDocumentDto.OrderDocumentDtoBuilder()
                .id(anyLong())
                .window(null)
                .build();

        //when + then
        assertThatCode(() -> OrderDocumentDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage("Window is Required");
    }

}