package plott3r;

import java.rmi.RemoteException;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

public class DualPositionAchse extends Achse {

	private boolean aktiv;

	public DualPositionAchse(RemoteEV3 ev3, Sensor sensor, String motorPort, char motorType,
			Einbaurichtung einbaurichtung, Reifen letzteEinheit, IUebersetzung... uebersetzungsEinheiten) {
		super(ev3, sensor, motorPort, motorType, einbaurichtung, letzteEinheit, uebersetzungsEinheiten);
	}

	public void aktiviere() throws RemoteException {
		if (this.aktiv)
			return;
		this.aktiv = true;
		this.getMotor().rotate(-90);
		Delay.msDelay(500);
	}

	public void deaktiviere() throws RemoteException {
		if (!this.aktiv)
			return;
		this.aktiv = false;
		this.getMotor().rotate(90);
		Delay.msDelay(500);
	}

	public boolean isAktiv() {
		return aktiv;
	}

	public void rotate(int grad) throws RemoteException {
		this.getMotor().rotate(grad);
	}
}
