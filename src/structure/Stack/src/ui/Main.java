package ui;

import model.Stack;
import model.StackException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            railwayExercise();
        } catch (StackException e) {
            throw new RuntimeException(e);
        }
    }

    public static void balancedBrackets() throws StackException {
        Scanner l = new Scanner(System.in);
        int n = l.nextInt();
        l.nextLine();
        for(int i=0; i<n; i++){
            String inp = l.nextLine();

            Stack<Character> s = new Stack<>();
            boolean flag = false;
            int cut = 0;
            for(int j=0; j<inp.length()&&!flag; j++){
                if(inp.charAt(j)=='(' || inp.charAt(j)=='[' || inp.charAt(j)=='{'){
                    s.push(inp.charAt(j));
                    cut++;
                } else {
                    flag=true;
                }
            }

            inp = inp.substring(cut);

            boolean flag2 = true;
            for(int k=0; k<inp.length()&&flag2; k++){
                if(inp.charAt(k) == ')' && s.peek() == '('){
                    s.pop();
                } else if(inp.charAt(k) == ']' && s.peek() == '['){
                    s.pop();
                } else if(inp.charAt(k) == '}' && s.peek() == '{'){
                    s.pop();
                } else {
                    flag2 = false;
                }
            }

            if(flag2){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void railwayExercise() throws StackException{
        Scanner l = new Scanner(System.in);
        int n = l.nextInt();
        l.nextLine();
        while(n!=0){
            String m = l.nextLine();

            while(!m.equals("0")){
                Stack<Integer> s = new Stack<>();
                for(int i=1; i<=n; i++){
                    s.push(i);
                }

                int[] array = stringToIntArray(m);

                Stack<Integer> temp = new Stack<>();

                for(int j = 0; j<array.length; j++){
                    if(array[j] == s.peek()){
                        s.pop();
                    } else if (!temp.isEmpty() && s.peek() == temp.pop()){
                        s.pop();
                    } else {
                        temp.push(array[j]);
                    }
                    s.print();
                    temp.print();
                }

                if(s.isEmpty()){
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }

                m = l.nextLine();
            }
            n = l.nextInt();
        }
    }

    public static int[] stringToIntArray(String input) {
        String[] splitStrings = input.split(" ");

        int[] result = new int[splitStrings.length];

        for (int i = 0; i < splitStrings.length; i++) {
            result[i] = Integer.parseInt(splitStrings[i]);
        }

        return result;
    }

}