package com.ahnafx.jobapp.company.service;

import com.ahnafx.jobapp.company.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company updateCompany(String companyId, Company updatedCompany);
    Company createCompany(Company company);
    void deleteCompany(String companyId);
    Company getCompany(String companyId);
}
