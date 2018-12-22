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
        "TradingDays",
        "FullClosures",
        "Status",
        "TypicalSessions"
})
public class TimeService {

    @JsonProperty("TradingDays")
    private List<String>        tradingDays          = null;
    @JsonProperty("FullClosures")
    private List<FullClosure>   fullClosures         = null;
    @JsonProperty("Status")
    private Integer             status;
    @JsonProperty("TypicalSessions")
    private Object              typicalSessions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("TradingDays") public List<String> getTradingDays() {
        return tradingDays;
    }

    @JsonProperty("TradingDays") public void setTradingDays(List<String> tradingDays) {
        this.tradingDays = tradingDays;
    }

    @JsonProperty("FullClosures") public List<FullClosure> getFullClosures() {
        return fullClosures;
    }

    @JsonProperty("FullClosures") public void setFullClosures(List<FullClosure> fullClosures) {
        this.fullClosures = fullClosures;
    }

    @JsonProperty("Status") public Integer getStatus() {
        return status;
    }

    @JsonProperty("Status") public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("TypicalSessions") public Object getTypicalSessions() {
        return typicalSessions;
    }

    @JsonProperty("TypicalSessions") public void setTypicalSessions(Object typicalSessions) {
        this.typicalSessions = typicalSessions;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}