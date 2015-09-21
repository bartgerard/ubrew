package be.gerard.core.service.builder;

import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.service.model.TranslationGroupRecord;
import be.gerard.core.service.model.TranslationRecord;

import java.util.HashSet;
import java.util.Set;

/**
 * PropertyGroupBuilder
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationGroupBuilder extends Builder<TranslationGroupRecord> {

    private final Set<TranslationRecord> translations = new HashSet<>();

    public TranslationGroupBuilder(TranslationGroupRecord record, BuilderContext builderContext) {
        super(record, builderContext);
    }

    public TranslationGroupBuilder translation(String key, TranslationType type, String language, String value) {
        TranslationRecord translation = getRecord().findByKey(key);

        if (translation == null) {
            translation = new TranslationRecord();
            translation.setKey(key);
        }

        if (!translations.contains(translation)) {
            translations.add(translation);
        }

        translation.setType(type);
        translation.setValue(value);
        translation.setLanguage(language);

        return this;
    }

    public TranslationGroupBuilder build() {
        getRecord().getTranslations().clear();

        for (TranslationRecord translation : translations) {
            getRecord().getTranslations().add(translation);
        }

        return this;
    }

}
