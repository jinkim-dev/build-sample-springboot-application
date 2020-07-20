package com.jindev.pipeline.api.build;

public interface BuildLogService {

    void getRealtimeLog(long buildId, int buildNumber);
}
