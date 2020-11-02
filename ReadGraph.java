//package graphimplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class ReadGraph {
    
    
    private File filename;
    private int vertices,edges;
     
    public ReadGraph(File filename){
        this.filename = filename;
    }
    /*read input file, creates vector with lists*/
    public Vector<myList> graphParser(File input){
        String line; 
        String[] parts, v;
        InputStream in;
        Vector<myList> vec =new Vector<>();
        
        myList l = new myList();
        try{
            int x,y;
            in = new FileInputStream(input);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            while((line = bf.readLine()) != null){
                if(line.startsWith("p",0)){
                    parts = line.split(" ");
                    vertices = Integer.parseInt(parts[2]);
                    edges = Integer.parseInt(parts[3]);
                    if(vec.isEmpty()){
                        for(int i=0;i<vertices;i++)
                            vec.add(new myList());//init vector
                    }
                } 
                
                for(int i=0;i<vertices;i++){
                    if(vec.elementAt(i).getHead() == null)
                         vec.elementAt(i).add(i+1);//insert vertex number at head
                }
                if(line.startsWith("e",0)){
                    v = line.split(" ");
                    x = Integer.parseInt(v[1]);
                    y = Integer.parseInt(v[2]);
                   
                    vec.elementAt(x-1).add(y); //insert connections between vertices
                    vec.elementAt(y-1).add(x);     
                }   
		if(!(line.startsWith("e",0) || line.startsWith("c",0) || line.startsWith("p",0))){
                    System.out.println("Wrong File Format");
                    System.exit(-1);
		}
					
            }
           
        }catch(Exception e){
            System.out.println(e);
        }
        return vec;
    }
   
    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

    public File getFilename() {
        return filename;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    public void setFilename(File filename) {
        this.filename = filename;
    }
}
