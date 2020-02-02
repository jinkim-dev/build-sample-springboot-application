package com.jindev.pipeline;

import com.jindev.pipeline.build.Build;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "build", path = "build")
public interface BuildRepository {
    List<Build> list();
}
