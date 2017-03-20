package picooc.com.changelanguage;

import java.util.Observable;

/**
 * Created by Administrator on 2017/3/16.
 */

public class ObserverUtils extends Observable {

	public static ObserverUtils instance;

	private ObserverUtils() {

	}

	public static ObserverUtils getInstance() {
		if (null == instance) {
			instance = new ObserverUtils();
		}
		return instance;
	}

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	public void removeAll() {
		instance.deleteObservers();
		instance = null;
	}

}
