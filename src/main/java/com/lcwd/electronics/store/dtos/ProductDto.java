package com.lcwd.electronics.store.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {




    private String productId;


    private String title;

    private String description;

    private int price;

    private int discountedPrice;

    private int quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;


}













