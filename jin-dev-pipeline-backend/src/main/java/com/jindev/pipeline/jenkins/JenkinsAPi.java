package com.jindev.pipeline.jenkins;

import com.jindev.pipeline.handler.JenkinsErrorHandler;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;

/** @see <a href="https://wiki.jenkins.io/display/JENKINS/Remote+access+API>jenkins wiki</a> */
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
    return executeWithResult(() -> jenkins.getJob(jobName));
  }

  public void createJob(String jobName, String jobXml) {
    execute(() -> jenkins.createJob(jobName, jobXml));
  }

  public QueueReference build(Job job) {
    return executeWithResult(job::build);
  }

  private void execute(Executor executor) {
    executor.exec();
  }

  private <T> T executeWithResult(ExecutorWithResult executor) {
    return (T) executor.exec();
  }

  @FunctionalInterface
  private interface Executor<E extends IOException> {
    default void exec() {
      try {
        run();
      } catch (Exception e) {
        throw JenkinsErrorHandler.handle(e);
      }
    }

    void run() throws E;
  }

  @FunctionalInterface
  public interface ExecutorWithResult<T, E extends IOException> {
    default T exec() {
      try {
        return run();
      } catch (Exception e) {
        throw JenkinsErrorHandler.handle(e);
      }
    }

    T run() throws E;
  }
}
