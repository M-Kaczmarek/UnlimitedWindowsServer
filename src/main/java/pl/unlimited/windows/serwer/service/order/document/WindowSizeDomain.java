package pl.unlimited.windows.serwer.service.order.document;

import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentInvalidRequestException;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import java.util.Optional;

class WindowSizeDomain {

    public static final String EXCEPTION_DESCRIPTION_IS_NULL = "%s is required";
    public static final String EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER = "%s could have positive number";

    private Long id;
    private Double width;
    private Double height;

    private WindowSizeDomain(final WindowSizeDto windowSizeDto) {
        isSize(windowSizeDto.getWidth(), windowSizeDto.getHeight());
        isPositiveSize(windowSizeDto.getWidth(), windowSizeDto.getHeight());
        this.id = windowSizeDto.getId();
        this.width = windowSizeDto.getWidth();
        this.height = windowSizeDto.getHeight();
    }

    public static WindowSizeDomain fromDto(final WindowSizeDto windowSizeDto) {
        return new WindowSizeDomain(Optional.ofNullable(windowSizeDto).orElse(null));
    }

    public static WindowSize toEntityWindowSize(final WindowSizeDomain windowSizeDomain) {
        return new WindowSize(windowSizeDomain.id, windowSizeDomain.width, windowSizeDomain.height);
    }

    private void isSize(Double width, Double height) {
        isRequiredValue(width, "Width");
        isRequiredValue(height, "Height");
    }

    private void isRequiredValue(Double filed, String fieldName) {
        if(filed == null){
            throw new OrderDocumentInvalidRequestException(String.format(EXCEPTION_DESCRIPTION_IS_NULL, fieldName), ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
    }

    private void isPositiveSize(Double width, Double height) {
        if (width <= 0) {
            throw new OrderDocumentInvalidRequestException(String.format(EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Width"), ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
        if (height <= 0) {
            throw new OrderDocumentInvalidRequestException(String.format(EXCEPTION_DESCRIPTION_IS_POSITIVE_NUMBER, "Height"), ErrorCode.ORDER_DOCUMENT_INVALID_REQUEST);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
