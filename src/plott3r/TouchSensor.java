package plott3r;

import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteEV3;

public final class TouchSensor extends Sensor {

	public TouchSensor(RemoteEV3 ev3, String port, String name, String mode) throws RemoteException {
		super(ev3, port, name, mode);
	}

	@Override
	protected boolean isAktiv(float wert) {
		if (wert == 1) {
			return true;
		}
		return false;
	}

}
