package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository <Product,String> {

// search


    List<Product> findByTittleContaining(String SubTitle);

    List<Product> findByLiveTrue(boolean live);

    // other methods
    // custom finder methods
    // query methods


}
