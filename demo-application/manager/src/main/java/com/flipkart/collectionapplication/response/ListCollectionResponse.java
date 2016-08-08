package com.flipkart.collectionapplication.response;

/**
 * Created by nitish.agarwal on 30/11/15.
 */

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

@Data
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCollectionResponse {

    private Long totalCount;
    private List<Collection> collection = Lists.newArrayList();

    @Data
    @JsonSnakeCase
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Collection
    {
        /*protected String name;
        protected String work;
        protected Long age;*/

        protected String seller_id;
        protected String seller_name;
        protected String merchant_transaction_id;
        protected String merchant_id;
        protected Long transaction_amount;
        protected String retry_count;
        protected String transaction_status;
      //  protected String created;
        //@JsonDeserialize(using= LocalDateTimeDeserializer.class)
       // private java.sql.Timestamp createdAt;

       /* protected String ui_mode;
        protected String hash_method;
        protected String hash;
        protected String merchant_key_id;
        protected String buyer_phone_no;*/
    }
}

