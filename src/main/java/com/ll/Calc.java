package com.ll;

public class Calc { Calc // 클래스 설계도
  public static int run(String exp) { // 10 + (10 + 5) // 메서드로 String타입의 exp를 매개변수로 받고있다.
    exp = exp.trim(); // 매개변수로 전달받은 exp를 trim()으로 함수를 실행시켜 문자열 처음과 끝에 여백이 있다면 지워준다.
    exp = stripOuterBracket(exp); // exp를 stripOuterBracket() 메서드로 인자를 보내주어 문자열 양옆에 괄호가 있다면 지워준다.

    // 연산기호가 없으면 바로 리턴
    if (!exp.contains(" ")) return Integer.parseInt(exp); // exp로 들어온 문자열에 공백이 없다면 그대로 exp를 정수화해서 리턴해준다.

    boolean needToMultiply = exp.contains(" * "); // boolean 타입으로 exp문자열에 " * "를 포함하고 있다면 true가 된다.
    boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");  // boolean 타입으로 exp문자열에 " + " 또는 " - "를 포함하고 있다면 true가 된다.

    boolean needToCompound = needToMultiply && needToPlus;  // boolean 타입으로 needToMultiply변수가 true이고 needToPlus변수도 true일때 이 변수는 true가 된다.
    boolean needToSplit = exp.contains("(") || exp.contains(")");  // boolean 타입으로 exp문자열에 "(" 또는 ")"를 포함하고 있다면 true가 된다.


    if (needToSplit) {  // needToSplit 변수가 true일때 실행되는 조건문.

      int splitPointIndex = findSplitPointIndex(exp); // int타입으로 선언된 splitPointIndex에  findSplitPointIndex()메서드를 실행시키고 exp를 인자로 넣어준다.

      String firstExp = exp.substring(0, splitPointIndex); // String 타입으로 선언된 firstExp에 exp문자열을 substring로 0번째 인덱스부터 splitPointIndex변수의 값 만큼 문자열을 잘라낸다.
      String secondExp = exp.substring(splitPointIndex + 1); // String 타입으로 선언된 secondExp에 exp문자열을 substring로 splitPointIndex에 1을 더한 값으로 인덱스를 기준으로 잡고
      // 기준부터 시작된 문자열을 잘라낸다.

      char operator = exp.charAt(splitPointIndex); // char타입으로 선언된 operator변수에 charAt를 실행시키고 splitPointIndex의 값의 인덱스를 찾고 넣어준다.

      exp = Calc.run(firstExp) + " " + operator + " " + Calc.run(secondExp); // exp변수에 Calc.run재귀함수로 firstExp를 넣어주고 " " 여백을 붙이고 operator값을 붙이고 재귀함수로 secondExp
      //를 넣어주고 돌아온 값을 문자열 형태로 모두 더해주고 exp값으로 넣어준다.
      return Calc.run(exp); // Calc.run재귀함수에 exp값을 넣어주고 리턴한다.

    } else if (needToCompound) { // 한묶음으로 엮여있는 첫번째 if문이 false일 경우 needToCompound가 true인지 체크하고 맞다면 조건문을 실행한다.
      String[] bits = exp.split(" \\+ "); // String 타입의 배열로 생성될 bits변수에 exp를 split을 실행하고 " \\+ " 기준으로 쪼개어 각 배열에 넣어준다.

      return Integer.parseInt(bits[0]) + Calc.run(bits[1]); // 문자열을 int형태로 바꿔주는 Integer.parseInt에 bits의 첫번째 인덱스를 넣어주고 재귀함수로 Calc.run을 실행시켜
      // bits의 두번째 인덱스를 넣어주고 돌아온 값끼리 서로 더해준 값을 리턴한다.
    }
    if (needToPlus) { // needToPlus가 true일때 조건문을 실행한다.
      exp = exp.replaceAll("\\- ", "\\+ \\-"); // exp의 문자열을 replaceAll을 실행시켜 "\\- "형태로 되어있는 값들을 "\\+ \\-"로 치환해준다.

      String[] bits = exp.split(" \\+ "); // String 타입의 배열로 생성될 bits변수에 exp를 split을 실행하고 " \\+ " 기준으로 쪼개어 각 배열에 넣어준다.

      int sum = 0; int // 타입으로 선언된 sum 변수에 0을 넣어준다.

      for (int i = 0; i < bits.length; i++) { // bits배열의 변수의 길이만큼 돌면서 배열의 인덱스를 i로 넣어주고 위치에 맞는 값들을 정수로 변환하고 sum에 더해주고 할당한다.
        sum += Integer.parseInt(bits[i]);
      }

      return sum; // sum의 값을 리턴해준다.
    } else if (needToMultiply) { // needToPlus의 조건문이 false일 경우 실행되는 조건문이고 needToMultiply변수가 true일때 실행된다.
      String[] bits = exp.split(" \\* "); // String 타입의 배열로 생성될 bits변수에 exp를 split을 실행하고 " \\* " 기준으로 쪼개어 각 배열에 넣어준다.

      int rs = 1; // int 타입으로 선언된 rs에 1을 값으로 넣어준다.

      for (int i = 0; i < bits.length; i++) { // bits배열의 변수의 길이만큼 돌면서 배열의 인덱스를 i로 넣어주고 위치에 맞는 값들을 정수로 변환하고 rs에 곱해주고 할당한다.
        rs *= Integer.parseInt(bits[i]);
      }
      return rs; // rs의 값을 리턴해준다.
    }

    throw new RuntimeException("처리할 수 있는 계산식이 아닙니다"); // 모든 조건문이 false일 경우 마지막에 예외처리 되며 출력된다.
  }

  private static int findSplitPointIndexBy(String exp, char findChar) { // 메서드 형태로 String과 char타입으로 각각 매개변수를 받고있다.
    int bracketCount = 0; // int 타입으로 선언된 bracketCount에 0을 값으로 넣어준다.

    for (int i = 0; i < exp.length(); i++) { // esp의 문자열의 길이만큼 반복하며 i가 길이을 초과했을때 실행을 종료한다.
      char c = exp.charAt(i); // char타입으로 선언된 c변수에 exp문자열의 i번째 문자를 가져오고 넣어준다.

      if (c == '(') { // c에 들어있는 값이 '('와 같다면 조건문을 실행한다.
        bracketCount++; // 조건문이 참일경우 bracketCount의 값을 1 증가시킨다.
      } else if (c == ')') { // 첫번째 조건문이 false일 경우 c에 들어있는 값이 ')'와 같다면 실행한다.
        bracketCount--; // 조건문이 참일경우 bracketCount의 값을 1 감소시킨다.
      } else if (c == findChar) { // else if의 조건이 false일 경우 c에 들어있는 값이 매개변수로 들어온 findChar와 같다면 실행한다.
        if (bracketCount == 0) return i; // 위의 조건문이 참일경우 bracketCount에 들어있는 값이 0과 같다면 i의 값을 리턴해준다.
      }
    }
    return -1; // 모든 조건문이 끝나고 return을 만나지 못했을 경우에 -1을 리턴해준다.
  }

  private static int findSplitPointIndex(String exp) {
    int index = findSplitPointIndexBy(exp, '+');

    if (index >= 0) return index;

    return findSplitPointIndexBy(exp, '*');
  }

  private static String stripOuterBracket(String exp) {
    int outerBracketCount = 0;

    while (exp.charAt(outerBracketCount) == '(' && exp.charAt(exp.length() - 1 - outerBracketCount) == ')') {
      outerBracketCount++;
    }

    if (outerBracketCount == 0) return exp;


    return exp.substring(outerBracketCount, exp.length() - outerBracketCount);
  }
}