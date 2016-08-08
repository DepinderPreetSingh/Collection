package com.flipkart.collectionapplication.action;

/**
 * Created by depinder.preet on 03/08/16.
 */

//import net.minidev.json.JSONObject;
import com.flipkart.payzippy.checkout.enumeration.*;
import com.flipkart.payzippy.checkout.request.ChargingRequest;
import com.flipkart.payzippy.checkout.utils.HashUtility;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.apache.http.client.ClientProtocolException;

public class PaymentAction
{
    private String merchantSecret = "4d05dfaf33cc1a641ffbc1c1c767bb1c489aeab638270f40c4081af7d3a5d51d";

    public String  payzippy(String seller_id,String buyer_email_address,Integer transaction_amount)
            throws Exception
    {
       // System.out.println(seller_id);
        //System.out.println(buyer_email_address);
        //System.out.println(transaction_amount);
        ChargingRequest chargingRequest = new ChargingRequest();
        String transactionId=getMerchantTransactionId();
        chargingRequest.setBuyerUniqueId(seller_id);
        //  chargingRequest.setCallbackUrl(request.getCallbackUrl());
        chargingRequest.setCurrency(Currency.valueOf("INR"));
        chargingRequest.setMerchantTransactionId(transactionId);
        chargingRequest.setPaymentMethod(PaymentMethod.valueOf("NET"));
        //chargingRequest.setTransactionAmount(transaction_amount);
        chargingRequest.setTransactionType(TransactionType.valueOf("SALE"));
        chargingRequest.setUiMode(Secure3DRedirectMode.valueOf("REDIRECT"));
        chargingRequest.setMerchantId("seller_flipkart");
        chargingRequest.setMerchantKeyId("payment");
        chargingRequest.setBuyerEmailAddress(buyer_email_address);
        HashingMethod hm = null;
        hm=HashingMethod.MD5;
        chargingRequest.setHashMethod(HashingMethod.MD5);
        String hash = HashUtility.generateHashNew(chargingRequest, hm, merchantSecret);
        //System.out.println(hash);
        JSONObject obj = new JSONObject();
        obj.put("transaction_id",transactionId);
        obj.put("hash", hash);
        //System.out.println(obj);
        String jsonText = obj.toString();
        return jsonText;
    }

    public String getMerchantTransactionId()
    {
        String longTime = new Long((new Date().getTime())).toString();
      //  System.out.println(longTime);
        return longTime;

    }
}


