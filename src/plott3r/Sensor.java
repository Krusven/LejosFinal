package plott3r;

import java.rmi.RemoteException;

import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;

public abstract class Sensor {

	private float[] sample;
	private RMISampleProvider provider;

	public Sensor(RemoteEV3 ev3, String port, String name, String mode) throws RemoteException {
		this.provider = ev3.createSampleProvider(port, "lejos.hardware.sensor." + name, mode);
		this.sample = this.provider.fetchSample();
		this.kalibriere(getWert());
	}

	public void close() throws RemoteException {
		this.provider.close();
	}

	private float getWert() throws RemoteException {
		sample = provider.fetchSample();
		return sample[0];
	}

	public final boolean isAktiv() throws RemoteException {
		return this.isAktiv(getWert());
	}

	@Override
	public void finalize() throws RemoteException {
		this.finalize();
		this.provider.close();
	}

	protected abstract boolean isAktiv(float wert);

	protected void kalibriere(float wert) {
		// Hook
	};

}
