package com.jindev.pipeline.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JenkinsAPi {

    private JenkinsServer jenkins;

    public JenkinsAPi(JenkinsServer jenkins) {
        this.jenkins = jenkins;
    }

    public JobWithDetails getJo(String jobName) throws IOException {
        return jenkins.getJob(jobName);
    }
}
