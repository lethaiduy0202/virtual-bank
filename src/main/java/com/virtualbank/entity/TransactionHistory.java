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
@Table(name = "trans_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "acc_id")
  private Account account;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "transtyoe_id")
  private TransactionType transType;
  @Column(name = "trans_note", nullable = false)
  private String transNote;
  @Column(name = "trans_date", nullable = false)
  private Date transDate;
  @Column(name = "trans_amount", nullable = false)
  private BigDecimal transAmount;
}
