package com.besaba.revonline.pastebinapi.response;

import javax.annotation.Nullable;

import lombok.NonNull;

/**
 *
 */
public class FailedResponse<T> implements Response<T> {
  private final String reason;

  public FailedResponse(final String reason) {
    this.reason = reason;
  }

  @NonNull
  @Override
  public T get() {
    // TODO replace with specific exception
    throw new RuntimeException(getError());
  }

  @Override
  public boolean hasError() {
    return true;
  }

  @Nullable
  @Override
  public String getError() {
    return reason;
  }
}
