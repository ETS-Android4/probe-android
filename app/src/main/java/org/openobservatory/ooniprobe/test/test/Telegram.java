package org.openobservatory.ooniprobe.test.test;

import static org.openobservatory.ooniprobe.model.jsonresult.TestKeys.BLOCKED;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.openobservatory.ooniprobe.R;
import org.openobservatory.ooniprobe.common.PreferenceManager;
import org.openobservatory.ooniprobe.model.database.Measurement;
import org.openobservatory.ooniprobe.model.database.Result;
import org.openobservatory.ooniprobe.model.jsonresult.JsonResult;
import org.openobservatory.ooniprobe.model.settings.Settings;

public class Telegram extends AbstractTest {
	public static final String NAME = "telegram";

	public Telegram() {
		super(NAME, R.string.Test_Telegram_Fullname, R.drawable.test_telegram, R.string.urlTestTlg, 10);
	}

	@Override public void run(Context c, PreferenceManager pm, Gson gson, Result result, int index, TestCallback testCallback) {
		Settings settings = new Settings(c, pm);
		run(c, pm, gson, settings, result, index, testCallback);
	}

	@Override public void onEntry(Context c, PreferenceManager pm, @NonNull JsonResult json, Measurement measurement) {
		super.onEntry(c, pm, json, measurement);
		if (json.test_keys.telegram_http_blocking == null || json.test_keys.telegram_tcp_blocking == null || json.test_keys.telegram_web_status == null)
			measurement.is_failed = true;
		else
			measurement.is_anomaly = json.test_keys.telegram_http_blocking || json.test_keys.telegram_tcp_blocking || json.test_keys.telegram_web_status.equals(BLOCKED);
	}
}
