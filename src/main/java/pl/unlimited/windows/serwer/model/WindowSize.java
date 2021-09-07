package pl.unlimited.windows.serwer.model;

import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;

import javax.persistence.*;
import java.util.Set;

@Entity
public class WindowSize {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "window_size_seq")
    @SequenceGenerator(name = "window_size_seq", sequenceName = "SEQ_WINDOW_SIZE_15", allocationSize = 1)

    private Long id;
    private Double width;
    private Double height;
    @OneToMany(mappedBy = "windowSize")
    private Set<Window> window;

    public WindowSize(Long id, Double width, Double height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public WindowSize() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public WindowSizeDto toDto() {
        return new WindowSizeDto.WindowSizeBuilder()
                .id(id)
                .height(height)
                .width(width)
                .build();
    }
}
