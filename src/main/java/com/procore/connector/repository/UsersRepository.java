package com.procore.connector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.procore.connector.models.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

}
