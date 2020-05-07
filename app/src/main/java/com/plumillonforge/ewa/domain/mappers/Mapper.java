package com.plumillonforge.ewa.domain.mappers;

public interface Mapper<T, L> {
    L map(T source);
}
