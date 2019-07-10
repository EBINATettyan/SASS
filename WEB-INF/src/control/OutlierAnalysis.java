package control;

import java.util.ArrayList;

public class OutlierAnalysis {

	/*
	 * 四分位数，外れ値の下限値と上限値を計算するプログラム
	 */

	public ArrayList<Float> outlierAnalysis(ArrayList<Integer> valueList) {

		System.out.println(valueList);

		float[] result = new float[3];
		int length = valueList.size();

		if (valueList == null || length < 4) {
			result[1] = 5;
		} else if (length % 2 == 0) {
			result[1] = 0.5f
					* (valueList.get(length / 2 - 1) + valueList.get(length / 2)) / 2;
		} else {
			//長さが奇数のとき
			result[1] = 0.5f * valueList.get((length + 1) / 2 - 1);
		}

		if (length >= 4 && length % 4 == 0) {
			result[0] = 0.5f
					* (valueList.get(length / 4 - 1) + valueList.get(length / 4)) / 2;
			result[2] = 0.5f * (valueList.get(3 * length / 4 - 1)
					+ valueList.get(3 * length / 4)) / 2;
		} else if (length >= 4 && length % 4 != 0) {
			result[0] = 0.5f * valueList.get(length / 4);
			result[2] = 0.5f * valueList.get(3 * length / 4);
		} else if (valueList == null || length < 4) {
			result[0] = 1;
			result[2] = 10;
		}

		//ArrayList<Float>に、下限値と上限値を代入する
		float Q1 = result[0];
		float Q3 = result[2];
		float IQR = Q3 - Q1;

		ArrayList<Float> result_outlier = new ArrayList<Float>();
		result_outlier.add((float) (Q1-(IQR*0.5)));
		result_outlier.add((float) (Q3+(IQR*0.5)));

		return result_outlier;
	}
}
