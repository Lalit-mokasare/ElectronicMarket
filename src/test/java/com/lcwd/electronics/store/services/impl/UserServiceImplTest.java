package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.User;
import com.lcwd.electronics.store.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;




    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

    User user;

    User user1;

    User user2;

    List <User> userList;

    UserDto userDto;

    @BeforeEach
    public void init(){

      user =  User.builder().name("Lalit")
                .about("This is create method")
                .email("lmokasare@gmail.com")
                .gender("Male")
                .password("Lalit@123")
                .imageName("Lalit.png")
                .build();


        user1 =  User.builder().name("Akshy")
                .about("This is create method")
                .email("Akshay@gmail.com")
                .gender("Male")
                .password("Akshay@123")
                .imageName("Akshay.png")
                .build();


        user2 =  User.builder().name("Suraj")
                .about("This is create method")
                .email("Suraj@gmail.com")
                .gender("Male")
                .password("Suraj@123")
                .imageName("Suraj.png")
                .build();


        userList = Arrays.asList(user,user1,user2);

        userDto =  UserDto.builder().name("Lalit")
                .about("This is create method")
                .email("mokasare@gmail.com")
                .gender("Male")
                .password("Mokasare@123")
                .imageName("Lalit.png")
                .build();

    }


    @Test
    void createUserTest() {

        // Arrange
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        // Actual call

        UserDto user1 = userServiceImpl.createUser(modelMapper.map(user, UserDto.class));

        System.out.println(user1.getName());

        // Assert

        Assertions.assertNotNull(user1);
        Assertions.assertEquals("Lalit",user1.getName());







    }

    @Test
    void updateUserTest() {

    String userId="anyid ";
    UserDto userDto =UserDto.builder()
            .name("Lalit Ravindra Mokasare")
            .about("This updated user about details")
            .gender("Male")
            .imageName("lalit.png")

            .build();


     Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));
     Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);


        UserDto updatedUser = userServiceImpl.updateUser(userDto, userId);
     //   UserDto updatedUser = modelMapper.map(user, UserDto.class);
        System.out.println(updatedUser.getName());
        System.out.println(updatedUser.getImageName());

        Assertions.assertNotNull(userDto);

        Assertions.assertEquals(userDto.getName(),updatedUser.getName(),"Name is not valid");

        // multiple assertion are valid...
        //

    }

    @Test
    void deleteUserTest() {

     String userId="";
     Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

     userServiceImpl.deleteUser(userId);

     Mockito.verify(userRepository,Mockito.times(1)).delete(user);


    }

    @Test
    void getAllUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void searchUser() {
    }

    @Test
    void testGetAllUser() {
    }
}