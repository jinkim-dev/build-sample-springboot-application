package com.jindev.pipeline.api.build;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildDao extends JpaRepository<Build, Long> {

}
