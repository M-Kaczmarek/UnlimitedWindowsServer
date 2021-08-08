package pl.unlimited.windows.serwer.model;

import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

import javax.persistence.*;

@Entity
public class OrderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_20")
    private Long id;
    @OneToOne
    private Window window;

    public OrderDocument() {
    }

    public OrderDocument(Long id, Window window) {
        this.id = id;
        this.window = window;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public OrderDocumentDto toDto(){
        return new OrderDocumentDto.OrderDocumentDtoBuilder()
                .id(id)
                .window(window.toDto())
                .build();
    }
}
