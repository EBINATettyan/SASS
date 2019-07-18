package control;

import java.io.IOException;
import java.util.ArrayList;

public class SystemManager {
	public ArrayList<Float> outlierAnalysisByPython(ArrayList<Integer> valueList) throws IOException {

		//https://stackoverflow.com/questions/26171862/java-processbuilder-not-able-to-run-python-script-in-java

		try {

			ArrayList<String> valueList_new = new ArrayList<String>();
			for (int i = 0; i < valueList.size(); i++) {
				valueList_new.add(String.valueOf(valueList.get(i)));
			}
			String[] valueList_array = valueList_new.toArray(new String[valueList_new.size()]);
			String valueList_str = String.join(",", valueList_array);

			ProcessBuilder pb = new ProcessBuilder();
			//※pathは必要に応じて変更する必要あり
			pb.command("python", "/root/eclipse-workspace/SASS/WEB-INF/src/control/OutlierAnalysis.py", valueList_str);
			Process p = pb.start();

			//InputStreamのスレッド開始
			InputStreamThread it = new InputStreamThread(p.getInputStream());
			InputStreamThread et = new InputStreamThread(p.getErrorStream());
			it.start();
			et.start();

			//プロセスの終了待ち
			p.waitFor();

			//InputStreamのスレッド終了待ち
			it.join();
			et.join();

			System.out.println("戻り値：" + p.exitValue());

			//標準エラーの内容を出力
			for (String s : et.getStringList()) {
				System.err.println(s);
			}

			String result = it.getStringList().toString();
			result = result.replace("[", "");
			result = result.replace("]", "");
			int index = result.indexOf(",");
			float result1 = Float.parseFloat(result.substring(0, index));
			float result2 = Float.parseFloat(result.substring(index + 1));

			/*
			 * 0番目にoutlier_minimum,1番目にoutlier_maximumを代入
			 */
			ArrayList<Float> resultList = new ArrayList<Float>();
			resultList.add(result1);
			resultList.add(result2);

			//標準出力の内容を出力
			System.out.println("<------------------------->");
			System.out.println("数値データの下限値" + resultList.get(0));
			System.out.println("数値データの上限値" + resultList.get(1));
			System.out.println("<------------------------->");

			return resultList;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}


	public ArrayList<Float> outlierAnalysisByJava(ArrayList<Integer> valueList) throws IOException {

		/*
		 * 0番目にoutlier_minimum,1番目にoutlier_maximum
		 */
		OutlierAnalysis outlierAnalysis = new OutlierAnalysis();
		ArrayList<Float> result_outlier = new ArrayList<Float>();
		result_outlier = outlierAnalysis.outlierAnalysis(valueList);

		return result_outlier;

	}

}