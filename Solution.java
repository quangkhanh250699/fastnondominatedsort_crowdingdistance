public class Solution{
    final int N_OBJ = 3 ; 
    final int SIZE_PO = 10; 

//---------------------------------------------

    double[] objects = new double[N_OBJ] ; 
    int domination_count ; 
    int mp ; 
    Solution[] set_dominate = new Solution[51];   
    double distance;
    int rank; 

//----------------------------------------------

    public Solution () {
        this.domination_count = 0;
        this.distance = 0.0;
        this.rank = 0;
        this.mp = 0;

        for(int i = 0; i < N_OBJ; ++i)
            this.objects[i] = 0.0;
    }

}