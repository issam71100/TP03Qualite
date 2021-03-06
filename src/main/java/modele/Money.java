package modele;

import java.security.InvalidParameterException;
public class Money {

	private int amount;
	private String currency;
	private Convertion convertion;

	public Money(int amount, String currency) throws InvalidParameterException {
		System.out.println(convertion);
		if (amount < 0) {
			throw new InvalidParameterException();
		}
		this.amount = amount;
		if (currency == "EUR" || currency == "USD") {
			this.currency = currency;
		} else
			throw new InvalidParameterException();

	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) throws InvalidParameterException {
		if (amount < 0) {
			throw new InvalidParameterException();
		}
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) throws InvalidParameterException {
		if (currency.equals("EUR") || currency.equals("USD")) {
			this.currency = currency;
		} else {
			throw new InvalidParameterException();
		}
	}

	public int amount() {
		return this.amount;
	}

	public String currency() {
		return this.currency;
	}

	public void add(int amount, String currency) throws InvalidParameterException {
		if (currency.equals("EUR") || currency.equals("USD")) {
			if (currency.equals(this.currency)) {
				if (amount < 0) {
					throw new InvalidParameterException();
				}

				this.amount += amount;
			} else {
				if (currency.equals("EUR")) {
					if (amount< 0) {
						throw new InvalidParameterException();
					}
					this.amount += amount * 1.29;
				} else if (currency.equals("USD")) {
					if (amount < 0) {
						throw new InvalidParameterException();
					}
					this.amount += amount * convertion.unit_Convertion("EUR-USD");
				}
			}
		} else
			throw new InvalidParameterException();

	}

	public void add(Money money) throws InvalidParameterException {
		if (money.getCurrency().equals("EUR") || money.getCurrency().equals("USD")) {
			if (money.getCurrency().equals(this.currency)) {
				if ((money.getAmount()) < 0) {
					throw new InvalidParameterException();
				}
				this.amount += money.getAmount();
			} else {
				if (money.getCurrency().equals("EUR")) {
					if ((money.getAmount()) < 0) {
						throw new InvalidParameterException();
					}
					this.amount += money.getAmount() * 1.29;
				} else if (money.getCurrency().equals("USD")) {
					if ((money.getAmount()) < 0) {
						throw new InvalidParameterException();
					}
					this.amount += money.getAmount() *convertion.unit_Convertion("EUR-USD");
				}
			}
		} else
			throw new InvalidParameterException();

	}

	/* Methodes Sub */

	public void sub(int amount, String currency) throws InvalidParameterException {
		if ((currency.equals("EUR") || currency.equals("USD")) && (this.amount - amount)>=0){
			if (currency.equals(this.currency)) {
				if (amount < 0) {
					throw new InvalidParameterException();
				}
				this.amount -= amount;
			} else {
				if (currency.equals("EUR")) {
					if (amount < 0) {
						throw new InvalidParameterException();
					}
					this.amount -= amount * 1.29;
				} else if (currency.equals("USD")) {
					if ((amount) < 0) {
						throw new InvalidParameterException();
					}
					this.amount -= amount * convertion.unit_Convertion("EUR-USD");
				}
			}
		} else
			throw new InvalidParameterException();

	}

	public void sub(Money money) throws InvalidParameterException {
		if ((money.getCurrency().equals("EUR") || money.getCurrency().equals("USD")) && (this.amount - money.getAmount()>=0)) {
			if (money.getCurrency().equals(this.currency)) {
				if (money.getAmount() < 0) {
					throw new InvalidParameterException();
				}
				this.amount -= money.getAmount();

			} else {
				if (money.getCurrency().equals("EUR")) {
					if (money.getAmount() < 0) {
						throw new InvalidParameterException();
					}
					this.amount -= money.getAmount() * 1.29;
				} else if (money.getCurrency().equals("USD")) {
					if (money.getAmount() < 0) {
						throw new InvalidParameterException();
					}
					this.amount -= money.getAmount() *convertion.unit_Convertion("EUR-USD");
				}
			}
		} else
			throw new InvalidParameterException();

	}
}
