package com.crud.CrudOperations.Repository;

import com.crud.CrudOperations.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {


    UserEntity findByEmailAndPassword(String email, String password);

}
