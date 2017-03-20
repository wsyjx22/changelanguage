package picooc.com.changelanguage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DetailsActivity extends BaseActivity {

	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		preferences = getSharedPreferences("lang", Context.MODE_PRIVATE);
		editor = preferences.edit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "中文");
		menu.add(2, 2, 1, "英文");
		menu.add(3, 3, 1, "韩语");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
			case 1:
				editor.putString("lang", "zh");
				editor.commit();
				break;
			case 2:
				editor.putString("lang", "en");
				editor.commit();
				break;
			case 3:
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
		return true;
	}

	private void go2Main() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
