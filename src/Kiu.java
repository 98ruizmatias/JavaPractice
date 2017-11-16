import java.util.ArrayList;
import java.util.List;

public class Kiu {

    private List<Integer> x;


    public Kiu(){
        this.x = new ArrayList<>();
    }

    public void enQueue(int a){
        this.x.add(a);
    }

    public void deQueue(int index){
        this.x.remove(index);
    }

    public boolean isClean(){
        return this.x.isEmpty();
    }

    public boolean hayAlguito(){
        return !this.x.isEmpty();
    }

    public void empty(){
        this.x.clear();
    }

    public int giveLast(){
        return this.x.get(this.x.size()-1);
    }

    public int length(){
        return this.x.size();
    }

}
