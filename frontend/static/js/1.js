var seller_name;
var seller_id;
var seller_email_address;
var transaction_amount;
/****************************************************  PAYMENT ***************************************************************/
function payment(name,email,amount)
{
	seller_name=name;
	seller_email_address=email;
	transaction_amount=amount;
	seller_id=getSellerId();
	var parameters={};
	parameters= payzippyParameters();
	//saveTransaction(parameters.merchant_transaction_id,"fail");
	console.log(parameters);
	saveTransaction(parameters.merchant_transaction_id,"failure");
	//saveTransaction(parameters.merchant_transaction_id);
	//saveTransaction2(parameters.merchant_transaction_id);
	hitpayzippy(parameters);
	var payzippyResponse;
	//payzippyResponse=callpayzippy();
	var Collection;
	//Collection=saveTransaction();
}

/****************************************************  SELLER ID ***************************************************************/
function getSellerId()
{
	var xmlhttp=new XMLHttpRequest();
	var URLBase="http://seller-master.nm.flipkart.com:10000/v3/sellers?";
	var url=URLBase+"email_id="+seller_email_address+"&display_name="+seller_name;
	xmlhttp.open("GET", url,false);
	xmlhttp.setRequestHeader("X_SM_CLIENT_ID", "mpacc");
	xmlhttp.setRequestHeader("Content-type", "application/json");
	xmlhttp.send();
	var seller_master={};
	seller_master=JSON.parse(xmlhttp.responseText);
	//console.log(seller_master);
	return seller_master.list[0].seller_id;
}
/**************************************************** FromBackend ***************************************************************/
function FromBackend(parameters)
{
	var xmlhttp=new XMLHttpRequest();
	var URLBase="http://0.0.0.0:63821/parameters/gateway?";
	var url=URLBase+"seller_id="+seller_id+"&buyer_email_address="+seller_email_address+"&transaction_amount="+transaction_amount;
	xmlhttp.open("GET",url,false);
	xmlhttp.setRequestHeader("Content-type", "application/json");
	xmlhttp.send();
	console.log(xmlhttp.responseText);
	var returned=JSON.parse(xmlhttp.responseText);
	parameters.merchant_transaction_id=returned.transaction_id;
	parameters.hash=returned.hash;
}
/**************************************************** payzippy parameters ***************************************************************/
function payzippyParameters()
{
	var xmlhttp = new XMLHttpRequest();
	var parameters={};
	parameters.merchant_id="seller_flipkart";
	parameters.currency="INR";
	parameters.transaction_type="SALE";
	parameters.transaction_amount=transaction_amount;
	parameters.payment_method="NET";
	parameters.ui_mode="REDIRECT";
	parameters.callback_url="";
	parameters.buyer_email_address=seller_email_address;
	parameters.hash_method="MD5";
	parameters.merchant_key_id="payment";
	FromBackend(parameters);	//these will be fetched using SDK client
	return parameters;
}
/**************************************************** saveTransacion ***************************************************************/
function saveTransaction(id,status)			//saving transaction to collection framework database 
{
	var xmlhttp = new XMLHttpRequest();
	var url = "http://0.0.0.0:63821/parameters";
	xmlhttp.open("POST", url, false);
	xmlhttp.setRequestHeader("Content-type", "application/json");
	console.log(xmlhttp.responseText)
	var collection = JSON.stringify({
	"seller_id": seller_id,
	"seller_name": seller_name,
	"merchant_transaction_id": id,
	"merchant_id": "seller_flipkart",
	"transaction_amount": transaction_amount,
	"retry_count": 1,
	"transaction_status": status
	});


	///Neither was accepted when I set with parameters="username=myname"+"&password=mypass" as the server may not accept that
	 xmlhttp.send(collection);
}
/**************************************************** hitting API***************************************************************/
function hitpayzippy(parameters)
{
	var xmlhttp=new XMLHttpRequest();
	//console.log("hey ther");
	console.log(parameters);
	var URLBase="http://pg-stage8.nm.flipkart.com:13001/payment/api/charging/v1?";
	var url=URLBase+"merchant_id="+parameters.merchant_id+"&currency="+parameters.currency+"&transaction_type="+parameters.transaction_type+"&transaction_amount="+parameters.transaction_amount+"&payment_method="+parameters.payment_method+"&ui_mode="+parameters.ui_mode+"&buyer_email_address="+parameters.buyer_email_address+"&hash_method="+parameters.hash_method+"&merchant_key_id="+parameters.merchant_key_id+"&transaction_amount="+seller_id+"&payment_method="+seller_email_address+"&ui_mode="+transaction_amount+"&hash="+parameters.hash+"&merchant_transaction_id="+parameters.merchant_transaction_id+"&parameters.callback_url=http://127.0.0.1:5000/pay";
	xmlhttp.open("GET",url,false);
	xmlhttp.setRequestHeader("Content-type", "application/json");
	xmlhttp.send();
	document.write(xmlhttp.responseText);
	//console.log(xmlhttp.responseText);
	// var returned=JSON.parse(xmlhttp.responseText);
	// parameters.merchant_transaction_id=returned.transaction_id;
	// parameters.hash=returned.hash;
}


