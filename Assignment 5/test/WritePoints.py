import os

with open("points.txt", "wb") as f:
    for i in range(1, 26):
        for j in range(1,26):
            f.write(str(i)+' '+str(j)+os.linesep)