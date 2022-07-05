package com.dp.spring.template.utils.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JsonFilterAnnotationIntrospector extends JacksonAnnotationIntrospector {
    public static final String DEFAULT_FILTER_ID = JsonFilterAnnotationIntrospector.class.getName() + "#FILTER";
    private final String filterId;

    public JsonFilterAnnotationIntrospector() {
        this(DEFAULT_FILTER_ID);
    }

    public JsonFilterAnnotationIntrospector(String filterId) {
        this.filterId = filterId;
    }

    public Object findFilterId(Annotated ann) {
        Object id = super.findFilterId(ann);
        if (id == null) {
            JavaType javaType = TypeFactory.defaultInstance().constructType(ann.getRawType());
            if (!javaType.isContainerType()) {
                id = this.filterId;
            }
        }

        return id;
    }
}
