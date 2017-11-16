

public class Test {

    public static void main(String[] args){
        int[] a = new int[]{1,24,-3223,11111,1,31,322,1,4,134};

        int k;
        int aux;
        for (int i = 0; i < a.length-1; ++i){
            for (int j = 0; j < a.length-1; ++j){
                k = j+1;
                if (a[j] > a[k]){
                    aux = a[j];
                    a[j] = a[k];
                    a[k] = aux;
                }
            }
        }

        int num = 1;
        boolean bul = isHere(a, num);

        if (bul){
            System.out.printf("si");
        } else {
            System.out.printf("no");
        }

    }


    public static boolean isHere(int []a, int num){
        int top = a.length-1;
        int bot = 0;
        if (num < a[bot] || num > a[top]){
            System.out.printf("como aka");
            return false;
        }
        while (bot <= top){
            int mid = bot + (top - bot)/2;

            if ( a[mid] == num ){
                System.out.println("ahi va");
                return  true;
            }
            if (num < a[mid]){
                top = mid-1;
                System.out.println("Por ahora no...");
            }
            if (num > a[mid]){
                System.out.println("No hay caso che..");
                bot = mid+1;
            }

        }
        return false;
    }

}
