package com.virtualbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoicestype")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoicesType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "invoices_type")
  private String invoicesType;
//  @OneToOne(mappedBy = "invoicesType")
//  private Invoices invoices;

}
