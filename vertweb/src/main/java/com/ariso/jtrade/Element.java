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
        "Label",
        "Type",
        "Error",
        "QuoteTimeLast",
        "CompanyName",
        "IssueType",
        "Symbol",
        "Status",
        "UtcOffsetMinutes",
        "ExchangeId",
        "Currency",
        "Meta",
        "Dates",
        "OverlayIndicators",
        "ComponentSeries"
})
public class Element {

    @JsonProperty("Label")
    private String                label;
    @JsonProperty("Type")
    private String                type;
    @JsonProperty("Error")
    private Object                error;
    @JsonProperty("QuoteTimeLast")
    private Object                quoteTimeLast;
    @JsonProperty("CompanyName")
    private String                companyName;
    @JsonProperty("IssueType")
    private String                issueType;
    @JsonProperty("Symbol")
    private String                symbol;
    @JsonProperty("Status")
    private Integer               status;
    @JsonProperty("UtcOffsetMinutes")
    private Integer               utcOffsetMinutes;
    @JsonProperty("ExchangeId")
    private String                exchangeId;
    @JsonProperty("Currency")
    private String                currency;
    @JsonProperty("Meta")
    private Meta                  meta;
    @JsonProperty("Dates")
    private Object                dates;
    @JsonProperty("OverlayIndicators")
    private List<Object>          overlayIndicators    = null;
    @JsonProperty("ComponentSeries")
    private List<ComponentSeries> componentSeries      = null;
    @JsonIgnore
    private Map<String, Object>   additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Label") public String getLabel() {
        return label;
    }

    @JsonProperty("Label") public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("Type") public String getType() {
        return type;
    }

    @JsonProperty("Type") public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Error") public Object getError() {
        return error;
    }

    @JsonProperty("Error") public void setError(Object error) {
        this.error = error;
    }

    @JsonProperty("QuoteTimeLast") public Object getQuoteTimeLast() {
        return quoteTimeLast;
    }

    @JsonProperty("QuoteTimeLast") public void setQuoteTimeLast(Object quoteTimeLast) {
        this.quoteTimeLast = quoteTimeLast;
    }

    @JsonProperty("CompanyName") public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("CompanyName") public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("IssueType") public String getIssueType() {
        return issueType;
    }

    @JsonProperty("IssueType") public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    @JsonProperty("Symbol") public String getSymbol() {
        return symbol;
    }

    @JsonProperty("Symbol") public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("Status") public Integer getStatus() {
        return status;
    }

    @JsonProperty("Status") public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("UtcOffsetMinutes") public Integer getUtcOffsetMinutes() {
        return utcOffsetMinutes;
    }

    @JsonProperty("UtcOffsetMinutes") public void setUtcOffsetMinutes(Integer utcOffsetMinutes) {
        this.utcOffsetMinutes = utcOffsetMinutes;
    }

    @JsonProperty("ExchangeId") public String getExchangeId() {
        return exchangeId;
    }

    @JsonProperty("ExchangeId") public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    @JsonProperty("Currency") public String getCurrency() {
        return currency;
    }

    @JsonProperty("Currency") public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("Meta") public Meta getMeta() {
        return meta;
    }

    @JsonProperty("Meta") public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("Dates") public Object getDates() {
        return dates;
    }

    @JsonProperty("Dates") public void setDates(Object dates) {
        this.dates = dates;
    }

    @JsonProperty("OverlayIndicators") public List<Object> getOverlayIndicators() {
        return overlayIndicators;
    }

    @JsonProperty("OverlayIndicators") public void setOverlayIndicators(List<Object> overlayIndicators) {
        this.overlayIndicators = overlayIndicators;
    }

    @JsonProperty("ComponentSeries") public List<ComponentSeries> getComponentSeries() {
        return componentSeries;
    }

    @JsonProperty("ComponentSeries") public void setComponentSeries(List<ComponentSeries> componentSeries) {
        this.componentSeries = componentSeries;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}