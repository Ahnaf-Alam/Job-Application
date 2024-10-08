package com.ahnafx.jobapp.company.dao;

import com.ahnafx.jobapp.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends JpaRepository<Company, String> {
}
