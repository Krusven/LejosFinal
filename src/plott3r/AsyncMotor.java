package plott3r;

import java.rmi.RemoteException;

public class AsyncMotor implements Runnable {

	private Motor motor;
	private MultiPositionAchse achse;
	private double rotations, speed;
	private int index;
	private Callback<Integer> callback;
	private Thread thread;

	public AsyncMotor(int index, MultiPositionAchse achse, double rotations, double speed, Callback<Integer> callback)
			throws RemoteException {
		this.motor = achse.getMotor();
		this.rotations = rotations;
		this.achse = achse;
		this.speed = achse.setSpeed(speed);
		this.callback = callback;
		this.index = index;
		this.thread = new Thread(this);
	}

	public void move() {
		this.thread.start();
	}

	public boolean isMoving() {
		try {
			return this.motor.isMoving();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void finish() throws InterruptedException {
		this.callback.callFinish(this.index);
		Thread.currentThread().join();
	}

	@Override
	public void run() {
		try {
			this.achse.rotateMm(rotations);
			System.out.println(this.index + ". RUN " + this.rotations + " speed " + this.speed);
			finish();
		} catch (RemoteException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
