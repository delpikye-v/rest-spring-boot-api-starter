package com.dp.spring.template.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class JsonPropertyFilter extends SimpleBeanPropertyFilter {
    public JsonPropertyFilter() {
    }

    private boolean parseBooleanExpression(String expStr, Object object) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expStr);
        EvaluationContext context = new StandardEvaluationContext(object);
        return (Boolean)exp.getValue(context, Boolean.class);
    }

    public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
        if (this.include(writer)) {
            JsonIgnoreWhen jsonIgnoreWhen = (JsonIgnoreWhen)writer.getAnnotation(JsonIgnoreWhen.class);
            if (jsonIgnoreWhen != null) {
                String expStr = jsonIgnoreWhen.value();
                boolean result = this.parseBooleanExpression(expStr, pojo);
                if (result) {
                    return;
                }
            }

            JsonPropertyWhen jsonPropertyWhen = (JsonPropertyWhen)writer.getAnnotation(JsonPropertyWhen.class);
            if (jsonPropertyWhen != null) {
                String expStr = jsonPropertyWhen.when();
                boolean result = this.parseBooleanExpression(expStr, pojo);
                if (result) {
                    Object value = ((BeanPropertyWriter)writer).getMember().getValue(pojo);
                    jgen.writeObjectField(jsonPropertyWhen.value(), value);
                    return;
                }
            }

            writer.serializeAsField(pojo, jgen, provider);
        } else if (!jgen.canOmitFields()) {
            writer.serializeAsOmittedField(pojo, jgen, provider);
        }

    }

    protected boolean include(BeanPropertyWriter writer) {
        return true;
    }

    protected boolean include(PropertyWriter writer) {
        return true;
    }
}
