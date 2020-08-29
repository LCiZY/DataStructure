import java.util.Scanner;

import javax.naming.ldap.SortKey;

public class sortedset {
    
    public void zadd(node n){
        
    }

    public static void main(String[] args) {
        
        skipList sl = new skipList();

        for(int i = 0; i<64; i+=2){
            sl.add("第"+i, i);
        }

        sl.showIndexLevel();

        while(true){
            Scanner in = new Scanner(System.in);

            int score = in.nextInt();  if(score==-1) break;  int le = in.nextInt(); 

            node nn = sl.searchNodeForInsert("第"+score,score,le);
            System.out.println("value "+nn.value + "    score "+nn.score);

            // System.out.println("寻找第 "+score + " 结果："+ sl.search("第"+score, score));
            // System.out.println("索引 "+skipList.indicesTimes + " 次");
            // skipList.indicesTimes = 0;
        }


    }

}