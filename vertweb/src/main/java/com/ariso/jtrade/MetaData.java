package com.ariso.jtrade;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "TimingData",
        "TimingRender"
})
public class MetaData {

    @JsonProperty("TimingData")
    private Object              timingData;
    @JsonProperty("TimingRender")
    private Double              timingRender;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TimingData") public Object getTimingData() {
        return timingData;
    }

    @JsonProperty("TimingData") public void setTimingData(Object timingData) {
        this.timingData = timingData;
    }

    @JsonProperty("TimingRender") public Double getTimingRender() {
        return timingRender;
    }

    @JsonProperty("TimingRender") public void setTimingRender(Double timingRender) {
        this.timingRender = timingRender;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}