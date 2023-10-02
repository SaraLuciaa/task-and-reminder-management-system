package model;

import structure.HashTable.HashTableChaining;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class TaskManagementController {
    private HashTableChaining<String,Activity> hashTableChaining;
    private ArrayList<String> keys;
    public TaskManagementController(int size){
        hashTableChaining=new HashTableChaining<>(size);
        keys=new ArrayList<>();
    }
    public String activityAdd(String tittle, String description, Calendar date, Calendar dayTime){
        Reminder reminder=new Reminder(tittle,description,date,dayTime);
        String code=keyCreator();
        hashTableChaining.add(code,reminder);
        keys.add(code);
        return "Your reminder was added with the key: "+code;
    }
    public String activityAdd(String tittle, String description, Calendar date, Calendar dayTime,boolean isPriority, int pl){
        PriorityLevel priorityLevel=searchPriorityLevel(pl);
        Task task=new Task(tittle,description,date,dayTime,isPriority,priorityLevel);
        String code=keyCreator();
        hashTableChaining.add(code,task);
        keys.add(code);
        return "Your task was added with the key: "+code;
    }
    public PriorityLevel searchPriorityLevel(int level){
        return switch (level) {
            case 1 -> PriorityLevel.HIGH;
            case 2 -> PriorityLevel.MEDIUM;
            case 3 -> PriorityLevel.LOW;
            default -> null;
        };
    }
    public String keyCreator(){
        String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder generatedCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            char randomCharacter= validCharacters.charAt(randomIndex);
            generatedCode.append(randomCharacter);
        }
        String code=generatedCode.toString();
        if(!keys.contains(code)){
            return code;
        }else{
            keyCreator();
        }
        return code;
    }
    public void exist(){
        for(String k:keys){
            System.out.println(hashTableChaining.get(k));
        }
    }
}
