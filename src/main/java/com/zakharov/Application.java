package com.zakharov;

import com.zakharov.dao.CategoryDao;
import com.zakharov.dmo.Category;
import com.zakharov.dmo.Product;
import com.zakharov.dmo.User;
import com.zakharov.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {



        ApplicationContext context = new AnnotationConfigApplicationContext("org.example");
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

        List<Product> products = productService.getProductList();

        for (Product p: products) {
            System.out.println(p);
        }

    }

    private static User getUser(String name, String surname) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);

        return user;
    }

    private static Product getProduct(String name, double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        return product;
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
