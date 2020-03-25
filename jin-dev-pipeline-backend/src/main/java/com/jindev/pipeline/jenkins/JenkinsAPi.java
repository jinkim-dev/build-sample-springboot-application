package com.jindev.pipeline.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @see <a href="https://wiki.jenkins.io/display/JENKINS/Remote+access+API>jenkins wiki</a>
 */
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

  private <R> R executeWithResult(ExecutorWithResult executor) {
    R exec = (R) executor.exec();
    return exec;
  }

  @FunctionalInterface
  private interface Executor<E extends IOException> {
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
    default T exec() throws RuntimeException {
      try {
        return (T) run();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }
    T run() throws E;
  }
}
