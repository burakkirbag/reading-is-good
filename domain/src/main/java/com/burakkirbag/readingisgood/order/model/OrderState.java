package com.burakkirbag.readingisgood.order.model;

public interface OrderState {
    public static final String PENDING = "PENDING";
    public static final String NEW = "NEW";
    public static final String PREPARING = "PREPARING";
    public static final String SHIPPED = "PENDING";
    public static final String DELIVERED = "DELIVERED";
    public static final String CANCELED = "CANCELED";
}
