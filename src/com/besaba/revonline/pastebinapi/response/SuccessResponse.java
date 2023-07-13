package com.besaba.revonline.pastebinapi.response;


import javax.annotation.Nullable;

import lombok.NonNull;
/**
 *
 */
public class SuccessResponse<T> implements Response<T> {
  private final T result;

  public SuccessResponse(final T result) {
    this.result = result;
  }

  @NonNull
  @Override
  public T get() {
    return result;
  }

  @Override
  public boolean hasError() {
    return false;
  }

  @Nullable
  @Override
  public String getError() {
    return null;
  }
}
