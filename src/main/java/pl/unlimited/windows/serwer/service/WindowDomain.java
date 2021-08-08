package pl.unlimited.windows.serwer.service;

import pl.unlimited.windows.serwer.model.*;
import pl.unlimited.windows.serwer.model.dto.WindowDto;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

class WindowDomain {

    private Long id;
    private WindowType windowType;
    private GlassType glassType;
    private Boolean protectionGlass;
    private WindowProfileType windowProfileType;
    private WindowHardware windowHardware;
    private Long countWindowSashes;
    private WindowOpenType windowOpenType;
    private WindowSizeDomain windowSize;

    private WindowDomain (WindowDto windowDto){
        this.id = windowDto.getId();
        this.windowType = windowDto.getWindowType();
        this.glassType = windowDto.getGlassType();
        this.protectionGlass = windowDto.getProtectionGlass();
        this.windowProfileType = windowDto.getWindowProfileType();
        this.windowHardware = windowDto.getWindowHardware();
        this.countWindowSashes = windowDto.getCountWindowSashes();
        this.windowOpenType = windowDto.getWindowOpenType();
        this.windowSize = WindowSizeDomain.fromDto(windowDto.getWindowSize());
    }

    public static WindowDomain fromDto(final WindowDto windowDto){
        return new WindowDomain(windowDto);
    }

}
