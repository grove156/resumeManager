package com.projects.resumeManager.repository;

import com.projects.resumeManager.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    List<Authority> findByEmail(String email);

    Authority save(Authority authority);
}
