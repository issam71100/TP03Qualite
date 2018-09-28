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
  public void AddEuroFromEuroParameters() {
    money = new Money(10,"EUR");
    money.add(new Money(-11,"EUR"));
  }

  @Test(expected = java.security.InvalidParameterException.class)
  public void AddEuroFromUSDParameters() {
    money = new Money(10,"USD");
    money.add(new Money(-101,"EUR"));
  }

  @Test(expected = java.security.InvalidParameterException.class)
  public void AddUSDFromEuroParameters() {
    money = new Money(10,"EUR");
    money.add(new Money(-101,"USD"));
  }


  @Test(expected = java.security.InvalidParameterException.class)
  public void AddEuroFromEuroTwoParameters() {
    money = new Money(10,"EUR");
    money.add(-11,"EUR");
}
  @Test(expected = java.security.InvalidParameterException.class)
  public void AddUSDFromEuroTwoParameters() {
    money = new Money(10,"EUR");
    money.add(-150,"USD");
}

  @Test(expected = java.security.InvalidParameterException.class)
  public void AddEuroFromUSDTwoParameters() {
    money = new Money(10,"USD");
    money.add(-150,"EUR");
}
///////////////////////////////////////////////////////////////////
@Test(expected = java.security.InvalidParameterException.class)
public void SubEuroFromEuroParameters() {
  money = new Money(10,"EUR");
  money.sub(new Money(100,"EUR"));
}

  @Test(expected = java.security.InvalidParameterException.class)
  public void SubEuroFromUSDParameters() {
    money = new Money(10,"USD");
    money.sub(new Money(101,"EUR"));
  }

  @Test(expected = java.security.InvalidParameterException.class)
  public void SubUSDFromEuroParameters() {
    money = new Money(10,"EUR");
    money.sub(new Money(101,"USD"));
  }


  @Test(expected = java.security.InvalidParameterException.class)
  public void SubEuroFromEuroTwoParameters() {
    money = new Money(10,"EUR");
    money.sub(11,"EUR");
  }
  @Test(expected = java.security.InvalidParameterException.class)
  public void SubUSDFromEuroTwoParameters() {
    money = new Money(10,"EUR");
    money.sub(150,"USD");
  }

  @Test(expected = java.security.InvalidParameterException.class)
  public void SubEuroFromUSDTwoParameters() {
    money = new Money(10,"USD");
    money.sub(150,"EUR");
  }
}

