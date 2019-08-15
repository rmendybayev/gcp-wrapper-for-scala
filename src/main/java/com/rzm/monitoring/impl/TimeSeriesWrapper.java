package com.rzm.monitoring.impl;

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.*;
import com.google.protobuf.util.Timestamps;
import com.rzm.monitoring.MonitoringWrapperApi;
import com.rzm.monitoring.param.TimeSeriesParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeSeriesWrapper implements MonitoringWrapperApi<CreateTimeSeriesRequest, TimeSeriesParam> {

  @Override
  public CreateTimeSeriesRequest createRequest(TimeSeriesParam requestParam) {
    // Prepares an individual data point
    TimeInterval interval = TimeInterval.newBuilder()
        .setEndTime(Timestamps.fromMillis(requestParam.getTs()))
        .build();
    TypedValue value = TypedValue.newBuilder()
        .setDoubleValue(requestParam.getValue())
        .build();
    Point point = Point.newBuilder()
        .setInterval(interval)
        .setValue(value)
        .build();

    List<Point> pointList = new ArrayList<>();
    pointList.add(point);

    ProjectName name = ProjectName.of(requestParam.getProjectId());

    // Prepares the metric descriptor
    Map<String, String> metricLabels = new HashMap<String, String>();
    metricLabels.put(requestParam.getMetricLabelKey(), requestParam.getMetricLabelValue());
    Metric metric = Metric.newBuilder()
        .setType(requestParam.getMetricsType())
        .putAllLabels(metricLabels)
        .build();

    // Prepares the monitored resource descriptor
    Map<String, String> resourceLabels = new HashMap<String, String>();
    resourceLabels.put("project_id", requestParam.getProjectId());
    MonitoredResource resource = MonitoredResource.newBuilder()
        .setType(requestParam.getResourceType())
        .putAllLabels(resourceLabels)
        .build();

    // Prepares the time series request
    TimeSeries timeSeries = TimeSeries.newBuilder()
        .setMetric(metric)
        .setResource(resource)
        .addAllPoints(pointList)
        .build();
    List<TimeSeries> timeSeriesList = new ArrayList<>();
    timeSeriesList.add(timeSeries);

    return CreateTimeSeriesRequest.newBuilder()
        .setName(name.toString())
        .addAllTimeSeries(timeSeriesList)
        .build();
  }

  @Override
  public void sendRequest(CreateTimeSeriesRequest request) throws IOException {
    // Instantiates a client
    MetricServiceClient metricServiceClient = MetricServiceClient.create();

    // Writes time series data
    metricServiceClient.createTimeSeries(request);
    metricServiceClient.close();
  }
}
