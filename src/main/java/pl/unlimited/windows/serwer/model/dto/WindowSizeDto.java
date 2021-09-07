package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = WindowSizeDto.WindowSizeBuilder.class)
public class WindowSizeDto {
    private final Long id;
    private final Double width;
    private final Double height;

    public WindowSizeDto(final WindowSizeBuilder builder) {
        this.id = builder.id;
        this.width = builder.width;
        this.height = builder.height;
    }

    public Long getId() {
        return id;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class WindowSizeBuilder {
        private Long id;
        private Double width;
        private Double height;

        public WindowSizeBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public WindowSizeBuilder width(final Double width) {
            this.width = width;
            return this;
        }

        public WindowSizeBuilder height(final Double height) {
            this.height = height;
            return this;
        }

        public WindowSizeDto build() {
            return new WindowSizeDto(this);
        }

    }
}
