package test;

public class Main {
 public static void main(String[] args) {
  String test = "false";
  String test2 = null;

  System.out.println(isTrusty(test));
  System.out.println(isTrusty(test2));
 }
 // verifica se a variavel contem algum valor e que nao seja null
 public static boolean isTrusty(String s) {
  return s != null && s.contains(s) == true ? true : false;
 }

}
