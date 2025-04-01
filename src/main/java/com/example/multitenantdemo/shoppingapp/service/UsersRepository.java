package com.example.multitenantdemo.shoppingapp.service;

import com.example.multitenantdemo.shoppingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
