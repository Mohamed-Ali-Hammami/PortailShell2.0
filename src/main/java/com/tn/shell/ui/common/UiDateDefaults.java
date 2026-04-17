package com.tn.shell.ui.common;

import java.util.Calendar;
import java.util.Date;

public final class UiDateDefaults {

	private UiDateDefaults() {
	}

	public static Date copy(Date date) {
		return date == null ? null : new Date(date.getTime());
	}

	public static Date today() {
		return new Date();
	}

	public static Date coalesce(Date primary, Date fallback) {
		return copy(primary != null ? primary : fallback);
	}

	public static Date latest(Date first, Date second) {
		if (first == null) {
			return copy(second);
		}
		if (second == null) {
			return copy(first);
		}
		return first.after(second) ? copy(first) : copy(second);
	}

	public static Date startOfDay(Date source) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(coalesce(source, today()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date endOfDay(Date source) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(coalesce(source, today()));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
}
