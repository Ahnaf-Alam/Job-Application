package com.ahnafx.jobapp.job.service;

import com.ahnafx.jobapp.job.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job createJob(Job job);
    Job getJobById(String JobId);
    boolean deleteJobById(String jobId);
    Job updateJob(String id, Job updatedJob);
}
