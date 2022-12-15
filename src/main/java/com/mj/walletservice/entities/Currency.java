package com.mj.walletservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Currency {
    @Id
    private String code;
    private String name;
    private String symbol;
    private Double salePrice;
    private Double purchasePrice;
}
