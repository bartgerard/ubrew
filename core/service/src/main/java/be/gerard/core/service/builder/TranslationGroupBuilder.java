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
public class TranslationGroupBuilder extends ChildBuilder<TranslationGroupRecord, ApplicationBuilder> {

    private final Set<TranslationRecord> translations = new HashSet<>();

    public TranslationGroupBuilder(
            TranslationGroupRecord record, BuilderContext builderContext, ApplicationBuilder parent
    ) {
        super(record, builderContext, parent);
    }

    public TranslationGroupBuilder translation(
            String key, String value, String language, String prefix, TranslationType type
    ) {
        TranslationRecord translation = getRecord().findByKey(key);

        if (translation == null) {
            translation = new TranslationRecord();
            translation.setKey(key);
        }

        if (!translations.contains(translation)) {
            translations.add(translation);
        }

        translation.setValue(value);
        translation.setLanguage(language);
        translation.setPrefix(prefix);
        translation.setType(type);

        return this;
    }

    @Override
    public TranslationGroupBuilder build() {
        super.build();

        getRecord().getTranslations().clear();

        for (TranslationRecord translation : translations) {
            getRecord().getTranslations().add(translation);
        }

        return this;
    }

    @Override
    public void save() {
    }

}
