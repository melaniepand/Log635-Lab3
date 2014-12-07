import java.text.DecimalFormat;
import java.util.Random;

public class Utils {

	public static Double defimalFormat(Double val) {
		Double resl = 0.0;
		DecimalFormat df = new DecimalFormat("########.000000000");
		String str = df.format(val);
		resl = Double.parseDouble(str.replace(',', '.'));

		// System.out.println("Resl:" + resl);
		return resl;

	}

	public static Double transform(int q, Double d) {
		return q / d;
	}

	public static Double transform(Double q, Double d) {
		return q / d;
	}

	public static Double normalValue(Double q) {
		DecimalFormat df = new DecimalFormat("########.00");
		String str = df.format(q * 10.0 + RandomClamped());
		return Double.parseDouble(str.replace(',', '.'));
	}

	public static double RandomClamped() {
		Random random = new Random();
		return random.nextDouble() * 2 - 1;
	}

}
