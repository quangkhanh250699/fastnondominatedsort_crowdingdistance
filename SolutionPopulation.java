import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Random;

public class SolutionPopulation{    
    final int SIZE_PO = 10; // Kích thước quần thể
    final int N_OBJ = 3;  // Số lượng object
    final double MAX_DISTANCE = 1000.0; 

    int nrank = 0 ;
    
//------------------------------------------------------------------------    
        
    Solution[] setsolution = new Solution[SIZE_PO];  // tập quần thể

//------------------------------------------------------------------------
// Khởi tạo quần thể
    public SolutionPopulation() {
        int size = this.SIZE_PO;
        int object_size = this.N_OBJ;

        Random ran = new Random();
        double ranval = 10.0;

        for(int i = 0; i < size; ++i)
            this.setsolution[i] = new Solution();

        for(int i = 0; i < size; ++i)
        for(int j = 0; j < object_size; ++j)
            this.setsolution[i].objects[j] = ran.nextDouble() * ranval;
    }

//--------------------------------------------------------------        

        public int dominatedCompare(Solution o1 , Solution o2) {
            Solution o3 = new Solution() ; 
            for(int i = 0 ; i < o1.N_OBJ ; i++){
                o3.objects[i] = o1.objects[i] - o2.objects[i] ;                                                                                                                                                     
            }
            for(int i = 0 , t = 0 ; i < o3.N_OBJ ; i++){
                if(o3.objects[i] >= 0) t++ ; 
                if(t == o3.N_OBJ) return 1 ;                                 
            }
            for(int i = 0 , t = 0 ; i < o3.N_OBJ ; i++){
                if(o3.objects[i] <= 0) t++ ; 
                if(t == o3.N_OBJ) return -1 ; 
            }                           
            return 0;
        }

        ArrayList<ArrayList<Solution>> rank = new ArrayList<ArrayList<Solution>>();

        /*
         * This is fast nondominated sort algorithm
         */
        public void FNDS(){                 
            for(int i = 1 ; i <= 100 ; i++){
                rank.add(new ArrayList<Solution>()); 
            }

            for(int i = 0 , t = 0 ; i < SIZE_PO ; i++){
                for(int j = 0 ; j < SIZE_PO ; j++){
                    if(i == j) continue ; 
                    if(dominatedCompare(setsolution[i], setsolution[j]) == 1){
                      setsolution[i].set_dominate[t] = setsolution[j] ;
                      t++ ;                                                    
                    }
                    else if(dominatedCompare(setsolution[i], setsolution[j]) == -1){
                        setsolution[i].domination_count++ ; 
                    }
                }
                if(setsolution[i].domination_count == 0){
                    setsolution[i].rank = 0 ; 
                    rank.get(0).add(setsolution[i]) ; 
                }
                setsolution[i].mp = t ;
                t = 0 ;
            }
            int i = 0; 
            System.out.println(rank.get(i).size()+"lala") ; 
            while(rank.get(i).size() != 0){
                for(int j = 0 ; j <rank.get(i).size() ; j++){
                    for(int k = 0 ; k < rank.get(i).get(j).mp ; k++){
                        rank.get(i).get(j).set_dominate[k].domination_count-- ; 
                        if(rank.get(i).get(j).set_dominate[k].domination_count == 0){
                            System.out.println(i) ; 
                            rank.get(i).get(j).set_dominate[k].rank = i+1 ; 
                            rank.get(i+1).add(rank.get(i).get(j).set_dominate[k]) ;  
                        }
                    }
                }
                i++ ;     
            }   
            this.nrank = i - 1 ;                                                                                                                                                                        
        }                               

///-----------------------------------------------------------------

    public class Sortbyobjects implements Comparator<Solution> {

        int index;

        public Sortbyobjects(int _index) {
            this.index = _index;
        }

        public int compare(Solution o1, Solution o2) {
            return Double.compare(o1.objects[index], o2.objects[index]);
        }
    }

    public void Crowding_distance() {
        int size = SIZE_PO;
        int object_size = N_OBJ;   
        
        for(int i = 0; i < object_size; ++i) {
            Arrays.sort(setsolution, new Sortbyobjects(i));

            setsolution[0].distance = MAX_DISTANCE;
            setsolution[size - 1].distance = MAX_DISTANCE;

            double min = setsolution[0].objects[i];
            double max = setsolution[size - 1].objects[i];                                                    

            for(int j = 1; j < size - 1; ++j)
                setsolution[j].distance += (setsolution[j+1].objects[i] - setsolution[j-1].objects[i]) / (max - min);                        
                                                                                    
        }                        
    }
}