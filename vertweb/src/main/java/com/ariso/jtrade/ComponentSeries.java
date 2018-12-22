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
        "Type",
        "MaxValue",
        "MinValue",
        "MaxValueDate",
        "MinValueDate",
        "Values",
        "Dates"
})

public class ComponentSeries {

    @JsonProperty("Type")
    private String              type;
    @JsonProperty("MaxValue")
    private Double              maxValue;
    @JsonProperty("MinValue")
    private Double              minValue;
    @JsonProperty("MaxValueDate")
    private String              maxValueDate;
    @JsonProperty("MinValueDate")
    private String              minValueDate;
    @JsonProperty("Values")
    private List<Double>        values               = null;
    @JsonProperty("Dates")
    private Object              dates;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Type") public String getType() {
        return type;
    }

    @JsonProperty("Type") public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("MaxValue") public Double getMaxValue() {
        return maxValue;
    }

    @JsonProperty("MaxValue") public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    @JsonProperty("MinValue") public Double getMinValue() {
        return minValue;
    }

    @JsonProperty("MinValue") public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    @JsonProperty("MaxValueDate") public String getMaxValueDate() {
        return maxValueDate;
    }

    @JsonProperty("MaxValueDate") public void setMaxValueDate(String maxValueDate) {
        this.maxValueDate = maxValueDate;
    }

    @JsonProperty("MinValueDate") public String getMinValueDate() {
        return minValueDate;
    }

    @JsonProperty("MinValueDate") public void setMinValueDate(String minValueDate) {
        this.minValueDate = minValueDate;
    }

    @JsonProperty("Values") public List<Double> getValues() {
        return values;
    }

    @JsonProperty("Values") public void setValues(List<Double> values) {
        this.values = values;
    }

    @JsonProperty("Dates") public Object getDates() {
        return dates;
    }

    @JsonProperty("Dates") public void setDates(Object dates) {
        this.dates = dates;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
