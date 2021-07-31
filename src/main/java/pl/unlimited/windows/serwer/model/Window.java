package pl.unlimited.windows.serwer.model;

import javax.persistence.*;

@Entity
public class Window {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WINDOW_10")
    private Long id;
    @Enumerated(EnumType.STRING)
    private WindowType windowType;
    @Enumerated(EnumType.STRING)
    private GlassType glassType;
    private Boolean protectionGlass;
    @Enumerated(EnumType.STRING)
    private WindowProfileType windowProfileType;
    @Enumerated(EnumType.STRING)
    private WindowHardware windowHardware;
    private Long countWindowSashes;
    @Enumerated(EnumType.STRING)
    private WindowOpenType windowOpenType;
    @ManyToOne
    private WindowSize windowSize;

    public Window() {
    }

    public Window(Long id, WindowType windowType, GlassType glassType, Boolean protectionGlass, WindowProfileType windowProfileType, WindowHardware windowHardware, Long countWindowSashes, WindowOpenType windowOpenType, WindowSize windowSize) {
        this.id = id;
        this.windowType = windowType;
        this.glassType = glassType;
        this.protectionGlass = protectionGlass;
        this.windowProfileType = windowProfileType;
        this.windowHardware = windowHardware;
        this.countWindowSashes = countWindowSashes;
        this.windowOpenType = windowOpenType;
        this.windowSize = windowSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WindowType getWindowType() {
        return windowType;
    }

    public void setWindowType(WindowType windowType) {
        this.windowType = windowType;
    }

    public GlassType getGlassType() {
        return glassType;
    }

    public void setGlassType(GlassType glassType) {
        this.glassType = glassType;
    }

    public Boolean getProtectionGlass() {
        return protectionGlass;
    }

    public void setProtectionGlass(Boolean protectionGlass) {
        this.protectionGlass = protectionGlass;
    }

    public WindowProfileType getWindowProfileType() {
        return windowProfileType;
    }

    public void setWindowProfileType(WindowProfileType windowProfileType) {
        this.windowProfileType = windowProfileType;
    }

    public WindowHardware getWindowHardware() {
        return windowHardware;
    }

    public void setWindowHardware(WindowHardware windowHardware) {
        this.windowHardware = windowHardware;
    }

    public Long getCountWindowSashes() {
        return countWindowSashes;
    }

    public void setCountWindowSashes(Long countWindowSashes) {
        this.countWindowSashes = countWindowSashes;
    }

    public WindowOpenType getWindowOpenType() {
        return windowOpenType;
    }

    public void setWindowOpenType(WindowOpenType windowOpenType) {
        this.windowOpenType = windowOpenType;
    }

    public WindowSize getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(WindowSize windowSize) {
        this.windowSize = windowSize;
    }
}
