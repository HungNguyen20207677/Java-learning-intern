package com.sapo.edu.ex5.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProductCount {
    private int categoryId;

    private String categoryCode;

    private int productCount;
}
