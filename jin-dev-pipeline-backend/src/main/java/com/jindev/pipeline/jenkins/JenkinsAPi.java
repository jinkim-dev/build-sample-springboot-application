package com.jindev.pipeline.jenkins;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.helper.BuildConsoleStreamListener;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;

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

  public void streamConsoleOutput(String jobName, int buildNumber,
    BuildConsoleStreamListener stream) {
    execute(
      () ->
        jenkins
          .getJob(jobName)
          .getBuilds()
          .get(buildNumber - 1)
          .details()
          .streamConsoleOutput(stream,
            1,
            360));
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
  private interface Executor<E extends Exception> {

    default void exec() {
      try {
        run();
      } catch (Exception e) {
        JenkinsErrorHandler.DEFAULT_JENKINS_ERROR_HANDLER.handle(e);
      }
    }

    void run() throws E;
  }

  @FunctionalInterface
  public interface ExecutorWithResult<T, E extends Exception> {

    default T exec() {
      try {
        return run();
      } catch (Exception e) {
        JenkinsErrorHandler.DEFAULT_JENKINS_ERROR_HANDLER.handle(e);
        return null;
      }
    }

    T run() throws E;
  }
}
