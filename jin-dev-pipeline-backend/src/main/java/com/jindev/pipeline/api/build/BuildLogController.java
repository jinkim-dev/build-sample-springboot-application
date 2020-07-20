package com.jindev.pipeline.api.build;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BuildLogController {

    private final BuildLogService buildLogService;
    private final SimpMessagingTemplate template;

    @MessageMapping("/builds/{buildId}/logs/{buildNumber}")
    @SendTo("/queue/builds/{buildId}")
    public void getLogs(@DestinationVariable int buildId, @DestinationVariable int buildNumber) {
        buildLogService.getRealtimeLog(buildId, buildNumber);
    }
}
