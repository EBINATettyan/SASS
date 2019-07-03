package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SystemManager {
	public ArrayList<Float> outlierAnalysisByPython(ArrayList<Integer> valueList) throws IOException {

		try {

			ProcessBuilder pb = new ProcessBuilder("python",
					"/Applications/Eclipse_4.8.0.app/Contents/workspace/SASS/WEB-INF/src/estimation/OutlierAnalysis.py",
					"" + valueList);
			Process p = pb.start();

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String result = new String(in.readLine());

			//次に、,の左側と右側を別々の変数に格納
			int index = result.indexOf(",");
			float result1 = Float.parseFloat(result.substring(0, index));
			float result2 = Float.parseFloat(result.substring(index + 1));

			/*
			 * 0番目にoutlier_minimum,1番目にoutlier_maximumを代入
			 */
			ArrayList<Float> resultList = new ArrayList<Float>();
			resultList.add(result1);
			resultList.add(result2);

			System.out.println("分析結果（outlier_minimum：）"+result1);
			System.out.println("分析結果（outlier_maximum：）"+result2);

			return resultList;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}