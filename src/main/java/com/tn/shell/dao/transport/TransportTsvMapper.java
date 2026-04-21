package com.tn.shell.dao.transport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tn.shell.model.transport.Statut;
import com.tn.shell.model.transport.Status;

final class TransportTsvMapper {

	private static final String[] DATE_PATTERNS = new String[] {
			"yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd",
			"dd-MM-yyyy",
			"dd/MM/yyyy"
	};

	private TransportTsvMapper() {
	}

	static String asString(Object value) {
		return value == null ? null : String.valueOf(value).trim();
	}

	static Integer asInteger(Object value) {
		String s = asString(value);
		if (s == null || s.isEmpty()) {
			return null;
		}
		try {
			return Integer.valueOf(s);
		} catch (NumberFormatException ex) {
			return null;
		}
	}

	static double asDouble(Object value) {
		String s = asString(value);
		if (s == null || s.isEmpty()) {
			return 0d;
		}
		try {
			return Double.parseDouble(s);
		} catch (NumberFormatException ex) {
			return 0d;
		}
	}

	static Date asDate(Object value) {
		String s = asString(value);
		if (s == null || s.isEmpty()) {
			return null;
		}
		for (String pattern : DATE_PATTERNS) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				sdf.setLenient(false);
				return sdf.parse(s);
			} catch (ParseException ignored) {
			}
		}
		return null;
	}

	static Statut asStatut(Object value) {
		String s = asString(value);
		if (s == null || s.isEmpty()) {
			return Statut.ACTIF;
		}
		if ("deactif".equalsIgnoreCase(s)) {
			return Statut.DEACTIF;
		}
		return Statut.ACTIF;
	}

	static Status asStatus(Object value) {
		String s = asString(value);
		if (s == null || s.isEmpty()) {
			return Status.NonFacturee;
		}
		Status status = Status.fromValue(s);
		return status == null ? Status.NonFacturee : status;
	}
}
