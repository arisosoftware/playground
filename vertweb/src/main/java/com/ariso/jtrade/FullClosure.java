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
        "Date",
        "Name",
        "ExchangeIDs"
})
public class FullClosure {

    @JsonProperty("Date")
    private String              date;
    @JsonProperty("Name")
    private String              name;
    @JsonProperty("ExchangeIDs")
    private String              exchangeIDs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Date") public String getDate() {
        return date;
    }

    @JsonProperty("Date") public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("Name") public String getName() {
        return name;
    }

    @JsonProperty("Name") public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("ExchangeIDs") public String getExchangeIDs() {
        return exchangeIDs;
    }

    @JsonProperty("ExchangeIDs") public void setExchangeIDs(String exchangeIDs) {
        this.exchangeIDs = exchangeIDs;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}