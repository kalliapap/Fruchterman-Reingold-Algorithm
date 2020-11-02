//package graphimplementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Math.sqrt;
import java.util.Random;
import java.util.Vector;

public class FruchtermanReingold {
    private double k;
    private int n;
    //private double area;
    private int W ;
    private int H ;
    
     Vector<Point> pos = new Vector<>();
     Vector<Point> disp = new Vector<>();

    public FruchtermanReingold() {}
    public FruchtermanReingold(int n, int W, int H) {
        this.W = W;
        this.H = H;
        this.k = 0.5 * sqrt((W*H)/n);
        this.n = n;   
    }

    public void initVec(){
        Random rand = new Random();
        for(int i=0;i<this.n;i++){
            disp.add(new Point());
            pos.add(new Point());
            pos.elementAt(i).setX(rand.nextDouble()*1280);
            pos.elementAt(i).setY(rand.nextDouble()*720);
        }        
    }
    public void FRalgorithm(Vector<myList> vec){
        initVec();
        Point delta;
        double deltaLen, T;
        int iterations =3*n;
        for(int i=0;i<iterations;i++){           
            Node v,u;
            //repulsive forces
            for(int j=0;j<vec.size();j++){//vertex v
		v = vec.elementAt(j).getHead();
		disp.elementAt(j).setX(0);
		disp.elementAt(j).setY(0);
                for(int k=0;k<vec.size();k++){//vertex u
                    u = vec.elementAt(k).getHead();
                    if(v.getNode() == u.getNode()) continue;
                    delta = distance(pos.elementAt(j),pos.elementAt(k));
                    deltaLen = dist(delta);
                   /*
                    System.out.println();
                    System.out.println("v="+v.getNode()+" u="+u.getNode());
                    System.out.println("delta= "+delta.getX()+", "+delta.getY());
                    System.out.println("deltaLen= "+deltaLen);
                    */
                    disp.elementAt(j).setX(disp.elementAt(j).getX() + delta.getX()*repulsiveForce(deltaLen)/deltaLen);
                    disp.elementAt(j).setY(disp.elementAt(j).getY() + delta.getY()*repulsiveForce(deltaLen)/deltaLen);  
                    /*
                    System.out.println("repuls: "+repulsiveForce(deltaLen));
                    System.out.println("dispx: "+disp.elementAt(j).getX()+", dispy: "+disp.elementAt(j).getY());
                    */ 
                }     
            }           
           
            //attractive forces
            for(int j=0;j<vec.size();j++){
                v = vec.elementAt(j).getHead();
                u = v.getNext();
                int uIndex=0;
                while(u != null){
                    for(int x=0;x<vec.size();x++){
                        if(vec.elementAt(x).getHead().getNode() == u.getNode()){
                            uIndex = x;
                        } 
                    }
                   // if(v.getNode() < u.getNode()){
                        delta = distance(pos.elementAt(j),pos.elementAt(uIndex));
                        deltaLen = dist(delta);
                        /*
                        System.out.println();
                        System.out.println("v="+v.getNode()+" u="+u.getNode());
                        System.out.println("delta= "+delta.getX()+", "+delta.getY());
                        System.out.println("deltaLen= "+deltaLen);
                        System.out.println("v,disp Prin="+disp.elementAt(j).getX()+", dispy: "+disp.elementAt(j).getY());
                        System.out.println("u,disp Prin: "+disp.elementAt(uIndex).getX()+", dispy: "+disp.elementAt(uIndex).getY());
                        */
                        disp.elementAt(j).setX(disp.elementAt(j).getX() - delta.getX()*attractiveForce(deltaLen)/deltaLen);
                        disp.elementAt(j).setY(disp.elementAt(j).getY() - delta.getY()*attractiveForce(deltaLen)/deltaLen); 
                        disp.elementAt(uIndex).setX(disp.elementAt(uIndex).getX() + delta.getX()*attractiveForce(deltaLen)/deltaLen);
                        disp.elementAt(uIndex).setY(disp.elementAt(uIndex).getY() + delta.getY()*attractiveForce(deltaLen)/deltaLen);  
                        /*
                        System.out.println("attract: "+attractiveForce(deltaLen));
                        System.out.println("v,dispx: "+disp.elementAt(j).getX()+", dispy: "+disp.elementAt(j).getY());
                        System.out.println("u,dispx: "+disp.elementAt(uIndex).getX()+", dispy: "+disp.elementAt(uIndex).getY());
						*/
                  //  }
                    u = u.getNext();
                }
                
            }
			//calculate Temperature
			T = -(Math.min(H,W)/(10*iterations))*i + (Math.min(H,W)/10);
            for(int j=0;j<vec.size();j++){
                disp.elementAt(j).setX(Math.max(Math.min(disp.elementAt(j).getX(), T), -T));
                disp.elementAt(j).setY(Math.max(Math.min(disp.elementAt(j).getY(), T), -T));
                
                pos.elementAt(j).setX(pos.elementAt(j).getX() + disp.elementAt(j).getX());
                pos.elementAt(j).setY(pos.elementAt(j).getY() + disp.elementAt(j).getY());
                
                pos.elementAt(j).setX(Math.min(W, Math.max(0, pos.elementAt(j).getX())));
                pos.elementAt(j).setY(Math.min(H, Math.max(0, pos.elementAt(j).getY())));
            }          
        }
        
    //write results to file
    try{
            File out = new File("result.dot");
            FileOutputStream fos = new FileOutputStream(out);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            int x;
            bw.write("graph G {");
            bw.newLine();
            bw.write("\tgraph [bb=\"0,0,"+W+","+H+"\"];");
            bw.newLine();
            bw.write("\tnode [label=\"\\N\"];");
            bw.newLine();
            for(int i=0;i<vec.size();i++){
                x=i+1;
                bw.write("\t" + x +" [height=0.5,width=0.5,pos=\"" + String.format("%.2f", pos.elementAt(i).getX()) + "," + String.format("%.2f", pos.elementAt(i).getY()) +"!\"]");
                bw.newLine();
            }
            for(int i=0;i<vec.size();i++){
                if(vec.elementAt(i).getHead() == null) continue;
                int j = i+1;
                Node h = vec.elementAt(i).getHead(), node = h.getNext();
                while(node != null){
                    if(node.getNode() <= h.getNode()){node=node.getNext(); continue;}
                    bw.write("\t" + h.getNode() + " -- " + node.getNode() + ";");
                    bw.newLine();
                    node = node.getNext();     
                }
            }
            bw.write("}");
            bw.close();
            System.out.println("Results are in file \"results.dot\".");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public double attractiveForce(double deltaLen){
        return (deltaLen*deltaLen)/k;
    }
    
    public double repulsiveForce(double deltaLen){
        return k*k/deltaLen;
    }
    
    public Point distance(Point v, Point u){
        Point p = new Point();
        p.setX(v.getX()-u.getX());
        p.setY(v.getY()-u.getY());
        return p;
    }
    
    public double dist(Point delta){
        return sqrt((delta.getX()*delta.getX()) + (delta.getY()*delta.getY()));
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getW() {
        return W;
    }

    public void setW(int W) {
        this.W = W;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }
    
    
}
