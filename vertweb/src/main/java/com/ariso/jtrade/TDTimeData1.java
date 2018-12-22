package com.ariso.jtrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Dates",
        "NormalizeDate",
        "Status",
        "StatusString",
        "MetaData",
        "TimeService",
        "Elements"
})
public class TDTimeData1 {

    @JsonProperty("Dates")
    private List<String>        dates                = null;
    @JsonProperty("NormalizeDate")
    private String              normalizeDate;
    @JsonProperty("Status")
    private Integer             status;
    @JsonProperty("StatusString")
    private String              statusString;
    @JsonProperty("MetaData")
    private MetaData            metaData;
    @JsonProperty("TimeService")
    private TimeService         timeService;
    @JsonProperty("Elements")
    private List<Element>       elements             = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Dates") public List<String> getDates() {
        return dates;
    }

    @JsonProperty("Dates") public void setDates(List<String> dates) {
        this.dates = dates;
    }

    @JsonProperty("NormalizeDate") public String getNormalizeDate() {
        return normalizeDate;
    }

    @JsonProperty("NormalizeDate") public void setNormalizeDate(String normalizeDate) {
        this.normalizeDate = normalizeDate;
    }

    @JsonProperty("Status") public Integer getStatus() {
        return status;
    }

    @JsonProperty("Status") public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("StatusString") public String getStatusString() {
        return statusString;
    }

    @JsonProperty("StatusString") public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    @JsonProperty("MetaData") public MetaData getMetaData() {
        return metaData;
    }

    @JsonProperty("MetaData") public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @JsonProperty("TimeService") public TimeService getTimeService() {
        return timeService;
    }

    @JsonProperty("TimeService") public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    @JsonProperty("Elements") public List<Element> getElements() {
        return elements;
    }

    @JsonProperty("Elements") public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}