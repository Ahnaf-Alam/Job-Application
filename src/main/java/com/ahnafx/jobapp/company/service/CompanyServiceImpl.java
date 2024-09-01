package com.ahnafx.jobapp.company.service;

import com.ahnafx.jobapp.company.dao.CompanyDao;
import com.ahnafx.jobapp.company.entity.Company;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;

    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDao.findAll();
    }

    @Override
    public Company updateCompany(String companyId, Company updatedCompany) {
        Optional<Company> optionalCompany = companyDao.findById(companyId);

        if(optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setCompanyName(updatedCompany.getCompanyName());
            company.setCompanyType(updatedCompany.getCompanyType());
            company.setDescription(updatedCompany.getDescription());
            company.setLocation(updatedCompany.getLocation());
            company.setJobs(updatedCompany.getJobs());
            return companyDao.saveAndFlush(company);
        }

        return null;
    }

    @Override
    public Company createCompany(Company company) {
        return companyDao.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(String companyId) {
        Company company = companyDao.findById(companyId).orElse(null);

        if(company != null) {
          companyDao.delete(company);
        } else {
            throw new DataAccessException("Company is not deleted") {};
        }
    }

    @Override
    public Company getCompany(String companyId) {
        return companyDao.findById(companyId).orElse(null);
    }
}
