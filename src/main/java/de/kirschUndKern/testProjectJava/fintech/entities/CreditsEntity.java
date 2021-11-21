package de.kirschUndKern.testProjectJava.fintech.entities;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credits")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class CreditsEntity {
  @Id private  String id;
  private String customerId;
  private LocalDate dataOfCreation;
  private Long originalTermInMonths;
  private Long remainingTermInMonths;
  private Long orignialCreditAmountInCents;
  private Long currentCreditAmountInCents;
}
