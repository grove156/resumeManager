package com.projects.resumeManager.repository;

import com.projects.resumeManager.domain.entity.Coverletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverletterRepository extends JpaRepository<Coverletter, Long> {

}
