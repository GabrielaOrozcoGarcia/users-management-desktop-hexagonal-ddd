package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidMascotaPesoException;

public record MascotaPeso(double value) {

  public MascotaPeso {
    if (value <= 0) {
      throw InvalidMascotaPesoException.becauseValueIsNotPositive(value);
    }
  }

  @Override
  public String toString() { return String.valueOf(value); }
}
