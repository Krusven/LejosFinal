package plott3r;

import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteEV3;

public final class IRSensor extends Sensor {

	public IRSensor(RemoteEV3 ev3, String port, String name, String mode) throws RemoteException {
		super(ev3, port, name, mode);
	}

	@Override
	protected boolean isAktiv(float wert) {
		return wert > 0;
	}

	@Override
	public void kalibriere(float wert) {
	}

}
