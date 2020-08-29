import java.util.ArrayList;
import java.util.Random;


public class skipList{

    private int size = 0; public static int indicesTimes = 0;
    private int max_level;
    private ArrayList<node> roots = new ArrayList<>();
    private node prev = null;
    private node curr = null; 

    public skipList(){
        this(4);
    }

    public skipList(int n){
        this.max_level = n;

        roots.add(0, new node("inf"));
        for(int i=1;i<max_level;i++){
            roots.add(i, new node("inf"));
            roots.get(i).down = roots.get(i-1);
        }
    }

    public void add(String value,int score){
        node n = new node(value, score);

        if(
            //this.insertLinkList(roots.get(0), n)
            insertIntoILevel(0,n)
            ){
             ++size;
             //如果超过2^maxlevel ，则将最大层数+1
             if(size>(2<<(max_level-1))) { roots.add(max_level++, new node("inf")); roots.get(max_level-1).down = roots.get(max_level-2); }
        }else{
            return;
        }

       int count = 1; boolean result = true;
       while(result&&count<max_level){
            Random random = new Random();
            if(random.nextInt(2)==1) {
                result = true;
                node n1 = new node(n);
                insertIntoILevel(count,n1);
               // this.insertLinkList(roots.get(count), n1);
                n1.down = n;
                n = n1;
            }else{
                result =false;
            }
            ++count;
       }

    }

    public boolean search(String value,int score){
        node curr = roots.get(max_level-1);
        node prev = curr;
        while(true){
            indicesTimes++;
            if(curr.score == score&&curr.value.equals(value)) return true;
            if(curr.score <score){
                if(curr.right==null){
                    if(curr.down==null) return false;
                    else{
                        curr = curr.down;
                        prev = curr;
                    }
                }else{
                    prev = curr;
                    curr = curr.right;
                }
            }else{
                if(prev.down==null){
                    return false;
                }
                else{
                    curr = prev.down;
                    prev = prev.down;
                    
                }
            }

            // System.out.println("prev："+prev.value);
            // System.out.print(indicesTimes+":"+"curr"+curr.value+"   ");
        }
    }

    public node searchNodeForInsert(String value,int score,int level){
        node curr = roots.get(max_level-1);
        node prev = curr;
        int downTimes = max_level-1;
        while(true){
            if(curr.score == score&&curr.value.equals(value)) return null;
            if(curr.score <score){
                if(curr.right==null){
                    if(downTimes == level) return curr;
                    else{
                        curr = curr.down;
                        prev = curr;
                        downTimes--;
                    }
                }else{
                    prev = curr;
                    curr = curr.right;
                }
            }else{
                if(downTimes == level){
                    return prev;
                }
                else{
                    curr = prev.down;
                    prev = prev.down;
                    downTimes--;
                }
            }

        }
    }


    public boolean insertIntoILevel(int level,node n){
        node prev = searchNodeForInsert(n.value,n.score,level);
        if(prev == null ) return false;
        node temp = prev.right;
        prev.right = n;
        n.right = temp;
        return true;

    }

    public boolean insertLinkList(node head, node n){
        if(head == null || n == null) return false;
        node curr = head;
        node prev = head;
        while(true){
            if(curr == null){
                prev.right = n;  return true;
            }
            if(curr.score == n.score) {
                if(curr.value.equals(n.value))break;
                else {
                    node temp = curr.right;
                    curr.right = n;
                    n.right = temp;
                    return true;
                }
            }
            else if(curr.score < n.score){
                prev = curr;
            }
            else{
                prev.right = n;
                n.right = curr;  return true;
            }

            curr = curr.right;

        }
        return false;
    }


    public void show(){
        node head = roots.get(0);
        while(head!=null){
          System.out.print(head.value + " → ");
            head = head.right;
        }
    }

    public void showIndexLevel(){
        for(int i=max_level-1; i>=0; i-- ){
            node head = roots.get(i);
            while(head!=null){
            System.out.print(head.value + "→");
                head = head.right;
            }
            System.out.println("\n");
        }

    }



}