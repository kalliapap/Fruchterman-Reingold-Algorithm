//package graphimplementation;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class GraphImplementation {

    public static void main(String[] args) {
        
        File inFile;
		//if input file is given from command line
        if (0 < args.length) {
           System.out.println("Reading file from command line...");
           inFile = new File(args[0]);
        } else { //else give input file from stdin
            System.out.println("Please give filename: ");
            Scanner scan = new Scanner(System.in);
            String in = scan.nextLine();
            inFile = new File(in);
            System.out.println("Reading file...");   
        }
        ReadGraph p = new ReadGraph(inFile);
        Vector<myList> vec;
        vec = p.graphParser(inFile);
        System.out.println("Main: "+vec+" with vec size "+vec.size()+"\n.........");
       
        int W = 1280, H = 720;
        FruchtermanReingold fr = new FruchtermanReingold(vec.size(),W,H);
        fr.FRalgorithm(vec);
        
    }
    
}