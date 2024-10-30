package com.sapo.edu.ex5.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories {

    private int categoryId;
    private String categoryCode;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    @Override
    public String toString() {
        return categoryId + " - " + categoryCode + " - " + name + " - " + description + " - " + createdAt + " - " + updatedAt;
    }

}
