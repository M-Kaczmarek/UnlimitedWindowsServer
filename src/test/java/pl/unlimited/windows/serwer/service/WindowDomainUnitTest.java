package pl.unlimited.windows.serwer.service;

import org.junit.jupiter.api.Test;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.*;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyLong;

public class WindowDomainUnitTest {

    @Test
    void shouldThrowInvalidRequestExceptionWhenGlassTypeIsNull(){
        //given
      var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_DOOR)
                .glassType(null)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Glass type"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowTypeIsNull(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(null)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Window type"));
    }
    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowProfileTypeIsNull(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_DOOR)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(null)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Window profile type"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowHardwareIsNull(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_BLIND)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(null)
                .countWindowSashes(5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Window hardware"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenCountWindowSashesIsNull(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_BLIND)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(null)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Count window sashes"));
    }
    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowOpenTypeIsNull(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_BLIND)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(5L)
                .windowOpenType(null)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Window open type"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenWindowSizeIsNull(){
        //given
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_BLIND)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(null)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_NULL, "Window size"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenCountWindowSashesIsNegative(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_DOOR)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(-5L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Count window sashes"));
    }

    @Test
    void shouldThrowInvalidRequestExceptionWhenCountWindowSashesIsZero(){
        //given
        var windowSize =  new WindowSizeDto.WindowSizeBuilder()
                .id(anyLong())
                .height(5.7)
                .height(7.8)
                .build();
        var window = new WindowDto.WindowDtoBuilder()
                .windowType(WindowType.WINDOW_DOOR)
                .glassType(GlassType.DOUBLE_CHAMBER_GLASS)
                .protectionGlass(Boolean.TRUE)
                .windowProfileType(WindowProfileType.PASSIVE_40X50)
                .windowHardware(WindowHardware.TILT_WINDOW)
                .countWindowSashes(0L)
                .windowOpenType(WindowOpenType.LEFT_INDOOR)
                .windowSize(windowSize)
                .build();

        //when + then
        assertThatCode(() -> WindowDomain.fromDto(window))
                .isInstanceOf(OrderDocumentInvalidRequestException.class)
                .hasMessage(String.format(WindowDomain.EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Count window sashes"));
    }

}
