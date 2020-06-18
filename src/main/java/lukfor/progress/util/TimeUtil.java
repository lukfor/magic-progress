package lukfor.progress.util;

public class TimeUtil {

	public static String format(long time) {

		long sec = time / 1000 % 60;
		String secString = "" + sec;
		if (sec < 10) {
			secString = "0" + sec;
		}

		long min = time / 1000 / 60 % 60;
		String minString = "" + min;
		if (min < 10) {
			minString = "0" + min;
		}

		long hours = time / 1000 / 60 / 60;
		String hoursString = "" + hours;
		if (hours < 10) {
			hoursString = "0" + hours;
		}
		return hoursString + ":" + minString + ":" + secString;
	}
	
}
