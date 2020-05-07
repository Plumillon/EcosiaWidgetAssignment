package com.plumillonforge.ewa.domain.mappers;

/**
 * Created by Flavien Norindr
 */
public interface Mapper<T, L> {
    L map(T source);
}
