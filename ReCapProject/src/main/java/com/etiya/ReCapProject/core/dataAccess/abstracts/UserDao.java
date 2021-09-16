package com.etiya.ReCapProject.core.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.core.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
