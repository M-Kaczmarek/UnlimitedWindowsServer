package pl.unlimited.windows.serwer.service;

import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.*;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import java.util.Optional;

class WindowDomain {

    public static final String EXCEPTION_DESCRIPTION_IS_NULL = "%s is required";
    public static final String EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER = "%s could have positive number";


    private Long id;
    private WindowType windowType;
    private GlassType glassType;
    private Boolean protectionGlass;
    private WindowProfileType windowProfileType;
    private WindowHardware windowHardware;
    private Long countWindowSashes;
    private WindowOpenType windowOpenType;
    private WindowSizeDomain windowSize;

    private WindowDomain(WindowDto windowDto) {
        isRequiredData(windowDto.getWindowType(), windowDto.getGlassType(), windowDto.getWindowProfileType(), windowDto.getWindowHardware(),
                windowDto.getCountWindowSashes(), windowDto.getWindowOpenType(), windowDto.getWindowSize());
        countWindowSashesIsPositive(windowDto.getCountWindowSashes());
        this.id = windowDto.getId();
        this.windowType = windowDto.getWindowType();
        this.glassType = windowDto.getGlassType();
        this.protectionGlass = Optional.ofNullable(windowDto.getProtectionGlass()).orElse(Boolean.FALSE);
        this.windowProfileType = windowDto.getWindowProfileType();
        this.windowHardware = windowDto.getWindowHardware();
        this.countWindowSashes = windowDto.getCountWindowSashes();
        this.windowOpenType = windowDto.getWindowOpenType();
        this.windowSize = WindowSizeDomain.fromDto(windowDto.getWindowSize());
    }

    public static WindowDomain fromDto(final WindowDto windowDto) {
        return new WindowDomain(windowDto);
    }

    public static Window toEntityWindow(final WindowDomain windowDomain) {
        return new Window(windowDomain.id, windowDomain.windowType, windowDomain.glassType, windowDomain.protectionGlass, windowDomain.windowProfileType, windowDomain.windowHardware, windowDomain.countWindowSashes, windowDomain.windowOpenType, WindowSizeDomain.toEntityWindowSize(windowDomain.windowSize));
    }

    private void isRequiredData(WindowType windowType, GlassType glassType, WindowProfileType windowProfileType, WindowHardware windowHardware, Long countWindowSashes, WindowOpenType windowOpenType, WindowSizeDto windowSize) {
        isRequiredValue(windowType, "Window type");
        isRequiredValue(glassType, "Glass type");
        isRequiredValue(windowProfileType, "Window profile type");
        isRequiredValue(windowHardware, "Window hardware");
        isRequiredValue(countWindowSashes, "Count window sashes");
        isRequiredValue(windowOpenType, "Window open type");
        isRequiredValue(windowSize, "Window size");
    }

    private void countWindowSashesIsPositive(Long countWindowSashes) {
        if (countWindowSashes <= 0) {
            throw new OrderDocumentInvalidRequestException(String.format(EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Count window sashes"), ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
    }

    private void isRequiredValue(Object filed, String fieldName) {
        if (filed == null) {
            throw new OrderDocumentInvalidRequestException(String.format(EXCEPTION_DESCRIPTION_IS_NULL, fieldName), ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
