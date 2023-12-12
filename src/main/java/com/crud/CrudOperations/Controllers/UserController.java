package com.crud.CrudOperations.Controllers;

import com.crud.CrudOperations.Entity.UserEntity;
import com.crud.CrudOperations.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }



    @PostMapping("/api/user")
    public ResponseEntity<UserEntity> saveUserEntity(@RequestBody UserEntity userEntity) {
        // Make sure userRepo is not null before using it
        if (userRepo != null) {
            UserEntity savedUser = userRepo.save(userEntity);
            return ResponseEntity.ok(savedUser);
        } else {
            // Handle the case where userRepo is not properly initialized
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/api/user")
    public ResponseEntity<List<UserEntity>> getUserEntity(){
        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
    }
    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserEntity> getUserEntity(@PathVariable long id){
        Optional<UserEntity> user =userRepo.findById(id);
        if(user.isPresent()) {

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

@PutMapping("/api/user/{id}")
public ResponseEntity<UserEntity> updateUserEntity(@PathVariable long id, @RequestBody UserEntity userentity) {
    Optional<UserEntity> userOptional = userRepo.findById(id);
    if (userOptional.isPresent()) {
        UserEntity user = userOptional.get();
        user.setUserName(userentity.getUserName());
        user.setAge(userentity.getAge());
        user.setAddress(userentity.getAddress());
        user.setEmail(userentity.getEmail());
        user.setPassword(userentity.getPassword());

        // Save the updated user back to the repository
        UserEntity updatedUser = userRepo.save(user);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/api/user/{id}")

public ResponseEntity<String> deleteUserEntity(@PathVariable long id ) {
    Optional<UserEntity> userOptional = userRepo.findById(id);
    if (userOptional.isPresent()) {
        userRepo.deleteById(id);
        String yes  = "User Deleted Successfully";

        return new ResponseEntity<>(yes, HttpStatus.OK);
    } else {
        String no = "User not found check your database once to find by get all";
        return new ResponseEntity<>(no, HttpStatus.NOT_FOUND);
    }
}


    @PostMapping("/api/login")
    public Object login(@RequestBody UserEntity user) {
        // Retrieve user from the repository based on email and password
        UserEntity storedUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());

        // Check if the user exists
        if (storedUser != null) {
            // Create a map with user details
            Map<String, Object> userDetails = new HashMap<>();
            userDetails.put("message", "Login successful!");
            userDetails.put("userName", storedUser.getUserName());
            userDetails.put("age", storedUser.getAge());
            userDetails.put("address", storedUser.getAddress());
            userDetails.put("email", storedUser.getEmail());

            return userDetails;
        } else {
            // Return an error message for invalid email or password
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid email or password");
            return errorResponse;
        }
    }
}

