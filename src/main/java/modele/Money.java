package modele;
public class Money {

	private int amount;
	private String currency;
	private Convertion convertion;
	
	public Money(int amount, String currency) {
		// TODO Auto-generated constructor stub
		this.amount = amount;
		this.currency= currency;
		convertion= new Convertion();
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

	public void setDevise(String devise) {
		this.currency = devise;
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
		if(currency.equals(this.currency)) {
			this.amount += amount;
		}
		else {
			if(currency.equals("EUR")) {
				this.amount += amount*convertion.unit_Convertion("EUR-USD");
			}
			else if(currency.equals("USD")) {
				this.amount += amount*1/1.29;
			}
		}
	}

	public void add(Money money) {
		// TODO Auto-generated method stub
	}

}

//public Money( int amount , String currency );

//public int amount ( );

//public String currency ( );

//public void add (Money m);

//public void add (int namount , String ncurrency );