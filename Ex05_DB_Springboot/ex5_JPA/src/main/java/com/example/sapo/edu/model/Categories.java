package com.example.sapo.edu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    @Column(name = "categoryCode")
    @Size(min = 1, max = 255)
    private String categoryCode;

    @Column(name = "name")
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "description")
    @Size(min = 1, max = 255)
    private String description;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;
}
