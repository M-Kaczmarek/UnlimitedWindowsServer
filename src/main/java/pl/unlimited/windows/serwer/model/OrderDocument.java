package pl.unlimited.windows.serwer.model;

import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;

import javax.persistence.*;

@Entity
public class OrderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "SEQ_ORDER_20", allocationSize = 1)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Window window;

    public OrderDocument() {
    }

    public OrderDocument(Long id, String name, Window window) {
        this.id = id;
        this.name = name;
        this.window = window;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .name(name)
                .window(window.toDto())
                .build();
    }

}
