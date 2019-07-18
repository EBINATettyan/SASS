# coding: UTF-8
#!/bin/python

import sys
import pandas as pd

args = sys.argv
data_str = args[1]
data = []

#intに変換して例外発生が起きなければ、data(リスト)に追加
for s in data_str:
    try:
        data.append(int(s))
    except ValueError:
        pass

data_frame = pd.DataFrame(data)

#第一四分位数の算出
Q1 = data_frame.quantile(0.25)

#第二四分位数の算出
Q2 = data_frame.quantile(0.50)

#第三四分位数の算出
Q3 = data_frame.quantile(0.75)

#四分位範囲の算出
IQR = (Q3-Q1)

#外れ値の基準点を算出
#バイアスは、本当は1.5だけど、0.5に変更
outlier_minimum = (Q1 - (IQR)*0.5)
outlier_maximum = (Q3 + (IQR)*0.5)

#無理やりだけど、outlier_minimumとoutlier_maximumの値のみを取り出せるようにし
#今回は、pd.DataFrameを用いているので、データの型に基づいてプログラムを作成する必要がある
df_minimum = pd.DataFrame({'name': ['outlier_minimum'],'score': [1.5]},columns=['name', 'score'])
df_minimum.loc[df_minimum.score == 1.5, 'score'] = outlier_minimum

df_maximum = pd.DataFrame({'name': ['outlier_maximum'],'score': [2.5]},columns=['name', 'score'])
df_maximum.loc[df_maximum.score == 2.5, 'score'] = outlier_maximum

print(str(df_minimum['score'][0])+","+str(df_maximum['score'][0]))

