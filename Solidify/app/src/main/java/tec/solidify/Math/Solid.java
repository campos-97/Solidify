package tec.solidify.Math;

import android.opengl.GLES20;

import java.nio.Buffer;
import java.nio.FloatBuffer;

/**
 * Created by josea on 10/19/2017.
 */

public class Solid extends MeshObject{

    public float[] vertices;
    public float[] texCoords;
    public float[] normals;
    public short[] indices;

    private FloatBuffer mVertBuff;
    private FloatBuffer mTexCoordBuff;
    private FloatBuffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;
    private int normalNumber = 0;
    private int textCoordsNumer = 0;

    public int mCubePositionsBufferIdx;
    public int mCubeNormalsBufferIdx;
    public int mCubeTexCoordsBufferIdx;

    public Solid(){}

    public void load(){
        mVertBuff = fillBuffer(vertices);
        verticesNumber = vertices.length / 3;

        mTexCoordBuff = fillBuffer(texCoords);
        textCoordsNumer = texCoords.length/2;

        mNormBuff = fillBuffer(normals);
        normalNumber = normals.length/3;

        mIndBuff = fillBuffer(indices);
        indicesNumber = indices.length;
    }

    public void loadVBO(){
        mVertBuff = fillBuffer(vertices);
        verticesNumber = vertices.length / 3;

        mTexCoordBuff = fillBuffer(texCoords);
        textCoordsNumer = texCoords.length/2;

        mNormBuff = fillBuffer(normals);
        normalNumber = normals.length/3;


        final int buffers[] = new int[3];
        GLES20.glGenBuffers(3, buffers, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, this.getVertices().capacity() * 4, this.getVertices(), GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, this.getNormals().capacity() * 4, this.getNormals(), GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, buffers[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, this.getTexCoords().capacity() * 4, this.getTexCoords(),
                GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        mCubePositionsBufferIdx = buffers[0];
        mCubeNormalsBufferIdx = buffers[1];
        mCubeTexCoordsBufferIdx = buffers[2];
    }

    public int getNumObjectIndex()
    {
        return indicesNumber;
    }

    @Override
    public int getNumObjectVertex()
    {
        return verticesNumber;
    }

    public int getNormalNumber(){return normalNumber;}

    public int getTextCoordsNumer(){return textCoordsNumer;}


    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;

        }

        return result;
    }
}