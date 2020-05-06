package com.plumillonforge.ewa.domain.mappers;

import androidx.annotation.NonNull;

public interface Mapper<T, L> {
    L map(@NonNull T source);
}
