package pl.unlimited.windows.serwer.service.order.document;

import org.junit.jupiter.api.Test;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;

class WindowSizeDomainUnitTest {

    @Test
    void shouldThrowInvalidRequestExceptionWhenHeightIsNull(){
        //given
        Double width = 8.0;
        WindowSizeDto windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(width)
                .height(null)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Height"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWidthIsNull(){
        //given
        var height = 8.0;
        var windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(null)
                .height(height)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Width"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenHeightIsZero(){
        //given
        Double width = 8.0;
        WindowSizeDto windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(width)
                .height(0.0)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Height"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWidthIsZero(){
        //given
        var height = 8.0;
        var windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(0.0)
                .height(height)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Width"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenHeightIsNegative(){
        //given
        Double width = 8.0;
        WindowSizeDto windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(width)
                .height(-2.0)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Height"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWidthIsNegative(){
        //given
        var height = 8.0;
        var windowSizeDto = new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .width(-2.0)
                .height(height)
                .build();

        //when + then
        assertThatCode(() -> WindowSizeDomain.fromDto(windowSizeDto))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowSizeDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Width"));
    }

}