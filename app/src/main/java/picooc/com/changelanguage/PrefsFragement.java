package picooc.com.changelanguage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;

public class PrefsFragement extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, PreferenceFragment.OnPreferenceStartFragmentCallback {
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Log.i("TAG", "key = " + key);
		Log.i("TAG", "value = " + sharedPreferences.getString(key, ""));
		if (key.equals("list_preference")) {
			save(sharedPreferences.getString(key, ""));
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	private void save(String value) {
		SharedPreferences preferences = getActivity().getSharedPreferences("lang", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("lang", value);
		editor.commit();
		go2Main();
	}

	private void go2Main() {
		// 微信切换语言后是回调主页
		// 微博切换语音后是重启App
		// 设计切换语音为系统自动，和自己支持的语音可选
		Intent intent = new Intent(getActivity(), MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	@Override
	public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
		Log.i("TAG", "key = " + pref.getKey());
		return false;
	}
}
