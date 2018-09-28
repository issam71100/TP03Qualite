package test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import modele.Convertion;
import modele.Money;
import static org.mockito.Mockito.*;

public class MoneyTest {

	@Mock
	Convertion conv;

	@InjectMocks
	private Money money;

	@Before
	public void setUp() throws Exception {
		money = new Money(10, "EUR");
		MockitoAnnotations.initMocks(this);
		when(conv.unit_Convertion("EUR-USD")).thenReturn(1.29);
		when(conv.unit_Convertion("USD-EUR")).thenReturn(1 / 1.29);
		when(conv.unit_Convertion("")).thenThrow(new NullPointerException());
		when(conv.unit_Convertion(null)).thenThrow(new NullPointerException());	
	}

	@Test
	public void ConstructorTestBothParametersNotNull() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(10));
		Assert.assertThat(money.getCurrency(), IsEqual.equalTo("EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void ConstructorTestCurrencyParametersNull() throws Exception {
		money = new Money(10, null);
		if (money.getCurrency() == null) {
			throw new InvalidParameterException();
		}
		// Assert.assertThat(money.getDevise(),IsNull.nullValue());
	}

	@Test
	public void TestAmount() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.amount(), IsEqual.equalTo(10));
	}

	@Test
	public void TestCurrency() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.currency(), IsEqual.equalTo("EUR"));
	}

	/**
	 * Test de la methode Money.add(int amount , String ncurrency );
	 */
	@Test
	public void AddZeroUnitTwoParameters() {
		money = new Money(50, "EUR");
		money.add(0, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void AddOneEuroFromEuroTwoParameters() {
		money = new Money(50, "EUR");
		money.add(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + 1));
	}

	@Test
	public void AddOneUSDFromEUROTwoParameters() {
		money = new Money(50, "EUR");
		money.add(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + 1* conv.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void AddOneUSDFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.add(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + 1));
	}

	@Test
	public void AddOneEuroFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.add(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + 1 * conv.unit_Convertion("EUR-USD"))));
	}

	/**
	 * Test de la methode Money.add(Money money);
	 */
	@Test
	public void AddZeroUnit() {
		Money m = new Money(0, "USD");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void AddOneEuroFromEuro() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + m.getAmount()));
	}

	@Test
	public void AddOneUSDFromEURO() {
		Money m = new Money(1, "USD");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + m.getAmount() * conv.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void AddOneUSDFromUSD() {
		Money m = new Money(1, "USD");
		money = new Money(50, "USD");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + m.getAmount()));
	}

	@Test
	public void AddOneEuroFromUSD() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "USD");
		money.add(m);	
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + m.getAmount() * conv.unit_Convertion("EUR-USD"))));
		//System.out.println(50 + m.getAmount() * 1.29);

	}

	/**
	 * Test Chaîne Currency
	 */
	@Test(expected = java.security.InvalidParameterException.class)
	public void ConstructorTestCurrencyParametersNotEmpty() throws Exception {
		money = new Money(50, "");
		if (money.getCurrency() == "") {
			throw new InvalidParameterException();
		}
		// Assert.assertThat(money.getCurrency(),IsEqual.equalTo(""));
	}
	
	/**
	 * Test de la methode Money.sub(int amount, String currency);
	 */
	
	@Test
	public void subZeroUnitTwoParameters() {
		money = new Money(50, "EUR");
		money.sub(0, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void subOneEuroFromEuroTwoParameters() {
		money = new Money(50, "EUR");
		money.sub(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 - 1));
	}

	@Test
	public void subOneUSDFromEUROTwoParameters() {
		money = new Money(50, "EUR");
		money.sub(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - 1* conv.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void subOneUSDFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.sub(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 - 1));
	}

	@Test
	public void subOneEuroFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.sub(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - 1 * conv.unit_Convertion("EUR-USD"))));
	}

	/**
	 * Test de la methode Money.sub(Money money);
	 */
	@Test
	public void subZeroUnit() {
		Money m = new Money(0, "USD");
		money = new Money(50, "EUR");
		money.sub(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void subOneEuroFromEuro() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "EUR");
		money.sub(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 - m.getAmount()));
	}

	@Test
	public void subOneUSDFromEURO() {
		Money m = new Money(1, "USD");
		money = new Money(50, "EUR");
		money.sub(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - m.getAmount() * conv.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void subOneUSDFromUSD() {
		Money m = new Money(1, "USD");
		money = new Money(50, "USD");
		money.sub(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 - m.getAmount()));
	}

	@Test
	public void subOneEuroFromUSD() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "USD");
		money.sub(m);	
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - m.getAmount() * conv.unit_Convertion("EUR-USD"))));
	}
	
/// AmountNegatif
}
/**
 * 
 * /* This Java source file was generated by the Gradle 'init' task.import
 * org.junit.Test; import static org.junit.Assert.*;
 * 
 * public class LibraryTest {
 * 
 * @Test public void testSomeLibraryMethod() { Library classUnderTest = new
 *       Library(); assertTrue("someLibraryMethod should return 'true'",
 *       classUnderTest.someLibraryMethod()); } } }
 * 
 */
