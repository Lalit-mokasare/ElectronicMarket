package com.lcwd.electronics.store.services;

import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.User;

import java.util.List;



public interface UserService {

    // create

    UserDto createUser(UserDto userDto);



    // update
    UserDto updateUser(UserDto userDto, String userId);

    // delete
    void deleteUser(String userId);


    // get all users
    List<UserDto> getAllUser();

    // get single user by id

    UserDto getUser(String userId);


    // get single user by email;

    UserDto getUserByEmail(String email);

    // search user

    List<UserDto> searchUser(String keyword);

    List<UserDto> getAllUser(int pageNumber, int pageSize);

    // other user specific feature


}
