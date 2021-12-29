package com.virtualbank.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoices {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "bill_month", nullable = false)
  private String billMonth;
  @Column(name = "consumption", nullable = true)
  private Long consumption;
  @Column(name = "bill_amount", nullable = false)
  private BigDecimal billAmount;
  @Column(name = "is_pay", nullable = true)
  private Boolean isPay;
  @Column(name = "date_pay", nullable = true)
  private Date datePay;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "invoicetype_id")
  private InvoicesType invoicesType;
}
