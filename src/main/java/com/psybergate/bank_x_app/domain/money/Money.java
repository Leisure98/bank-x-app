package com.psybergate.bank_x_app.domain.money;

import java.math.BigDecimal;

/**
 * The immutable money abstraction for the application.
 */
@SuppressWarnings("unused")
public class Money {

  public static final Money ZERO = new Money();

  public static final Money JOINING_BONUS = new Money("500");

  private final BigDecimal value;

  public Money() {
    this.value = new BigDecimal("0");
  }

  public Money(BigDecimal value) {
    this.value = value;
  }

  public Money(String value) {
    this(new BigDecimal(value));
  }

  public Money(double value) {
    this(BigDecimal.valueOf(value));
  }

  public Money add(String value) {
    return new Money(this.value.add(new BigDecimal(value)));
  }

  public Money add(Money money) {
    return new Money(this.value.add(money.value));
  }

  public Money subtract(String value) {
    return new Money(this.value.subtract(new BigDecimal(value)));
  }

  public Money subtract(Money money) {
    return new Money(this.value.subtract(money.value));
  }

  public Money multiply(String value) {
    return new Money(this.value.multiply(new BigDecimal(value)));
  }

  public boolean moreThan(String value) {
    return (this.value.compareTo(new BigDecimal(value)) > 0);
  }

  public boolean lessThan(String value) {
    return (this.value.compareTo(new BigDecimal(value)) < 0);
  }

  public BigDecimal getValue() {
    return value;
  }
}