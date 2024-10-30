package com.sapo.edu.ex5.jdbc.service;

import org.springframework.stereotype.Service;

@Service
public interface CategoriesService {

    void getAllCategories();

    void createCategory();

    void updateCategory();

    void deleteCategory();
}
