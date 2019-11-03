from z3 import *
import time

# Number of Queens
print("N: ")
N = int(input())

start = time.time()
s = Solver()

# Variables (1차원 배열을 만들어준다)
X = [Int("x_%s" % (i)) for i in range(N)]

# Domains (각 queen의 위치는 1부터 N으로 표시 -> Domain은 1~N까지의 수가 된다)
s.add([And(X[i]>=1, X[i] <= N) for i in range(N)])

# Constraints
# for문의 범위를 i-range(N-1) j-range(i+1,N)으로 설정해서 i와 j가 동일한 값이 나오지 않도록 설정
# 1. 다른 줄에 있는 queen들은 같은 값을 가지면 안 되므로 X[i] != X[j]을 설정해준다
# 2. abs(X[i] - X[j]) != abs(i-j)로 하면 에러가 떠서 X[i]-i != X[j]-j로 constraint 추가
# 3. 위 2번에서 발견하지 못한 인접하거나 대각선상 queen들을 찾기 위해 X[i]+i != X[j]+j 추가
for i in range(N-1):
    for j in range(i+1,N):
        s.add(X[i] != X[j], X[i]-i != X[j]-j, X[i]+i != X[j]+j)

# SAT판단 후에 생성된 모델을 출력해준다
if s.check() == sat:
    m = s.model()
    r = [m.evaluate(X[i]) for i in range(N)]
    print(r)

end = time.time()
print("elapsed time: ",end-start," sec")
