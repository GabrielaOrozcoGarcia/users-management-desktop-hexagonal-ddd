package com.jcaa.usersmanagement.domain.valueobject;

/** Encapsula si la mascota tiene o no vacunas. */
public record MascotaVacunas(boolean value) {

  public static MascotaVacunas conVacunas()    { return new MascotaVacunas(true);  }
  public static MascotaVacunas sinVacunas()    { return new MascotaVacunas(false); }
  public static MascotaVacunas of(boolean val) { return new MascotaVacunas(val);   }

  @Override
  public String toString() { return value ? "Si" : "No"; }
}
