package com.lcwd.electronics.store.dtos;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {


    private String userId;


    @Size(min = 3, max = 15, message = "Invalid Name !!")
    private String name;

    // @Email(message = "Invalid user email !!")
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)[a-z]{2,5}$", message = "Invalid mail address")
    @NotBlank(message = "Email is required !!")
    private String email;

    @NotBlank(message = "Password is required !!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid gender !!")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;


    // Pattern
    // custom validator


    //   @ImageNameValid
    private String imageName;


}
