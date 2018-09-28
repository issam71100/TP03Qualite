package test;

import modele.Convertion;
import modele.Money;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NegativeMoneyTest {
	@Mock
	private Convertion conv;

	@InjectMocks
	private Money money;

	@Before
	public void setUp() throws Exception {
		money = new Money(10, "EUR");
		MockitoAnnotations.initMocks(this);
		Mockito.when(conv.unit_Convertion("EUR-USD")).thenReturn(1.29);
		Mockito.when(conv.unit_Convertion("USD-EUR")).thenReturn(1 / 1.29);
		Mockito.when(conv.unit_Convertion("")).thenThrow(new NullPointerException());
		Mockito.when(conv.unit_Convertion(null)).thenThrow(new NullPointerException());
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void ConstructorAmountInferiorTo0() {
		money = new Money(-1, "EUR");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void setMoney() {
		money = new Money(0, "EUR");
		money.setAmount(-1);
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addEuroFromEuroParameters() {
		money = new Money(10, "EUR");
		money.add(new Money(-11, "EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addEuroFromUSDParameters() {
		money = new Money(10, "USD");
		money.add(new Money(-101, "EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addUSDFromEuroParameters() {
		money = new Money(10, "EUR");
		money.add(new Money(-101, "USD"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addEuroFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.add(-11, "EUR");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addUSDFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.add(-150, "USD");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void addEuroFromUSDTwoParameters() {
		money = new Money(10, "USD");
		money.add(-150, "EUR");
	}

///////////////////////////////////////////////////////////////////
	@Test(expected = java.security.InvalidParameterException.class)
	public void subEuroFromEuroParameters() {
		money = new Money(10, "EUR");
		money.sub(new Money(-100, "EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subEuroFromUSDParameters() {
		money = new Money(10, "USD");
		money.sub(new Money(-101, "EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subUSDFromEuroParameters() {
		money = new Money(10, "EUR");
		money.sub(new Money(-101, "USD"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subEuroFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.sub(-11, "EUR");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subUSDFromEuroTwoParameters() {
		money = new Money(10, "EUR");
		money.sub(-150, "USD");
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void subEuroFromUSDTwoParameters() {
		money = new Money(10, "USD");
		money.sub(-150, "EUR");
	}
}
