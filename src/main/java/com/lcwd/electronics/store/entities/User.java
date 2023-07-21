package com.lcwd.electronics.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "users_name")
    private String name;

    @Column(name = "users_email", unique = true)
    private String email;

    @Column(name = "users_password", length = 10)
    private String password;

    @Column(name = "users_gender")
    private String gender;

    @Column(name = "users_about", length = 100)
    private String about;

    @Column(name = "user_imagename")
    private String imageName;


}



