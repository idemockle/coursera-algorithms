public class StackOfStrings {
  private String[] stack;
  
  public StackOfStrings() {
    //create an empty stack
    stack = new String[0];
  }
  
  public void push(String item) {
    //insert a new string onto stack
    String[] new_stack = new String[stack.length + 1];
    System.arraycopy(stack, 0, new_stack, 0, stack.length);
    new_stack[new_stack.length-1] = item;
    stack = new_stack;
    
    /* for (int i; i < new_stack.length-1; i++) {
      new_stack[i] = stack[i];
    } */
  }
  
  public String pop() {
    //remove and return the string most recently added
    String popped = stack[stack.length];
    String[] new_stack = new String[stack.length-1];
    System.arraycopy(stack, 0, new_stack, 0, stack.length-1);
    stack = new_stack;
    return popped;
  }
  
  public boolean isEmpty() {
    if (stack.length == 0) return true;
    else return false;
  }
}
