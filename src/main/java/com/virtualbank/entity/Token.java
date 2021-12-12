package com.virtualbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "token_id", nullable = false, unique = true)
  private String tokenId;
  @Column(name = "user_id", nullable = false)
  private Long userId;
  @Column(name = "is_revoked", nullable = false)
  private boolean isRevoked;
}
