package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AbstractProductOrder implements UpdateSelf<AbstractProductOrder> {
    @Id() @GeneratedValue()
    protected Long id;
    @Column(nullable = false, updatable = false)
    protected Long productId;
    protected Long quantity;
    protected String orderUsername;


    @Override
    public void updateSelf(AbstractProductOrder t) {
        this.quantity = t.quantity;
    }
}
