package com.burakkirbag.readingisgood.common.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;

public interface UseCasePublisher {

    <R, T extends UseCase> R publish(Class<R> returnClass, T useCase);

    <R, T extends UseCase> void publish(T useCase);
}
