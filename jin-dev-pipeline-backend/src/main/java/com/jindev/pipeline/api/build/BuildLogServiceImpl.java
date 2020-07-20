package com.jindev.pipeline.api.build;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.jindev.pipeline.jenkins.JenkinsAPi;
import com.offbytwo.jenkins.helper.BuildConsoleStreamListener;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuildLogServiceImpl implements BuildLogService {

    private final SimpMessagingTemplate template;
    private final JenkinsAPi jenkinsAPi;
    private final BuildDao buildDao;
//    private final BuildService buildService;

    @Override
    public void getRealtimeLog(long buildId, int buildNumber) {

        buildDao.findById(buildId).ifPresent(build -> {
            BuildConsoleStreamListener stream = new BuildConsoleStreamListener() {
                @Override
                public void onData(String newLogChunk) {
                    System.out.println("=====================================");
                    System.out.println(newLogChunk);
                    System.out.println("=====================================");
                    template.convertAndSend(
                        String.format("/queue/builds/%d", buildId), newLogChunk);
                }

                @Override
                public void finished() {
                    System.out.println("finished");
                }
            };
            jenkinsAPi.streamConsoleOutput(build.getAppName(), buildNumber, stream);

        });
    }
}
