package com.flipkart.collectionapplication.resource;

/**
 * Created by nitish.agarwal on 30/11/15.
 */

import com.flipkart.collectionapplication.action.PaymentAction;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.flipkart.collectionapplication.action.CreateCollectionAction;
import com.flipkart.collectionapplication.action.FetchCollectionAction;
import com.flipkart.collectionapplication.request.CreateCollectionRequest;
import com.flipkart.collectionapplication.response.ListCollectionResponse;
import com.yammer.metrics.annotation.Timed;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.UriBuilder.fromPath;

@Path("/parameters")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)

public class CollectionResource
{

    private final Provider<CreateCollectionAction> createCollectionActionProvider;
    private final Provider<FetchCollectionAction>  fetchCollectionActionProvider;
    private final Provider<PaymentAction> paymentActionProvider;

    @Inject
    public CollectionResource(
        Provider<CreateCollectionAction> createCollectionActionProvider,
        Provider<FetchCollectionAction> fetchCollectionActionProvider,
            Provider<PaymentAction> paymentActionProvider)
    {
        this.createCollectionActionProvider = createCollectionActionProvider;
        this.fetchCollectionActionProvider = fetchCollectionActionProvider;
        this.paymentActionProvider=paymentActionProvider;
    }
    @GET
    @Path("/gateway")
    public String backend(@QueryParam("seller_id") String seller_id,
                      @QueryParam("buyer_email_address") String buyer_email_address,
                      @QueryParam("transaction_amount")Integer transaction_amount
                      ) throws Exception
    {

        return paymentActionProvider.get().payzippy(seller_id,buyer_email_address,transaction_amount);
    }

    @GET
    @Path("/{name}")
    @Timed
    public List<ListCollectionResponse.Collection> getCollection(@PathParam("name") String name) throws ParseException
    {
        return fetchCollectionActionProvider.get().withName(name).invoke();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @Transactional
    public Response xyz(@Valid CreateCollectionRequest request) throws Exception {
        createCollectionActionProvider.get().withRequest(request).invoke();
        return created(fromPath("/{external_id}").build(request.getSeller_id())).build();
    }
}

