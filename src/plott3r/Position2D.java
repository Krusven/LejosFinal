package plott3r;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Position2D {

	private double x, y;

	public Position2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getXRounded() == ((Position2D) obj).getXRounded()
				&& this.getYRounded() == ((Position2D) obj).getYRounded())
			return true;

		return false;
	}

	public double getX() {
		return x;
	}

	private double getXRounded() {
		return round(this.getX());
	}

	public double getY() {
		return y;
	}

	private double getYRounded() {
		return round(this.getY());
	}

	private double round(double d) {
		BigDecimal bd = BigDecimal.valueOf(d);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return this.getXRounded() + " : " + this.getYRounded();
	}
}
