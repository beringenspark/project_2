package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @MappedSuperclass
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class AbtractProduct implements UpdateSelf<AbtractProduct> {
        @Id()@GeneratedValue()
        Long id;
        String productName;
        String productDescription;
        @Column(nullable = false,updatable = false)
        String productOwnerUsername;
        Double cost;

        @Override
        public void updateSelf(AbtractProduct t) {
            this.productName = t.productName;
            this.productDescription = t.productDescription;
            this.cost = t.cost;
        }
    }

