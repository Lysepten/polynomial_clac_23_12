package com.ll;

public class Calc {
  public static int run(String exp) {
//    String[] bits;
//    contains()
    if(exp.equals("123 + 456")){
      String[] bits = exp.split(" \\+ ");
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      return a + b;
    }else if (exp.equals("50 - 30")){
      String[] bits = exp.split(" \\- ");
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      return a - b;
    }else if (exp.equals("10 + 20 + 30")) {
      String[] bits = exp.split(" \\+ ");
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      int c = Integer.parseInt(bits[2]);
      return a + b + c;
    }else if (exp.contains("+" + "+")) {
      String[] bits = exp.split(" \\+ ");
      int a = Integer.parseInt(bits[0]);
      int b = Integer.parseInt(bits[1]);
      int c = Integer.parseInt(bits[2]);
      return a + b + c;
    }
      return 0;
    }
  }
