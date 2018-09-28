package modele;

import java.security.InvalidParameterException;

public class Money {

	private int amount;
	private String currency;
	private Convertion convertion;

	public Money(int amount, String currency) {
		// TODO Auto-generated constructor stub
		this.amount = amount;
		try {
			this.setCurrency(currency);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) throws Exception {
		if (currency == "EUR" || currency == "USD") {
			this.currency = currency;
		} else {
			throw new InvalidParameterException();
		}
	}

	public int amount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	public String currency() {
		// TODO Auto-generated method stub
		return this.currency;
	}

	public void add(int amount, String currency) {
		// TODO Auto-generated method stub
		if (currency.equals(this.currency)) {
			this.amount += amount;
		} else {
			if (currency.equals("EUR")) {
				this.amount += amount * 1.29;
			} else if (currency.equals("USD")) {
				this.amount += amount * 1 / 1.29;
			}
		}
	}

	public void add(Money money) {
		// TODO Auto-generated method stub
		if (money.getCurrency().equals(this.currency)) {
			this.amount += money.getAmount();
		} else {
			if (money.getCurrency().equals("EUR")) {
				this.amount += money.getAmount() * 1.29;
			} else if (money.getCurrency().equals("USD")) {
				this.amount += money.getAmount() * 1 / 1.29;
			}
		}
	}

	/* Methodes Sub */

	public void sub(int amount, String currency) {
		// TODO Auto-generated method stub
		if (currency.equals(this.currency)) {
			this.amount -= amount;
		} else {
			if (currency.equals("EUR")) {
				this.amount -= amount * 1.29;
			} else if (currency.equals("USD")) {
				this.amount -= amount * 1 / 1.29;
			}
		}
	}

	public void sub(Money money) {
		// TODO Auto-generated method stub
		if (money.getCurrency().equals(this.currency)) {
			this.amount -= money.getAmount();
		} else {
			if (money.getCurrency().equals("EUR")) {
				this.amount -= money.getAmount() * 1.29;
			} else if (money.getCurrency().equals("USD")) {
				this.amount -= money.getAmount() * 1 / 1.29;
			}
		}
	}
}

//public Money( int amount , String currency );

//public int amount ( );

//public String currency ( );

//public void add (Money m);

//public void add (int namount , String ncurrency );