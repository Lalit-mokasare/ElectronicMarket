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
import org.springframework.data.domain.*;

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




    // deleteUser test
    @Test
    void deleteUserTest() {

     String userId="";
     Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

     userServiceImpl.deleteUser(userId);

     Mockito.verify(userRepository,Mockito.times(1)).delete(user);


    }

    @Test
    void getAllUserTest() {



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


        List<User> userList=Arrays.asList(user,user1,user2);
        Page<User> page =new PageImpl<>(userList);


        Mockito.when(userRepository.findAll((Pageable) Mockito.any())).thenReturn(page);



        List<UserDto> allUser = userServiceImpl.getAllUser(1,2);

      //  Assertions.assertEquals(3,allUser.size()


    }

    @Test
    void getUserTest() {

        String userId ="userIdTest";
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));


        // actual call of service method

        UserDto userDto = userServiceImpl.getUser(userId);

        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getName(),userDto.getName(),"Name not matched !!");



    }

    @Test
    void getUserByEmail() {

        String emailId="lmokasare@gmail.com";
        Mockito.when(userRepository.findByEmail("lmokasare@gmail.com")).thenReturn(Optional.of(user));
        UserDto userDto = userServiceImpl.getUserByEmail(emailId);

        Assertions.assertNotNull(userDto);
        Assertions.assertEquals(user.getEmail(),userDto.getEmail(),"Email not match !!");

    }

    @Test
    void searchUserTest() {

       User user1= User.builder().name("Chetan")
                .about("This is create method")
                .email("lmokasare@gmail.com")
                .gender("Male")
                .password("Lalit@123")
                .imageName("Lalit.png")
                .build();



        User user2= User.builder().name("Vishal")
                .about("This is create method")
                .email("lmokasare@gmail.com")
                .gender("Male")
                .password("Lalit@123")
                .imageName("Lalit.png")
                .build();


        User user3= User.builder().name("Nilesh")
                .about("This is create method")
                .email("lmokasare@gmail.com")
                .gender("Male")
                .password("Lalit@123")
                .imageName("Lalit.png")
                .build();

        String keywords ="Lalit";
          Mockito.when(userRepository.findByNameContaining(keywords)).thenReturn(Arrays.asList(user,user1,user2,user3));

        List<UserDto> userDtos = userServiceImpl.searchUser(keywords);

        Assertions.assertEquals(4,userDtos.size(),"size not matched!!");


    }

    @Test
    void testGetAllUser() {
    }
}