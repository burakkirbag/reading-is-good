package com.burakkirbag.readingisgood.common.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UseCaseHandlerRegistry {

    private Map<Class<? extends UseCase>, UseCaseHandler<?, ? extends UseCase>> registryForUseCaseHandlers;
    private Map<Class<? extends UseCase>, VoidUseCaseHandler<? extends UseCase>> registryForVoidUseCaseHandlers;

    public static final UseCaseHandlerRegistry INSTANCE = new UseCaseHandlerRegistry();

    private UseCaseHandlerRegistry() {
        registryForUseCaseHandlers = new HashMap<>();
        registryForVoidUseCaseHandlers = new HashMap<>();
    }

    public <R, T extends UseCase> void register(Class<T> key, UseCaseHandler<R, T> useCaseHandler) {
        registryForUseCaseHandlers.put(key, useCaseHandler);
    }

    public <T extends UseCase> void register(Class<T> key, VoidUseCaseHandler<T> useCaseHandler) {
        registryForVoidUseCaseHandlers.put(key, useCaseHandler);
    }

    public UseCaseHandler<?, ? extends UseCase> detectUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return registryForUseCaseHandlers.get(useCaseClass);
    }

    public VoidUseCaseHandler<? extends UseCase> detectVoidUseCaseHandlerFrom(Class<? extends UseCase> useCaseClass) {
        return registryForVoidUseCaseHandlers.get(useCaseClass);
    }
}
