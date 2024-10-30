package com.sapo.edu.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriesDTO {

    @Size(min = 1, max = 255)
    private String categoryCode;

    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 255)
    private String description;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
