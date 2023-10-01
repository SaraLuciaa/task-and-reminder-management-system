package ui;

import model.Queue;
import model.QueueException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            cardsExercise();
        } catch (QueueException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cardsExercise() throws QueueException {
        Scanner l = new Scanner(System.in);
        int n = l.nextInt();
        while(n!=0){
            Queue<Integer> q = new Queue<>();
            for(int j = 1; j <= n; j++){
                q.offer(j);
            }
            Queue<Integer> disCards = new Queue<>();
            while(q.size()!=1){
                disCards.offer(q.poll());
                q.offer(q.poll());
            }
            System.out.println("Discarded Cards: "+ disCards.print());
            System.out.println("Remaining Cards: "+ q.print());

            n = l.nextInt();
        }
    }
}