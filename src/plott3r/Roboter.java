package plott3r;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;

import lejos.hardware.Sound;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

public class Roboter {
	//lol
//0,0 und 160, 200 im Koordinatensystem

	private Position3D currentPosition;

	private MultiPositionAchse xAchse = null;
	private MultiPositionAchse yAchse = null;
	public DualPositionAchse zAchse = null;
	public MultiPositionAchse getxAchse() {
		return xAchse;
	}
	public MultiPositionAchse getyAchse() {
		return yAchse;
	}
	public DualPositionAchse getzAchse() {
		return zAchse;
	}
	public Roboter(RemoteEV3 ev3) throws Throwable {
		xAchse = new MultiPositionAchse(ev3, new TouchSensor(ev3, "S1", "EV3TouchSensor", "Touch"), "A", 'L',
				Einbaurichtung.REGULAER, new Reifen(40.0),
				new Zahnradsatz(new Zahnrad(Zahnrad.ANZAHL_ZAEHNE_KLEIN), new Zahnrad(Zahnrad.ANZAHL_ZAEHNE_GROSS)));
		yAchse = new MultiPositionAchse(ev3, new IRSensor(ev3, "S2", "EV3IRSensor", "Distance"), "B", 'L',
				Einbaurichtung.REGULAER, new Reifen(43.2),
				new Zahnradsatz(new Zahnrad(Zahnrad.ANZAHL_ZAEHNE_KLEIN), new Zahnrad(Zahnrad.ANZAHL_ZAEHNE_GROSS)));
		zAchse = new DualPositionAchse(ev3, null, "C", 'M', Einbaurichtung.REGULAER, null, null);

		currentPosition = new Position3D(0, 0, false);

		System.out.println("starting...");
			Sound.beep();
			resetX();
			resetY();
/*try{
			Sound.beep();
			Sound.buzz();
			moveToPosition(new Position3D(160, 0, true), 40);
			moveToPosition(new Position3D(160, 195, true), 40);
			moveToPosition(new Position3D(160, 195, true), 40);
			moveToPosition(new Position3D(0, 0, true), 40);
			zAchse.deaktiviere();


//			System.out.println("moving position...");
//			Position3D pos = new Position3D(150.0, 0.0, false);
//			moveToPosition(pos, 50);

			System.out.println("done...");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			xAchse.close();
			yAchse.close();
			zAchse.close();
		}*/

	}

	private void bereitePapierVor() throws InterruptedException {
	}

	private void entfernePapier() throws InterruptedException, RemoteException {
		zAchse.deaktiviere();
		yAchse.setSpeed(Integer.MAX_VALUE);
		yAchse.backward(2000);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.exit(0);
	}

	public Position3D getCurrentPosition() {
		return this.currentPosition;
	}

	public MultiPositionAchse getXAchse() {
		return this.xAchse;
	}

	public MultiPositionAchse getYAchse() {
		return this.yAchse;
	}

	public void resetX() throws RemoteException {
		zAchse.deaktiviere();
		xAchse.setSpeed(50);
		while (!xAchse.isSensorAktiv())
			xAchse.forward();
		xAchse.stop();
		xAchse.rotateMm(-160);
		this.currentPosition = new Position3D(0, 0, false);
		this.resetTachoCounts();
	}

	public void resetY() throws RemoteException {
		zAchse.deaktiviere();
		yAchse.setSpeed(50);
		while (yAchse.isSensorAktiv())
			yAchse.forward();
		yAchse.stop();
		yAchse.rotateMm(-200);
		this.currentPosition = new Position3D(currentPosition.getX(), 0, false);
		this.resetTachoCounts();
	}

	protected void moveToHomePosition() throws InterruptedException, RemoteException {
		zAchse.deaktiviere();
		xAchse.setSpeed(50);
		while (!xAchse.isSensorAktiv()) {
			xAchse.forward();
		}
		xAchse.stop();
		xAchse.forward();
		Delay.msDelay(200);
		xAchse.stop();
		this.currentPosition = new Position3D(0, 0, false);
		//this.resetTachoCounts();
	}

	public void moveToPosition(Position2D position2D, int mmSec) throws InterruptedException, RemoteException {
		this.moveToPosition(new Position3D(position2D, this.zAchse.isAktiv()), mmSec);
	}

	public void moveToPosition(Position3D position, int mmSec) throws InterruptedException, RemoteException {
		if (position.isZ())
			this.zAchse.aktiviere();
		else
			this.zAchse.deaktiviere();

		double cX = currentPosition.getX(), cY = currentPosition.getY();
		double nX = position.getX(), nY = position.getY();

		double xDiff = (cX - nX) * -1;
		double yDiff = (cY - nY) * -1;

		double factor = xDiff / yDiff;
		double speedX = mmSec;
		double speedY = mmSec / factor;
		speedY = xDiff == 0 ? 100 : speedY;

		final Boolean[] motorState = new Boolean[2];
		for (int i = 0; i < motorState.length; i++)
			motorState[i] = false;
		new AsyncMotor(0, xAchse, xDiff, speedX, new Callback<Integer>() {

			@Override
			public void callFinish(Integer index) {
				motorState[index] = true;
			}
		}).move();
		new AsyncMotor(1, yAchse, yDiff, speedY, new Callback<Integer>() {

			@Override
			public void callFinish(Integer index) {
				motorState[index] = true;
			}
		}).move();
		int couter = 0;
		while (!motorsFinished(motorState) && couter < 100) {
			Thread.sleep(100L);
			couter++;
		}

		xAchse.getMotor().waitComplete();
		yAchse.getMotor().waitComplete();

		this.currentPosition = new Position3D(xAchse.getPositionFromTachoCount(), yAchse.getPositionFromTachoCount(),
				zAchse.isAktiv());

	}

	private boolean motorsFinished(Boolean[] values) {
		for (Boolean bool : values)
			if (!bool)
				return false;
		return true;
	}

	private void resetTachoCounts() throws RemoteException {
		this.xAchse.resetTachoCount();
		this.yAchse.resetTachoCount();
		if (xAchse.getTachoCount() != 0 || yAchse.getTachoCount() != 0)
			throw new RuntimeException("Konnte Tachocount nicht zur�cksetzen");
	}

	public void stop() throws RemoteException {
		xAchse.stop();
		yAchse.stop();
		zAchse.stop();
	}

}
