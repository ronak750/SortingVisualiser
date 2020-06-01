/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Apar
 */
public class Sorting extends JFrame{   
    
    int arraySize=50;
    int[] array=new int[arraySize];
    int x_min=50,y_min=50;
    int x_window=1000,y_window=800;
    int window=700;
    int width=window/arraySize,margin=2;
    int max_height=600;
    Color background_color=Color.decode("#eeeeee");
    Color initial_color=Color.yellow,
            highlighted_color=Color.red,
            secondary_color=Color.magenta,
            final_color=Color.blue,
            temp_color=Color.green;
    int delay=1;
    boolean sorted=false;
    
    Sorting(){
        setSize(x_window,y_window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Visulaisation of Soritng algorithms");
        for(int i=0;i<arraySize;i++)
        {
            array[i]=(int)(Math.random()*1000)%max_height;
//            array[i]=arraySize-i+1;
//            array[i]=i+1;
        }
        printArray();    
    }
    
    public void paint(Graphics g)
    {
//        super.paint(g);
        if(sorted)
            return;
        try {
            drawArray(g);
//            bubbleSort(g);
            insertionSort(g);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sorting.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }     
//        g.drawString("Insertion Sort", 300, 700);
    }

     void drawArray(Graphics g) throws InterruptedException
     {
         g.setColor(initial_color);
         if(sorted)
           g.setColor(final_color);
         for(int i=0;i<arraySize;i++)
         {
             g.fillRect(x_min+i*width, y_min, width-2, array[i]);
         }

     }

     
    void insertionSort(Graphics g) throws InterruptedException
    {
        System.out.print("Insertion sort");
        for(int i=0;i<arraySize;i++)
        {
            int key=array[i];
            g.setColor(highlighted_color);
            g.fillRect(x_min+i*width, y_min, width-margin, array[i]);          
            int j=i-1;          
            while(j>=0 && array[j]>key)
            {
                g.setColor(secondary_color);
                g.fillRect(x_min+j*width, y_min, width-margin, array[j]);
                TimeUnit.MILLISECONDS.sleep(delay);
                g.setColor(initial_color);
                g.fillRect(x_min+j*width, y_min, width-margin, array[j]);
                array[j+1]=array[j];
                j--;               
            }
            if(j+1==i);
            else{
                g.setColor(temp_color);
                g.fillRect(x_min+(j+1)*width, y_min, width-margin, array[j+1]);
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            array[j+1]=key;
            for(int k=j+1;k<=i;k++)
            {
                resetRectangle(k,g);
                g.setColor(initial_color);
                g.fillRect(x_min+(k)*width, y_min, width-margin, array[k]);
            }
        }
        sorted=true;       
        drawArray(g);
        printArray();
        drawArray(g);
    }
     
    void bubbleSort(Graphics g) throws InterruptedException
    {
        System.out.print("bubble sort");
        for(int i=0;i<arraySize;i++)
        {
            for(int j=0;j<arraySize-i-1;j++)
            {
                g.setColor(highlighted_color);
                g.fillRect(x_min+j*width, y_min, width-margin, array[j]);
                g.setColor(secondary_color);
                g.fillRect(x_min+(j+1)*width, y_min, width-margin, array[j+1]);
                
                TimeUnit.MILLISECONDS.sleep(delay/2);          
                if(array[j]>array[j+1])
                {
                    swap(j,j+1,g);
                    TimeUnit.MILLISECONDS.sleep(delay/2);
                }             
//                else {
                    g.setColor(initial_color);
                    g.fillRect(x_min+j*width, y_min, width-margin, array[j]);
                    g.fillRect(x_min+(j+1)*width, y_min, width-margin, array[j+1]);
//                }
            }
            g.setColor(final_color);
            g.fillRect(x_min+(arraySize-i-1)*width, y_min, width-margin, array[arraySize-i-1]);
        }
        sorted=true;
        drawArray(g);
    }        
     
    void swap(int j,int k,Graphics g)
    {
        resetRectangle(j,k,g);
        int temp=array[k];
        array[k]=array[j];
        array[j]=temp;
        g.setColor(secondary_color);
        g.fillRect(x_min+j*width, y_min, width-margin, array[j]);
        g.setColor(highlighted_color);
        g.fillRect(x_min+(k)*width, y_min, width-margin, array[k]);
    }
     
    public static void main(String[] args) {
        new Sorting().setVisible(true);
    }
    
    void printArray()
    {
        for(int i=0;i<arraySize;i++)
            System.out.print(array[i]+" ");
        System.out.println("END");
    }
    void resetRectangle(int i,int j, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height);
        g.fillRect(x_min+(j)*width, y_min, width-margin, max_height);
    }
    void resetRectangle(int i, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height);
    }
}
