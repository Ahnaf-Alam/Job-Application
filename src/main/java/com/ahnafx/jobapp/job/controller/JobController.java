package com.ahnafx.jobapp.job.controller;

import com.ahnafx.jobapp.job.entity.Job;
import com.ahnafx.jobapp.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = new ArrayList<>();

        jobs = jobService.findAll();

        if(jobs == null || jobs.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping("/addJob")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job newJob = null;

        newJob = jobService.createJob(job);

        if(newJob == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        Job job = jobService.getJobById(id);
        if(job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Job>(job, HttpStatus.OK);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String id) {
        boolean deleted = jobService.deleteJobById(id);
        if(deleted) {
            return new ResponseEntity<>("Job Deleted SuccessFully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable String id, @RequestBody Job updatedJob) {
        Job job = null;

        job = jobService.updateJob(id, updatedJob);

        if(job == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Job>(job, HttpStatus.OK);
    }
}
