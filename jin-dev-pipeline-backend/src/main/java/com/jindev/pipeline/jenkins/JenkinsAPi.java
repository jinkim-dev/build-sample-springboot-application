package com.jindev.pipeline.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JenkinsAPi {

  private JenkinsServer jenkins;

  public JenkinsAPi(JenkinsServer jenkins) {
    this.jenkins = jenkins;
  }

  public Map<String, Job> getJobs() {
    return executeWithResult(() -> jenkins.getJobs());
  }

  public JobWithDetails getJob(String jobName) {
    JobWithDetails details = executeWithResult(() -> jenkins.getJob(jobName));
    return details;
  }

  public void run(String jobName, String jobXml) {
    execute(() -> jenkins.createJob(jobName, jobXml));
  }

  public void execute(Executor executor) {
    executor.exec();
  }

  public <R> R executeWithResult(ExecutorWithResult executor) {
    return (R) executor.exec();
  }

  @FunctionalInterface
  public interface Executor<E extends IOException> {
    default void exec() throws RuntimeException {
      try {
        run();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }
    void run() throws E;
  }

  @FunctionalInterface
  public interface ExecutorWithResult<T, E extends IOException> {
    default <T> T exec() throws RuntimeException {
      try {
        return (T) run();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }
    T run() throws E;
  }
}
