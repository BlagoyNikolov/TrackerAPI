package com.financeapi.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "currencies")
public class Currency {

  @Id
  @Column(name = "currency_id")
  private String currencyId;

//  @Column(name = "currency_code")
//  private String currencyCode;

  @Column(name = "language")
  private String language;

  @Column(name = "region")
  private String region;
}
