package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass()
@Data()
@AllArgsConstructor()
@NoArgsConstructor()
public abstract class AbstractCustomer implements UpdateSelf<AbstractCustomer>{
    @Id()
    protected String username;
    protected String password;
    protected String aboutMe;
    protected Double credits;

    @Override
    public void updateSelf(AbstractCustomer t) {
        this.aboutMe = t.aboutMe;
    }

}
