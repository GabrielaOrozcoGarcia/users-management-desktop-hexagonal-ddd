package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import java.io.PrintStream;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConsoleIO {

  private final Scanner scanner;
  private final PrintStream out;

  // ── input ─────────────────────────────────────────────────────────────────

  public String readRequired(final String prompt) {
    String value;
    do {
      out.print(prompt);
      value = scanner.nextLine().trim();
      if (value.isBlank()) {
        out.println("  Value cannot be blank. Please try again.");
      }
    } while (value.isBlank());
    return value;
  }

  public String readOptional(final String prompt) {
    out.print(prompt);
    return scanner.nextLine().trim();
  }

  public int readInt(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim();
      try {
        return Integer.parseInt(raw);
      } catch (final NumberFormatException ignored) {
        out.println("  Invalid input. Please enter a whole number.");
      }
    }
  }

  public double readDouble(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim();
      try {
        return Double.parseDouble(raw);
      } catch (final NumberFormatException ignored) {
        out.println("  Invalid input. Please enter a decimal number (e.g. 12.5).");
      }
    }
  }

  public boolean readBoolean(final String prompt) {
    while (true) {
      out.print(prompt);
      final String raw = scanner.nextLine().trim().toLowerCase();
      if (raw.equals("s") || raw.equals("si") || raw.equals("true") || raw.equals("1")) {
        return true;
      } else if (raw.equals("n") || raw.equals("no") || raw.equals("false") || raw.equals("0")) {
        return false;
      } else {
        out.println("  Invalid input. Please enter S/N.");
      }
    }
  }

  // ── output ────────────────────────────────────────────────────────────────

  public void println(final String message) {
    out.println(message);
  }

  public void println() {
    out.println();
  }

  public void printf(final String format, final Object... args) {
    out.printf(format, args);
  }
}
