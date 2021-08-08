package pl.unlimited.windows.serwer.model.dto;

import pl.unlimited.windows.serwer.model.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class WindowDto {
    private final Long id;
    private final WindowType windowType;
    private final GlassType glassType;
    private final Boolean protectionGlass;
    private final WindowProfileType windowProfileType;
    private final WindowHardware windowHardware;
    private final Long countWindowSashes;
    private final WindowOpenType windowOpenType;
    private final WindowSizeDto windowSize;

    public WindowDto(final WindowDtoBuilder builder) {
        this.id = builder.id;
        this.windowType = builder.windowType;
        this.glassType = builder.glassType;
        this.protectionGlass = builder.protectionGlass;
        this.windowProfileType = builder.windowProfileType;
        this.windowHardware = builder.windowHardware;
        this.countWindowSashes = builder.countWindowSashes;
        this.windowOpenType = builder.windowOpenType;
        this.windowSize = builder.windowSize;
    }

    public Long getId() {
        return id;
    }

    public WindowType getWindowType() {
        return windowType;
    }

    public GlassType getGlassType() {
        return glassType;
    }

    public Boolean getProtectionGlass() {
        return protectionGlass;
    }

    public WindowProfileType getWindowProfileType() {
        return windowProfileType;
    }

    public WindowHardware getWindowHardware() {
        return windowHardware;
    }

    public Long getCountWindowSashes() {
        return countWindowSashes;
    }

    public WindowOpenType getWindowOpenType() {
        return windowOpenType;
    }

    public WindowSizeDto getWindowSize() {
        return windowSize;
    }

    public static class WindowDtoBuilder {
        private Long id;
        private WindowType windowType;
        private GlassType glassType;
        private Boolean protectionGlass;
        private WindowProfileType windowProfileType;
        private WindowHardware windowHardware;
        private Long countWindowSashes;
        private WindowOpenType windowOpenType;
        private WindowSizeDto windowSize;

        public WindowDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WindowDtoBuilder windowType(WindowType windowType) {
            this.windowType = windowType;
            return this;
        }

        public WindowDtoBuilder glassType(GlassType glassType) {
            this.glassType = glassType;
            return this;
        }

        public WindowDtoBuilder protectionGlass(Boolean protectionGlass) {
            this.protectionGlass = protectionGlass;
            return this;
        }

        public WindowDtoBuilder windowProfileType(WindowProfileType windowProfileType) {
            this.windowProfileType = windowProfileType;
            return this;
        }

        public WindowDtoBuilder windowHardware(WindowHardware windowHardware) {
            this.windowHardware = windowHardware;
            return this;
        }

        public WindowDtoBuilder countWindowSashes(Long countWindowSashes) {
            this.countWindowSashes = countWindowSashes;
            return this;
        }

        public WindowDtoBuilder windowOpenType(WindowOpenType windowOpenType) {
            this.windowOpenType = windowOpenType;
            return this;
        }

        public WindowDtoBuilder windowSize(WindowSizeDto windowSize) {
            this.windowSize = windowSize;
            return this;
        }

        public WindowDto build() {
            return new WindowDto(this);
        }

    }


}
