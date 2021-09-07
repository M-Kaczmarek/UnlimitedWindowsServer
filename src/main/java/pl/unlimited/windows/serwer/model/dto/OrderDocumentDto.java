package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Optional;

@JsonDeserialize(builder = OrderDocumentDto.OrderDocumentDtoBuilder.class)
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

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
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
