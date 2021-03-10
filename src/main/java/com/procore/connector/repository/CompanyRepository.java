package com.procore.connector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.procore.connector.models.Company;



@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

}
