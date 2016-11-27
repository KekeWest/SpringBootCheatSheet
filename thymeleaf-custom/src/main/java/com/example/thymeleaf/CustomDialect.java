package com.example.thymeleaf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

import com.example.URL;

/**
 * Thymeleafにアプリ独自の機能を追加するための{@link IDialect}
 *
 * @author keke
 *
 */
@Component
public class CustomDialect extends AbstractDialect implements IExpressionEnhancingDialect {

    private Map<String, Object> expressionObjects;

    @PostConstruct
    public void init() {
        Map<String, Object> objects = new HashMap<>();
        objects.put("url", new URL());
        expressionObjects = Collections.unmodifiableMap(objects);
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
        return expressionObjects;
    }

}
