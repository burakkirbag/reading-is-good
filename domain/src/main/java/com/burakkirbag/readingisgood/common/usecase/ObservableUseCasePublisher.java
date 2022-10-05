package com.burakkirbag.readingisgood.common.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;

public class ObservableUseCasePublisher extends BeanAwareUseCasePublisher {

    public <R, T extends UseCase> void register(Class<T> useCaseClass, UseCaseHandler<R, T> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, useCaseHandler);
    }

    public <T extends UseCase> void register(Class<T> useCaseClass, VoidUseCaseHandler<T> useCaseHandler) {
        UseCaseHandlerRegistry.INSTANCE.register(useCaseClass, useCaseHandler);
    }
}