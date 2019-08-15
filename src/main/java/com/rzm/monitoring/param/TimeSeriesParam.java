package com.rzm.monitoring.param;

import java.util.Objects;

public class TimeSeriesParam extends RequestParam {
  private String projectId;
  private Double value;
  private Long ts;

  private String metricLabelKey;
  private String metricLabelValue;
  private String metricsType;

  private String resourceType;

  public TimeSeriesParam() {}

  public TimeSeriesParam(String projectId, Double value, Long ts, String metricLabelKey, String metricLabelValue, String metricsType, String resourceType) {
    this.projectId = projectId;
    this.value = value;
    this.ts = ts;
    this.metricLabelKey = metricLabelKey;
    this.metricLabelValue = metricLabelValue;
    this.metricsType = metricsType;
    this.resourceType = resourceType;
  }

  public static TimeSeriesParam buildTs(String projectId, Double value, Long ts, String metricLabelKey, String metricLabelValue, String metricsType, String resourceType) {
    TimeSeriesParam tsp = new TimeSeriesParam();
    tsp.setProjectId(projectId);
    return tsp;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Long getTs() {
    return ts;
  }

  public void setTs(Long ts) {
    this.ts = ts;
  }

  public String getMetricLabelKey() {
    return metricLabelKey;
  }

  public void setMetricLabelKey(String metricLabelKey) {
    this.metricLabelKey = metricLabelKey;
  }

  public String getMetricLabelValue() {
    return metricLabelValue;
  }

  public void setMetricLabelValue(String metricLabelValue) {
    this.metricLabelValue = metricLabelValue;
  }

  public String getMetricsType() {
    return metricsType;
  }

  public void setMetricsType(String metricsType) {
    this.metricsType = metricsType;
  }

  public String getResourceType() {
    return resourceType;
  }

  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TimeSeriesParam that = (TimeSeriesParam) o;
    return Objects.equals(projectId, that.projectId) &&
        Objects.equals(value, that.value) &&
        Objects.equals(ts, that.ts) &&
        Objects.equals(metricLabelKey, that.metricLabelKey) &&
        Objects.equals(metricLabelValue, that.metricLabelValue) &&
        Objects.equals(metricsType, that.metricsType) &&
        Objects.equals(resourceType, that.resourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, value, ts, metricLabelKey, metricLabelValue, metricsType, resourceType);
  }
}
