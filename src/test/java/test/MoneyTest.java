package test;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import modele.Convertion;
import modele.Money;
import static org.mockito.Mockito.*;

public class MoneyTest {
	@Mock
	Convertion convertion;
	@InjectMocks
	private Money money;

	@Before
	public void setUp() {
		money = new Money(10, "EUR");
		MockitoAnnotations.initMocks(this);

		when(convertion.unit_Convertion("EUR-USD")).thenReturn(1.29);
		when(convertion.unit_Convertion("USD-EUR")).thenReturn(1 / 1.29);
		when(convertion.unit_Convertion("")).thenThrow(new IllegalArgumentException());
		when(convertion.unit_Convertion(" ")).thenThrow(new IllegalArgumentException());
		when(convertion.unit_Convertion(null)).thenThrow(new IllegalArgumentException());
	}

	@Test
	public void constructorTestBothParametersNotNull() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(10));
		Assert.assertThat(money.getCurrency(), IsEqual.equalTo("EUR"));
	}

	@Test(expected = java.security.InvalidParameterException.class)
	public void constructorTestCurrencyParametersNull() throws Exception {
		money = new Money(10, null);
	}

	@Test
	public void testAmount() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.amount(), IsEqual.equalTo(10));
	}

	@Test
	public void testCurrency() {
		money = new Money(10, "EUR");
		Assert.assertThat(money.currency(), IsEqual.equalTo("EUR"));
	}

	/**
	 * Test de la methode Money.add(int amount , String ncurrency );
	 */
	@Test
	public void addZeroUnitTwoParameters() {
		money = new Money(50, "EUR");
		money.add(0, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void addOneEuroFromEuroTwoParameters() {
		money = new Money(50, "EUR");
		money.add(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + 1));
	}

	@Test
	public void addOneUSDFromEUROTwoParameters() {
		money = new Money(50, "EUR");
		money.add(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + 1 * convertion.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void addOneUSDFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.add(1, "USD");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + 1));
	}

	@Test
	public void addOneEuroFromUSDTwoParameters() {
		money = new Money(50, "USD");
		money.add(1, "EUR");
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 + 1 * convertion.unit_Convertion("EUR-USD"))));
	}

	/**
	 * Test de la methode Money.add(Money money);
	 */
	@Test
	public void addZeroUnit() {
		Money m = new Money(0, "EUR");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50));
	}

	@Test
	public void addOneEuroFromEuro() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + m.getAmount()));
	}

	@Test
	public void addOneUSDFromEURO() {
		Money m = new Money(1, "USD");
		money = new Money(50, "EUR");
		money.add(m);
		Assert.assertThat(money.getAmount(),
				IsEqual.equalTo((int) (50 + m.getAmount() * convertion.unit_Convertion("USD-EUR"))));
	}

	@Test
	public void addOneUSDFromUSD() {
		Money m = new Money(1, "USD");
		money = new Money(50, "USD");
		money.add(m);
		Assert.assertThat(money.getAmount(), IsEqual.equalTo(50 + m.getAmount()));
	}

	@Test
	public void addOneEuroFromUSD() {
		Money m = new Money(1, "EUR");
		money = new Money(50, "USD");
		money.add(m);
		Assert.assertThat(money.getAmount(),
				IsEqual.equalTo((int) (50 + m.getAmount() * convertion.unit_Convertion("EUR-USD"))));

	}

	/**
	 * Test Chaîne Currency
	 */
	@Test(expected = java.security.InvalidParameterException.class)
	public void constructorTestCurrencyParametersNotEmpty() throws Exception {
		money = new Money(50, "");
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
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - 1 * convertion.unit_Convertion("USD-EUR"))));
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
		Assert.assertThat(money.getAmount(), IsEqual.equalTo((int) (50 - 1 * convertion.unit_Convertion("EUR-USD"))));
	}

	/**
	 * Test de la methode Money.sub(Money money);
	 */
	@Test
	public void subZeroUnit() {
		Money m = new Money(0, "EUR");
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
		Assert.assertThat(money.getAmount(),
				IsEqual.equalTo((int) (50 - m.getAmount() * convertion.unit_Convertion("USD-EUR"))));
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
		Assert.assertThat(money.getAmount(),
				IsEqual.equalTo((int) (50 - m.getAmount() * convertion.unit_Convertion("EUR-USD"))));
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
