package com.ahnafx.jobapp.job.dao;

import com.ahnafx.jobapp.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job, String> {
}
