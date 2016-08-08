package com.flipkart.collectionapplication.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by nitish.agarwal on 28/11/15.
 */

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@JsonSnakeCase
public class CreateCollectionRequest {

  /*@NotNull
  @JsonProperty
  private String name;

  @NotNull
  @JsonProperty
  private String work;

  @NotNull
  @JsonProperty
  private Long age;*/

  @NotNull
  @JsonProperty
  protected String seller_id;

  @NotNull
  @JsonProperty
  protected String seller_name;

  @NotNull
  @JsonProperty
  protected String merchant_transaction_id;

  @NotNull
  @JsonProperty
  protected String merchant_id;

  @NotNull
  @JsonProperty
  protected Long transaction_amount;

  @NotNull
  @JsonProperty
  protected Long retry_count;

  @NotNull
  @JsonProperty
  protected String transaction_status;

  /*@NotNull
  @JsonProperty
  protected String ui_mode;
  @NotNull
  @JsonProperty
  protected String hash_method;
  @NotNull
  @JsonProperty
  protected String hash;
  @NotNull
  @JsonProperty
  protected String merchant_key_id;
  @NotNull
  @JsonProperty
  protected String buyer_phone_no;*/

}
