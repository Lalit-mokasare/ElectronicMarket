package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.User;
import com.lcwd.electronics.store.exception.ResourceNotFoundException;
import com.lcwd.electronics.store.repositories.UserRepository;
import com.lcwd.electronics.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;








@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // creating categoryId randomly
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);



        User user = this.modelMapper.map(userDto, User.class);
        User user2 = this.userRepository.save(user);

        return this.modelMapper.map(user2,UserDto.class);
    }

  @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found given id"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updateUser = this.userRepository.save(user);

        return this.modelMapper.map(updateUser,UserDto.class);

    }


   @Override
    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found given id"));
        this.userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto getUser(String userId) {
        Optional<User> user = this.userRepository.findById(userId);

        return this.modelMapper.map(user,UserDto.class);
    }

//    @Override
//    public UserDto getUserByEmail(String email) {
//
//        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not Found Your email id  !! "));
//
//
//        return this.modelMapper.map(user,UserDto.class);
//    }
//
//    @Override
//    public List<UserDto> searchUser(String keyword) {
//
//        List<User> users = this.userRepository.findByNameContaining(keyword);
//        List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//
//        return userDtos;
//    }
//}
*/