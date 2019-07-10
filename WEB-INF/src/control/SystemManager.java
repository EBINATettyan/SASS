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
			pb.command("python", "/Users/csuser/Desktop/Python/OutlierAnalysis.py", valueList_str);
			//pb.directory(new File("/Users/csuser/.pyenv/versions/anaconda3-4.2.0/lib/python3.5/site-packages/"));
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

			//標準出力の内容を出力
			for (String s : it.getStringList()) {
				System.out.println(s);
			}
			//標準エラーの内容を出力
			for (String s : et.getStringList()) {
				System.err.println(s);
			}

			/*
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int exitCode = p.waitFor();
			System.out.println("Exit Code : " + exitCode);
			String result = new String(in.readLine());
			System.out.println(result);

			//次に、,の左側と右側を別々の変数に格納
			int index = result.indexOf(",");
			float result1 = Float.parseFloat(result.substring(0, index));
			float result2 = Float.parseFloat(result.substring(index + 1));
			*/

			/*
			 * 0番目にoutlier_minimum,1番目にoutlier_maximumを代入
			 */
			ArrayList<Float> resultList = new ArrayList<Float>();
			resultList.add((float) 0);
			resultList.add((float) 1);

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