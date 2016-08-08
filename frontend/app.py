
from flask import Flask, render_template, request

app = Flask(__name__)
app.debug = True

@app.route("/")
@app.route("/pay")
def index():
	# return str()
	if request.method == 'POST':
		print(request)
		return redirect(url_for('/f2'))
	seller_name = request.args.get('seller_name')
	email_id = request.args.get('email_id')
	amount = request.args.get('amount')
	return render_template("pay.html", email_id=email_id, seller_name=seller_name, amount=amount)


@app.route("/f2")
def f2():
	return render_template("f2.html")

if __name__ == '__main__':
	app.run()

# pay.html?email_id=gulabapparel@gmail.com&seller_name=swaranjaliaa&amount=1000


