import java.util.HashMap;

public class node{

    public static HashMap<String,Integer> nodeScore = new HashMap<>();
    public String value;
    public node left,right,up,down;
    public int score;
    
    public node(String value,Integer score){
        this.value = value;
        this.score = score;
        nodeScore.put(value, score);
    }

    public node(String inf){
        this.value = "-INF";
        this.score = Integer.MIN_VALUE;
        nodeScore.put(this.value, Integer.MIN_VALUE);
    }

    public node(node otherNode){
        this.value = otherNode.value;
        this.score = otherNode.score;
    }



}