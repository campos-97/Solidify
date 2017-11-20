package tec.solidify.Math;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

/**
 * Created by josea on 10/31/2017.
 */

public class SolidCretor {

     private static SolidCretor  instance;

    protected SolidCretor() {
    }

    public static SolidCretor getInstance(){

        if (instance == null){
            instance = new SolidCretor();
        }
        return instance;
    }

    public void createSolidFromEq(){
        float[] v1 = {0,2};
        float[] v2 = {0,4};
        generateFace("","","",v1,v2);
    }

    private void generateFace(String x, String y, String Z, float[] r1, float[] r2){
        Vector3f[] mierda = new Vector3f[r1.length*r2.length];
        int i = 0;
        for (float n1 : r1){
            for(float n2 : r2){
                Vector3f NewVec = new Vector3f(n1,2-n1,n2);
                mierda[i] = NewVec;
                Log.d("mierdon", "Vector #"+i+" = "+NewVec.toString());
                i++;
            }
        }
    }
}
