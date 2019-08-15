package com.rzm.monitoring;

import com.rzm.monitoring.param.RequestParam;

import java.io.IOException;

public interface MonitoringWrapperApi<A,B extends RequestParam> {
  public A createRequest(B requestParam);
  public void sendRequest(A request) throws IOException;

}
