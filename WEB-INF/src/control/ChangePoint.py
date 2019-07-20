#段階10,r=0.02,order=2,smooth=5

import sys
import matplotlib.pyplot as plt
import changefinder
import numpy as np

args = sys.argv
data_str = args[1]
data = []

#intに変換して例外発生が起きなければ、data(リスト)に追加
for s in data_str:
    try:
       data.append(int(s))
    except ValueError:
       pass
#print(data)

#各パラメータの設定
cf = changefinder.ChangeFinder(r=0.02, order=2, smooth=5)

ret = []
for i in data:
    score = cf.update(i)
    ret.append(score)

#あんまりしないけど、DBの関係でindeの値は1からスタート
index = 1
index_number=[]
for item in ret:
  if item > 30:
    index_number.append(index)
  index += 1
print(index_number)

#以下は、グラフの出力(anacondaとかでやるなら、コメント外したほうがいい)
#fig = plt.figure()
#ax = fig.add_subplot(111)
#ax.plot(ret)
#ax2 = ax.twinx()
#ax2.plot(data,'r')
#plt.show()

