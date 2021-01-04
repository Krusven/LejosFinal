package plott3r;

import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteEV3;

public class MultiPositionAchse extends Achse {

	public MultiPositionAchse(RemoteEV3 ev3, Sensor sensor, String motorPort, char motorType,
			Einbaurichtung einbaurichtung, Reifen letzteEinheit, IUebersetzung... uebersetzungsEinheiten) {
		super(ev3, sensor, motorPort, motorType, einbaurichtung, letzteEinheit, uebersetzungsEinheiten);
	}

	public void backward() throws RemoteException {
		this.getMotor().backward();
	}

	public void backward(long timeInMillis) throws InterruptedException, RemoteException {
		this.backward();
		Thread.sleep(timeInMillis);
		this.stop();
	}

	public void forward() throws RemoteException {
		this.getMotor().forward();
	}

	public void forward(long timeInMillis) throws InterruptedException, RemoteException {
		this.forward();
		Thread.sleep(timeInMillis);
		this.stop();
	}

	public double getPositionFromTachoCount() throws RemoteException {
		final double gearWheelRatio = this.getUebersetzungsverhaeltnis();
		final double umfang = this.antriebsEinheit.getUmfang();
		final int tachoCount = this.getTachoCount();
		double mm = (tachoCount * umfang) / (gearWheelRatio * 360);
		if (this.getMotor().getEinbaurichtung() == Einbaurichtung.UMGEKEHRT)
			mm = mm * -1;
		return mm;
	}

	public int getTachoCount() throws RemoteException {
		return this.getMotor().getTachoCount();
	}

	public void resetTachoCount() throws RemoteException {
		this.getMotor().resetTachoCount();
	}

	public void rotateMm(double mm) throws RemoteException {
		int gradMotor = this.berechneGradAusMm(mm);
		if (this.getMotor().getEinbaurichtung() == Einbaurichtung.UMGEKEHRT)
			gradMotor *= -1;
		this.getMotor().rotate(gradMotor);
	}

}
