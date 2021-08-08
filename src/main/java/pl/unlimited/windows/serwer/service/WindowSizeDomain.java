package pl.unlimited.windows.serwer.service;

import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

class WindowSizeDomain {
    private Long id;
    private Double width;
    private Double height;

    private WindowSizeDomain(final WindowSizeDto windowSizeDto){
        this.id = windowSizeDto.getId();
        this.width = windowSizeDto.getWidth();
        this.height = windowSizeDto.getHeight();
    }

    public static WindowSizeDomain fromDto(final WindowSizeDto windowSizeDto) {
        return new WindowSizeDomain(windowSizeDto);
    }
}
