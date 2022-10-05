package com.burakkirbag.readingisgood.common.usecase;

public interface UseCaseHandler<E, T> {

    E handle(T useCase);
}
