package com.ahnafx.jobapp.company.controller;

import com.ahnafx.jobapp.company.entity.Company;
import com.ahnafx.jobapp.company.service.CompanyService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = new ArrayList<>();

        companies = companyService.getAllCompanies();

        if(companies == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id,
                                                 @RequestBody Company updatedCompany) {
        Company toReturn = companyService.updateCompany(id, updatedCompany);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company toReturn = companyService.createCompany(company);

        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable String id) {
        try {
            companyService.deleteCompany(id);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable String id) {
        Company company = companyService.getCompany(id);

        if(company == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
