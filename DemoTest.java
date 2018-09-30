import java.util.Random;
import java.lang.NullPointerException;

public class DemoTest{
    final int SIZE_PO = 10;
    final int N_OBJ = 3 ; 

    SolutionPopulation population = new SolutionPopulation();

    public static void main(String[] args)  {

        DemoTest demo = new DemoTest();
        //demo.population.initialize();

        demo.population.FNDS() ; 

        System.out.println(demo.population.nrank) ; 
        
        for(int i = 0 ; i < demo.population.nrank+1 ; i++){
            System.out.println(i) ;

            for(int j = 0 ; j < demo.population.rank.get(i).size() ; j++){ 
                for(int k = 0 ; k < 3 ; k++) System.out.print(demo.population.rank.get(i).get(j).objects[k]+"   "); 
                System.out.println(' ') ; 
            }
        }
    
            
        demo.population.Crowding_distance();

        for(int i = 0; i < demo.SIZE_PO; ++i)
        System.out.println("Object " + i + " distance:" + demo.population.setsolution[i].distance);

    }
}