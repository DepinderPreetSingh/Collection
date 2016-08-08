package com.flipkart.collectionapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Table(name = "example2")
public class Collection extends Auditable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @NotNull
  protected String seller_id;
  @NotNull
  protected String seller_name;
  @NotNull
  protected  String merchant_transaction_id;
  @NotNull
  protected String merchant_id;
  @NotNull
  protected Long transaction_amount;
  @NotNull
  protected Long retry_count;
  @NotNull
  protected String transaction_status;
 // public java.util.Date created;
  /*@NotNull
  protected String currency;
  @NotNull
  protected String ui_mode;
  @NotNull
  protected String hash_method;
  @NotNull
  protected String hash;
  @NotNull
  protected String merchant_key_id;
  @NotNull
  protected String buyer_phone_no;*/

}
