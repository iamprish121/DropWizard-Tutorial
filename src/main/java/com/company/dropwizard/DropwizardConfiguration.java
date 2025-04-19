package com.company.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class DropwizardConfiguration extends Configuration implements MyConfiguredBundleConfig {
    // TODO: implement service configuration
    @JsonProperty
    private String mySetting;

    private String bundleSpecificConfig;
    // Add this for the "health" field
    private Map<String, Object> health;
    @Override
    public String getBundleSpecificConfig() {
        return bundleSpecificConfig;
    }
    // Add getter and setter for YAML parsing
    public void setBundleSpecificConfig(String value) {
        this.bundleSpecificConfig = value;
    }

    public String getMySetting() {
        return mySetting;
    }

    public void setMySetting(String mySetting) {
        this.mySetting = mySetting;
    }

    @JsonProperty
    public Map<String, Object> getHealth() {
        return health;
    }

    @JsonProperty
    public void setHealth(Map<String, Object> health) {
        this.health = health;
    }
}
