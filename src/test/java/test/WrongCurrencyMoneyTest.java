package test;

import modele.Convertion;
import modele.Money;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class WrongCurrencyMoneyTest {

	@Mock
	private Convertion conv;

	@InjectMocks
	private Money money;

	@Before
	public void setUp() {
		money = new Money(10, "EUR");
		MockitoAnnotations.initMocks(this);
		Mockito.when(conv.unit_Convertion("EUR-USD")).thenReturn(1.29);
		Mockito.when(conv.unit_Convertion("USD-EUR")).thenReturn(1 / 1.29);
		Mockito.when(conv.unit_Convertion("")).thenThrow(new NullPointerException());
		Mockito.when(conv.unit_Convertion(null)).thenThrow(new NullPointerException());
	}

	///////////////////////////////////////////////////////////////////////////////////////
	@Test(expected = java.security.InvalidParameterException.class)
	public void ConstructorTestCurrencyInvalidParameter() {
		money = new Money(10, "ERI");
	}
	
	@Test(expected = java.security.InvalidParameterException.class)
	public void setCurrency() {
		money = new Money(10, "EUR");
		money.setCurrency("AAA");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addInvalidCurrencyFromEuro() {
		money = new Money(10, "EUR");
		money.add(new Money(1, "AAA"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addInvalidCurrencyFromUSD() {
		money = new Money(10, "USD");
		money.add(new Money(1, "AAA"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addInvalidCurrencyFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.add(1, "AAA");
	}


	@Test(expected = java.security.InvalidParameterException.class)
	public void addInvalidCurrencyFromUSDTwoParameters() {
		money = new Money(10, "USD");
		money.add(1, "AAA");
	}

	///////////////////////////////////////////////////////////////////
	@Test(expected = java.security.InvalidParameterException.class)
	public void subInvalidCurrencyFromEuro() {
		money = new Money(10, "EUR");
		money.sub(new Money(1, "AAA"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subInvalidCurrencyFromUSD() {
		money = new Money(10, "USD");
		money.sub(new Money(1, "AAA"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subInvalidCurrencyFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.sub(1, "AAA");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subInvalidCurrencyFromUSDTwoParameters() {
		money = new Money(10, "USD");
		money.sub(1, "AAA");
	}
}
