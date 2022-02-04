package com.zakharov;

import com.zakharov.dao.CategoryDao;
import com.zakharov.dmo.Category;
import com.zakharov.dmo.Product;
import com.zakharov.dmo.User;
import com.zakharov.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.zakharov");

        ProductService productService = context.getBean("productService", ProductService.class);
        CategoryDao categoryDao = context.getBean("categoryDao",CategoryDao.class);

        Category vegetable = getCategory("Vegetable");
        categoryDao.save(vegetable);

        Category fruit = getCategory("Fruit");
        categoryDao.save(fruit);

        Product potato = getProduct("Potato",10.50,vegetable);
        Product tomato = getProduct("Tomato",12.00,vegetable);
        Product apple = getProduct("Apple", 15.00, fruit);
        Product pear = getProduct("Pear", 16,fruit);

        productService.addProduct(potato);
        productService.addProduct(tomato);
        productService.addProduct(apple);
        productService.addProduct(pear);

        List<Product> products;

        System.out.println("Existing products");
        products = productService.getProductList();
        for (Product p: products) {
            System.out.println(p);
        }

        System.out.println("Delete product with id 1");
        productService.deleteProduct(1);
        products = productService.getProductList();
        for (Product p: products) {
            System.out.println(p);
        }

        System.out.println("Update tomato price");
        tomato.setPrice(22.22);
        productService.updateProduct(tomato);
        System.out.println(productService.getProductById(tomato.getId()));


    }

    private static Product getProduct(String name, double price,Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
    private static Category getCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}
