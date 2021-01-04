package plott3r;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lejos.remote.ev3.RemoteEV3;

public abstract class Achse {

	protected Reifen antriebsEinheit;

	private Motor motor;

	private Sensor sensor;

	private List<IUebersetzung> uebersetzungsEinheiten = new ArrayList<>();

	public Achse(RemoteEV3 ev3, Sensor sensor, String motorPort, char motorType, Einbaurichtung einbaurichtung,
			Reifen antriebsEinheit, IUebersetzung... uebersetzungsEinheiten) {
		this.motor = new Motor(ev3, motorPort, motorType, einbaurichtung);
		this.sensor = sensor;
		if (uebersetzungsEinheiten != null)
			this.uebersetzungsEinheiten = Arrays.asList(uebersetzungsEinheiten);
		this.antriebsEinheit = antriebsEinheit;
	}

	protected int berechneGradAusMm(double mm) {
		double umdrehungenRad = mm / this.antriebsEinheit.getUmfang();
		double umdrehungenMotor = umdrehungenRad * this.getUebersetzungsverhaeltnis();
		double gradMotor = umdrehungenMotor * 360;
		return (int) Math.round(gradMotor);
	}

	protected Motor getMotor() {
		return this.motor;
	}

	public Sensor getSensor() {
		return sensor;
	}

	protected double getUebersetzungsverhaeltnis() {
		if (uebersetzungsEinheiten.isEmpty())
			return 1;
		return uebersetzungsEinheiten.get(0).getUebersetzungsverhaeltnis();
	}

	public boolean isSensorAktiv() throws RemoteException {
		if (sensor == null)
			return true;
		return sensor.isAktiv();
	}

	public int setSpeed(double mmSecond) throws RemoteException {
		int gradMotor = this.berechneGradAusMm(mmSecond);
		this.getMotor().setSpeed(gradMotor);
		return gradMotor;
	}

	public void stop() throws RemoteException {
		this.motor.stop();
	}

	public void close() throws Throwable {
		if (this.motor != null)
			this.motor.close();
		if (this.sensor != null)
			this.sensor.close();
	}

}
