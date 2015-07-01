package be.gerard.core.service.builder;

import be.gerard.core.service.model.ApplicationRecord;
import be.gerard.core.service.model.PropertyRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * ApplicationBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class ApplicationBuilder {

    private final ApplicationRecord applicationRecord;

    private final BuilderContext builderContext;

    private final Set<PropertyRecord> properties = new HashSet<>();

    ApplicationBuilder(ApplicationRecord applicationRecord, BuilderContext builderContext) {
        this.applicationRecord = applicationRecord;
        this.builderContext = builderContext;
    }

    public ApplicationBuilder property(String key, String group, String value) {
        PropertyRecord property = applicationRecord.findProperty(key);

        if (property == null) {
            property = new PropertyRecord(key);
        }

        if (!properties.contains(property)) {
            properties.add(property);
        }

        property.setGroup(group);
        property.setValue(value);

        return this;
    }

    public ApplicationBuilder build() {
        applicationRecord.clearProperties();

        for (PropertyRecord property : properties) {
            applicationRecord.addProperty(property);
        }

        return this;
    }

    public void save() {
        builderContext.save(applicationRecord);
    }

}