package com.jk.util;

import java.util.UUID;

public class StringUUID {
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
