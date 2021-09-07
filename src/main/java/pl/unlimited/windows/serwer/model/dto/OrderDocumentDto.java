package pl.unlimited.windows.serwer.model.dto;

import pl.unlimited.windows.serwer.model.Window;

import java.util.Optional;

public class OrderDocumentDto {
    private final Long id;
    private final WindowDto window;

    public OrderDocumentDto(final OrderDocumentDtoBuilder builder) {
        this.window = builder.window;
        this.id = builder.id;
    }

    public WindowDto getWindow() {
        return Optional.ofNullable(window).orElse(null);
    }

    public Long getId() {
        return id;
    }

    public static class OrderDocumentDtoBuilder {
        private WindowDto window;
        private Long id;


        public OrderDocumentDtoBuilder window(final WindowDto window) {
            this.window = window;
            return this;
        }

        public OrderDocumentDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public OrderDocumentDto build() {
            return new OrderDocumentDto(this);
        }
    }
}
