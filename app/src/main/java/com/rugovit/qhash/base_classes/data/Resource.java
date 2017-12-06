package com.rugovit.qhash.base_classes.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.rugovit.qhash.base_classes.data.ResourceStatus.*;

/**
 * Created by rugovit on 12/4/2017.
 */


public class Resource<T> {


    @NonNull
    private final ResourceStatus status;
    @Nullable
    private final T data;
    @Nullable public final String message;
    private Resource(@NonNull ResourceStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }
    @NonNull
    public ResourceStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

}