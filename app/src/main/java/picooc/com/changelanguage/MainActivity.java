package picooc.com.changelanguage;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

	private Button mSettingBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(MainActivity.class.getSimpleName(), "onCreate");
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mSettingBtn = (Button) findViewById(R.id.setting_btn);
		mSettingBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.setting_btn:
				Intent intent = new Intent(this, SettingActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(MainActivity.class.getSimpleName(), "onDestroy");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i(MainActivity.class.getSimpleName(), "onNewIntent");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.i(MainActivity.class.getSimpleName(), "onConfigurationChanged");
	}
}
