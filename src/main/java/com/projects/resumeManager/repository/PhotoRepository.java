package com.projects.resumeManager.repository;

import com.projects.resumeManager.domain.entity.Photo;
import com.projects.resumeManager.domain.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findByResume(Resume resume);
}
