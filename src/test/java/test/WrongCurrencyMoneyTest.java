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
	public void setUp() throws Exception {
		money = new Money(10, "EUR");
		MockitoAnnotations.initMocks(this);
		Mockito.when(conv.unit_Convertion("EUR-USD")).thenReturn(1.29);
		Mockito.when(conv.unit_Convertion("USD-EUR")).thenReturn(1 / 1.29);
		Mockito.when(conv.unit_Convertion("")).thenThrow(new NullPointerException());
		Mockito.when(conv.unit_Convertion(null)).thenThrow(new NullPointerException());
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void ConstructorTestCurrencyInvalidParameter(){
		money = new Money(10, "ERI");
	}
}
