public class Data {

	private double fixedAcidity;
	private double volatileAcidity;
	private double citricAcid;
	private double residualSugar;
	private double chlorides;
	private double freeSulfurDioxide;
	private double totalSulfurDioxide;
	private double density;
	private double pH;
	private double sulphates;
	private double alcohol;
	private int quality;

	public Data(double pFixedAcidity, double pVolatileAcidity,
			double pCitricAcid, double pResidualSugar, double pChlorides,
			double pFreeSulfurDioxide, double pTotalSulfurDioxide,
			double pDensity, double pPH, double pSulphates, double pAlcohol,
			int pQuality) {
		fixedAcidity = pFixedAcidity;
		volatileAcidity = pVolatileAcidity;
		citricAcid = pCitricAcid;
		residualSugar = pResidualSugar;
		chlorides = pChlorides;
		freeSulfurDioxide = pFreeSulfurDioxide;
		totalSulfurDioxide = pTotalSulfurDioxide;
		density = pDensity;
		pH = pPH;
		sulphates = pSulphates;
		alcohol = pAlcohol;
		quality = pQuality;

	}

	public double getFixedAcidity() {
		return fixedAcidity;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public void setFixedAcidity(double fixedAcidity) {
		this.fixedAcidity = fixedAcidity;
	}

	public double getVolatileAcidity() {
		return volatileAcidity;
	}

	public void setVolatileAcidity(double volatileAcidity) {
		this.volatileAcidity = volatileAcidity;
	}

	public double getCitricAcid() {
		return citricAcid;
	}

	public void setCitricAcid(double citricAcid) {
		this.citricAcid = citricAcid;
	}

	public double getResidualSugar() {
		return residualSugar;
	}

	public void setResidualSugar(double residualSugar) {
		this.residualSugar = residualSugar;
	}

	public double getChlorides() {
		return chlorides;
	}

	public void setChlorides(double chlorides) {
		this.chlorides = chlorides;
	}

	public double getFreeSulfurDioxide() {
		return freeSulfurDioxide;
	}

	public void setFreeSulfurDioxide(double freeSulfurDioxide) {
		this.freeSulfurDioxide = freeSulfurDioxide;
	}

	public double getTotalSulfurDioxide() {
		return totalSulfurDioxide;
	}

	public void setTotalSulfurDioxide(double totalSulfurDioxide) {
		this.totalSulfurDioxide = totalSulfurDioxide;
	}

	public double getpH() {
		return pH;
	}

	public void setpH(double pH) {
		this.pH = pH;
	}

	public double getSulphates() {
		return sulphates;
	}

	public void setSulphates(double sulphates) {
		this.sulphates = sulphates;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String printData() {
		String data = String.format("Data: \n" + "\nfixedAcidity: %f"
				+ "volatileAcidity: %f" + "\ncitricAcid: %f"
				+ "\nresidualSugar: %f" + "\nchlorides: %f"
				+ "\nfreeSulfurDioxide: %f" + "\ntotalSulfurDioxide: %f"
				+ "\ndensity: %f" + "pH: %f" + "\nsulphates: %f"
				+ "\nalcohol: %f" + "\nquality: %d", fixedAcidity,
				volatileAcidity, citricAcid, residualSugar, chlorides,
				freeSulfurDioxide, totalSulfurDioxide, density, pH, sulphates,
				alcohol, quality);
		return data;
	}
}
