package com.sample.build.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.build.model.Build;

@Repository
public interface BuildRepository extends JpaRepository<Build, Integer> {
}
