package interview.rc;

public class QuarterSalesItem {
	private int quarter;
	private double value;

	public QuarterSalesItem() {
	}

	public QuarterSalesItem(int quarter, double value) {
		this.quarter = quarter;
		this.value = value;
	}

	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}


}
