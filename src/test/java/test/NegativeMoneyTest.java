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

  @Test
  public void ConstructorAmountInferiorTo0() {
    money = new Money(-1, "EUR");
    Assert.assertThat(money.getAmount(),lessThan(0));
  }
  @Test
  public void AddMoneyOneParameters() {


  }
  @Test
  public void AddMoneyTwoParameters() {


  }

  @Test
  public void SubMoneyOneParameters() {


  }

  @Test
  public void SubMoneyTwoParameters() {


  }

}

