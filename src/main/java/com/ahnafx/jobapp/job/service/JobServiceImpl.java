package com.ahnafx.jobapp.job.service;

import com.ahnafx.jobapp.job.dao.JobDao;
import com.ahnafx.jobapp.job.entity.Job;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private JobDao jobDao;

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = new ArrayList<>();

        try {
            jobs = jobDao.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {};
        }
        return jobs;
    }

    @Override
    public Job createJob(Job job) {
        Job toReturn = null;

        try {
            toReturn = jobDao.saveAndFlush(job);
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {};
        }

        return toReturn;
    }

    @Override
    public Job getJobById(String jobId) {
        return jobDao.findById(jobId).orElse(null);
    }

    @Override
    public boolean deleteJobById(String jobId) {
        try {
            jobDao.deleteById(jobId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Job updateJob(String id, Job updatedJob) {
        Optional<Job> optionalJob = jobDao.findById(id);
        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();

            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());

            return jobDao.saveAndFlush(job);
        }

        return null;
    }
}
