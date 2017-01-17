import sys

def threesumcount(ints):
  count = 0
  
  for i in xrange(len(ints)):
    for j in xrange(i+1, len(ints)):
      for k in xrange(j+1, len(ints)):
        if sum((ints[i],ints[j],ints[k])) == 0:
          count += 1
  
  return count
  
if __name__ == "__main__":
  ints = [int(i) for i in sys.argv[1:]]
  print threesumcount(ints)