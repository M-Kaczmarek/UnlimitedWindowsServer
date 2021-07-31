package pl.unlimited.windows.serwer.model.dto;

import pl.unlimited.windows.serwer.model.Window;

public class OrderDocumentDto {
    private final WindowDto window;

    public OrderDocumentDto(final OrderDocumentDtoBuilder builder) {
        this.window = builder.window;
    }

    public static class OrderDocumentDtoBuilder {
        private WindowDto window;
        ;

        public OrderDocumentDtoBuilder window(final WindowDto window) {
            this.window = window;
            return this;
        }

        public OrderDocumentDto build() {
            return new OrderDocumentDto(this);
        }
    }
}
