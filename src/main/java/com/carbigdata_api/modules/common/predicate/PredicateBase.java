package com.carbigdata_api.modules.common.predicate;

import com.querydsl.core.BooleanBuilder;
import org.springframework.util.ObjectUtils;

public class PredicateBase {

    protected BooleanBuilder builder;

    public PredicateBase() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    protected boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

}
