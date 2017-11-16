public class Producer extends Thread {
    private Kiu q;
    public Producer(Kiu q){
        this.q = q;
    }

    public void exexcute(){
        this.start();
    }

    @Override
    public void run(){
        while (true){
            int i =2;
            while ( this.q.hayAlguito()){
                try {
                    if (!this.q.hayAlguito()) {
                        this.q.enQueue(3);
                        System.out.println("addeedddd");
                    }
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }

        }

    }



}
