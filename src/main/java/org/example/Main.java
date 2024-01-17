package org.example;


public class Main {
    public static void main(String[] args) {
        int[] array = new int[300];
        for (int i = 1; i <301; i++){
            array[i-1] = i;
        }
        String s = ser(array);
        System.out.println(s);
        int[] newArray = deser(s);
        for (int a : newArray){
            System.out.println(a);
        }
    }


    public static String ser(int[] array){
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : array){
            if (num ==0){
                break;
            }
            stringBuilder
                    .append(toBase18(num));
        }
        return stringBuilder.toString();
    }

    public static int[] deser(String string){
        String[] strNums = string.split("(?<=\\G.{2})");
        int [] nums = new int[strNums.length];
        for(int i = 0; i < strNums.length; i++){
            nums[i] = fromBase18(strNums[i]);
        }
        return nums;
    }


    public static String toBase18(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            int digit = n % 18;
            if (digit < 10) {
                result.append(digit);
            } else {
                result.append((char) ('A' + digit - 10));
            }
            n /= 18;
        }
        if (result.length() ==1){
            result.append(0);
        }
        return result.reverse().toString();
    }



    public static int fromBase18(String n) {
        int result = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            char digit = n.charAt(i);
            if (digit >= '0' && digit <= '9') {
                result += (digit - '0') * Math.pow(18, n.length() - 1 - i);
            } else {
                result += (digit - 'A' + 10) * Math.pow(18, n.length() - 1 - i);
            }
        }
        return result;
    }
}