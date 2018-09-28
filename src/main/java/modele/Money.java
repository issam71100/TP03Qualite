package modele;

import java.security.InvalidParameterException;

public class Money {

	private int amount;
	private String currency;
	private Convertion convertion;
	
	public Money(int amount, String currency) throws InvalidParameterException {

	  if (amount < 0) {
	    throw new InvalidParameterException();
    }

		this.amount = amount;
		try {
			this.setCurrency(currency);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (currency.equals(this.currency)) {
      if ((	this.amount += amount) < 0) {
        throw new InvalidParameterException();
      }
			this.amount += amount;
		}
		else {
			if(currency.equals("EUR")) {
        if ((this.amount += amount* 1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount += amount* 1.29;
			}
			else if(currency.equals("USD")) {
        if ((this.amount += amount*1/1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount += amount*1/1.29;
			}
		}
	}

	public void add(Money money) {
		if(money.getCurrency().equals(this.currency)) {
			this.amount += money.getAmount();
		}
		else {
			if(money.getCurrency().equals("EUR")) {
        if ((this.amount += money.getAmount()*1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount += money.getAmount()*1.29;
			}
			else if(money.getCurrency().equals("USD")) {
        if ((this.amount += money.getAmount()*1/1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount += money.getAmount()*1/1.29;
			}
		}
	}

	/* Methodes Sub */

	public void sub(int amount, String currency) throws InvalidParameterException{
		if (currency.equals(this.currency)) {
      if ((this.amount -= amount) < 0) {
        throw new InvalidParameterException();
      }
			this.amount -= amount;
		}
		else {
			if(currency.equals("EUR")) {
        if ((this.amount -= amount*1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount -= amount*1.29;
			}
			else if(currency.equals("USD")) {
        if ((this.amount -= amount*1/1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount -= amount*1/1.29;
			}
		}
	}

	public void sub(Money money) throws InvalidParameterException {
		if (money.getCurrency().equals(this.currency)) {
      if ((this.amount -= money.getAmount()) < 0) {
        throw new InvalidParameterException();
      }
			this.amount -= money.getAmount();
		}
		else {
			if(money.getCurrency().equals("EUR")) {
        if ((this.amount -= money.getAmount()*1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount -= money.getAmount()*1.29;
			}
			else if(money.getCurrency().equals("USD")) {
        if ((this.amount -= money.getAmount()*1/1.29) < 0) {
          throw new InvalidParameterException();
        }
				this.amount -= money.getAmount()*1/1.29;
			}
		}
	}
}
