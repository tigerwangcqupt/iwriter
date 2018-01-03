package com.yryz.writer.modules.id.service.worker;


import com.yryz.writer.modules.id.utils.ValuedEnum;

/**
 * WorkerNodeType
 * <li>CONTAINER: Such as Docker
 * <li>ACTUAL: Actual machine
 * 
 * @author
 */
public enum WorkerNodeType implements ValuedEnum<Integer> {

    CONTAINER(1), ACTUAL(2);

    /**
     * Lock type
     */
    private final Integer type;

    /**
     * Constructor with field of type
     */
    private WorkerNodeType(Integer type) {
        this.type = type;
    }

    @Override
    public Integer value() {
        return type;
    }

}
