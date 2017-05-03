package picooc.com.changelanguage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

	private TextView mLang;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		preferences = getSharedPreferences("lang", Context.MODE_PRIVATE);
		editor = preferences.edit();
		initView();
	}

	private void initView() {
		mLang = (TextView) findViewById(R.id.lang);
		mLang.setOnClickListener(this);
		getFragmentManager().beginTransaction().replace(R.id.fragment_content, new PrefsFragement()).commit();
	}

	@Override
	public void onClick(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setSingleChoiceItems(R.array.lang, android.R.id.text1, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
				saveLangConfig(i);
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void saveLangConfig(int i) {
		switch (i) {
			case 0:
				editor.putString("lang", "zh");
				editor.commit();
				break;
			case 1:
				editor.putString("lang", "en");
				editor.commit();
				break;
			case 2:
				editor.putString("lang", "ko");
				editor.commit();
				break;
			default:
				editor.putString("lang", "zh");
				editor.commit();
				break;
		}
		// 第一种方式，观察者模式通知刷新recreate
//		ObserverUtils.getInstance().notifyObservers(Integer.parseInt("1"));
		// 第二种方式，跳回MainActivity
		go2Main();
	}

	private void go2Main() {
		// 微信切换语言后是回调主页
		// 微博切换语音后是重启App
		// 设计切换语音为系统自动，和自己支持的语音可选
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
