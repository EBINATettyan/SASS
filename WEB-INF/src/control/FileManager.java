package control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

	public String checkSpace(String fileName) {

		String str = fileName;
		String check1 = " ";
		String check2 = "　";

		Pattern pattern1 = Pattern.compile(check1);
		Matcher matcher1 = pattern1.matcher(str);
		str = matcher1.replaceAll("_");

		Pattern pattern2 = Pattern.compile(check2);
		Matcher matcher2 = pattern2.matcher(str);
		String s = matcher2.replaceAll("_");

		/*
		 * 先頭と末尾に_がある場合消去して更新
		 */
		String result = checkUnderBar(s);

		return result;
	}

	public String checkUnderBar(String s) {

		StringBuilder str = new StringBuilder(s);

		/*
		 * 先頭に_があるか判断 先頭に_があったらそれを消去
		 */
		if (String.valueOf(str.charAt(0)).equals("_")) {
			str = str.deleteCharAt(0);
		}

		// 文字列の長さを受けとる
		int length = str.length();

		// 末尾に_があるか判断 末尾に_があったらそれを消去
		if (String.valueOf(str.charAt(length - 1)).equals("_")) {
			str = str.deleteCharAt(length - 1);
		}

		return str.toString();
	}

	public String moveFileName(String fileName) {

		Calendar c = Calendar.getInstance();
		// フォーマットパターンを指定して表示する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String result = sdf.format(c.getTime()) + fileName;

		return result;
	}

	public int IdentifyFileKind(String fileName) {

		int result = 0;

		String str = fileName;
		String regex1 = ".png$";
		String regex2 = ".PNG$";
		String regex3 = ".jpg$";
		String regex4 = ".JPG$";
		String regex13 = ".jpeg$";
		String regex14 = ".JPEG$";
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(str);
		Pattern p2 = Pattern.compile(regex2);
		Matcher m2 = p2.matcher(str);
		Pattern p3 = Pattern.compile(regex3);
		Matcher m3 = p3.matcher(str);
		Pattern p4 = Pattern.compile(regex4);
		Matcher m4 = p4.matcher(str);
		Pattern p13 = Pattern.compile(regex13);
		Matcher m13 = p13.matcher(str);
		Pattern p14 = Pattern.compile(regex14);
		Matcher m14 = p14.matcher(str);

		String regex5 = ".mp4$";
		String regex6 = ".MP4$";
		String regex7 = ".m4v$";
		String regex8 = ".M4V$";
		String regex9 = ".mov$";
		String regex10 = ".MOV$";
		String regex11 = ".qt$";
		String regex12 = ".QT$";
		Pattern p5 = Pattern.compile(regex5);
		Matcher m5 = p5.matcher(str);
		Pattern p6 = Pattern.compile(regex6);
		Matcher m6 = p6.matcher(str);
		Pattern p7 = Pattern.compile(regex7);
		Matcher m7 = p7.matcher(str);
		Pattern p8 = Pattern.compile(regex8);
		Matcher m8 = p8.matcher(str);
		Pattern p9 = Pattern.compile(regex9);
		Matcher m9 = p9.matcher(str);
		Pattern p10 = Pattern.compile(regex10);
		Matcher m10 = p10.matcher(str);
		Pattern p11 = Pattern.compile(regex11);
		Matcher m11 = p11.matcher(str);
		Pattern p12 = Pattern.compile(regex12);
		Matcher m12 = p12.matcher(str);

		if (m1.find() || m2.find() || m3.find() || m4.find() || m13.find() || m14.find()) {
			result = 1;
			return result;
		} else if (m5.find() || m6.find() || m7.find() || m8.find() || m9.find() || m10.find() || m11.find()
				|| m12.find()) {
			result = 2;
			return result;
		}
		return result;
	}

	/*
	 * 本当はファイルごと消去したいけど、クエリをあんまり送りたくないので×
	 */
}
