package com.projects.resumeManager.repository;

import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByUser(User user);
}
