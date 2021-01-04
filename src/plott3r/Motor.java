package plott3r;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

public class Motor {

	private Einbaurichtung einbaurichtung;

	private RMIRegulatedMotor regulatedMotor;

	public Motor(RemoteEV3 ev3, String port, char type, Einbaurichtung einbaurichtung) {
		regulatedMotor = ev3.createRegulatedMotor(port, type);
		this.einbaurichtung = einbaurichtung;
	}

	public void backward() throws RemoteException {
		if (einbaurichtung == Einbaurichtung.UMGEKEHRT) {
			this.regulatedMotor.forward();
			return;
		}
		this.regulatedMotor.backward();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		regulatedMotor.close();
	}

	public void close() throws RemoteException {
		this.regulatedMotor.close();
	}

	public void forward() throws RemoteException {
		if (einbaurichtung == Einbaurichtung.UMGEKEHRT) {
			this.regulatedMotor.backward();
			return;
		}
		this.regulatedMotor.forward();
	}

	protected Einbaurichtung getEinbaurichtung() {
		return einbaurichtung;
	}

	public boolean isMoving() throws RemoteException {
		return this.regulatedMotor.isMoving();
	}

	public int getTachoCount() throws RemoteException {
		return this.regulatedMotor.getTachoCount();
	}

	public void resetTachoCount() throws RemoteException {
		this.regulatedMotor.resetTachoCount();
	}

	public void rotate(int grad) throws RemoteException {
		this.regulatedMotor.rotate(grad, false);
	}

	public void rotateTo(int limitAngle) throws RemoteException {
		this.regulatedMotor.rotateTo(limitAngle);

	}

	public void setSpeed(int speed) throws RemoteException {
		this.regulatedMotor.setSpeed(speed);
	}

	public void stop() throws RemoteException {
		regulatedMotor.stop(false);
	}

	public void waitComplete() throws RemoteException {
		this.regulatedMotor.waitComplete();
	}

}
