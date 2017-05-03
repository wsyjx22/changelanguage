package picooc.com.changelanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class BaseActivity extends AppCompatActivity implements Observer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		changeAppLanguage();
		ObserverUtils.getInstance().addObserver(this);
	}

	public void changeAppLanguage() {
		SharedPreferences preferences = getSharedPreferences("lang", Context.MODE_PRIVATE);
		String sta = preferences.getString("lang", "zh");//这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
		// 本地语言设置
		Locale myLocale = new Locale(sta);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		Log.i("TAG", "sta = " + sta + ", local = " + conf.locale.getLanguage());
		if (TextUtils.equals("auto", sta)) {
			conf.locale = Locale.getDefault();
		} else {
			conf.locale = myLocale;
		}
		res.updateConfiguration(conf, dm);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ObserverUtils.getInstance().deleteObserver(this);
	}

	@Override
	public void update(Observable observable, Object o) {
		if (o instanceof Integer) {
			changeAppLanguage();
			recreate();
		}
	}

}
