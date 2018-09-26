package modele;

public class Convertion {
	public double unit_Convertion(String unit) {
		if (unit == "EUR-USD") {
			return 1.29;
		} else {
			return 1 / 1.29;
		}

	}
}
