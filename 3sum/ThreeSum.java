public class ThreeSum {
  public static int count(int[] ints) {
    int threeSumCount = 0;
    
    for (int i=0; i<ints.length; i++) {
      for (int j=i+1; j<ints.length; j++) {
        for (int k=j+1; k<ints.length; k++) {
          if (0 == ints[i]+ints[j]+ints[k]) {
            threeSumCount++;
          }
        }
      }
    }
    
    return threeSumCount;
  }
  
  public static void main(String[] args) {
    int[] ints = new int[args.length];
    
    for (int i=0; i<args.length; i++)
      ints[i] = Integer.parseInt(args[i]);
    
    System.out.println(count(ints));
  }
}