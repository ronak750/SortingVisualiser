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
 * @author ronak
 */
public class Sorting extends JFrame{   
    
    private int arraySize=50;
    private int[] array=new int[arraySize];
    private int x_min=50,y_min=50;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     private  int x_window = (int) screenSize.getWidth()-100;
    private int y_window = (int) screenSize.getHeight()-100;
    private int window=x_window*85/100;
    private int width=window/arraySize,margin=2;
    private int max_height=y_window*75/100;
    private Color background_color=Color.decode("#d6d9e0");
    private Color initial_color=Color.yellow,
            highlighted_color=Color.red,
            secondary_color=Color.magenta,
            final_color=Color.blue,
            temp_color=Color.green;
    private int delay=50;
    private boolean sorted=false;
    Graphics graphics;
    
    Sorting(Graphics g,int arrSize){
        setSize(x_window,y_window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Visulaisation of Soritng algorithms");
        arraySize=arrSize;
        array=new int[arraySize];
        width=window/arraySize;
        graphics=g;
        
        System.out.println("hey there");
        generateArray();   
        resetBoard();
        try {
            drawArray(g);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
    
//    public void paint(Graphics g)
//    {
////        super.paint(g);
//        if(sorted)
//            return;
//        try {
//            drawArray(g);
////            bubbleSort(g);
//            insertionSort(g);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Sorting.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex);
//        }     
////        g.drawString("Insertion Sort", 300, 700);
//    }

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
            drawRectangle(i,highlighted_color);        
            int j=i-1;          
            while(j>=0 && array[j]>key)
            {
                drawRectangle(j,secondary_color); 
                TimeUnit.MILLISECONDS.sleep(delay);
                drawRectangle(j,initial_color); 
                array[j+1]=array[j];
                j--;               
            }
            if(j+1==i);
            else{
                drawRectangle(j+1,temp_color);
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            array[j+1]=key;
            for(int k=j+1;k<=i;k++)
            {
                resetRectangle(k,g);
//                g.setColor(initial_color);
                drawRectangle(k,initial_color);
//                g.fillRect(x_min+(k)*width, y_min, width-margin, array[k]);
            }
        }
        sorted=true;       
        drawArray(g);
        printArray();
        drawArray(g);
    }
    
     
    void bubbleSort(Graphics g) throws InterruptedException
    {
        graphics=this.graphics;
        System.out.print("bubble sort");
        for(int i=0;i<arraySize;i++)
        {
            for(int j=0;j<arraySize-i-1;j++)
            {
                if(array[j]>array[j+1])
                {
                    swap(j,j+1,g);
                    drawRectangle(j,highlighted_color);
                    drawRectangle(j+1,secondary_color); 
                }             
                else {
                    drawRectangle(j,highlighted_color);
                    drawRectangle(j+1,temp_color);

                }
                    TimeUnit.MILLISECONDS.sleep(delay);
                    drawRectangle(j,initial_color);
                    drawRectangle(j+1,initial_color);
            }       
            
            drawRectangle(arraySize-i-1,final_color);
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
    }
    
    void mergeSort(Graphics g) throws InterruptedException
    {
        System.out.print("Merge sort");
        MSort(0,arraySize-1);
        printArray();
        sorted=true;
        drawArray(g);
        
    }
    void MSort(int start,int end) throws InterruptedException
    {
        if(start<end)
        {
            int mid = (start+end)/2; 
            MSort(start,mid);
            MSort(mid+1,end);
            
            merge(start,mid,end);
        }
    }
    

    void merge( int l, int m, int r) throws InterruptedException 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = array[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = array[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        {     
//            Thread.sleep(3);
            drawRectangle(l+i,secondary_color);
            drawRectangle(l+j+n1,secondary_color);
            resetRectangle(k);
            graphics.setColor(highlighted_color);
            if(L[i]<=R[j]){               
                graphics.fillRect(x_min+k*width, y_min, width-margin, L[i]);
            }
            else{
                graphics.fillRect(x_min+k*width, y_min, width-margin, R[j]);
            }
//            drawRectangle(k,highlighted_color);
            TimeUnit.MILLISECONDS.sleep(delay*2);
            resetRectangle(l+i,m+j);
            resetAndRedraw(k);
            drawRectangle(l+i,initial_color);
            drawRectangle(m+j,initial_color);  
            if (L[i] <= R[j]) 
            { 
                array[k] = L[i]; 
                i++; 
                
            } 
            else
            { 
                array[k] = R[j]; 
                j++; 
            } 
            k++; 

            for(int co=l;co<=r;co++)
                resetAndRedraw(co);
            
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            array[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            array[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
    
    int visited[];
    void quickSort(Graphics g) throws InterruptedException
    {
        visited=new int[arraySize];
        System.out.println("Quiick Sort");
        for(int i=0;i<arraySize;i++)
            visited[i]=0;
        QSort(0,arraySize-1);
        sorted=true;
        printArray();
//        drawArray(g);
        
    }
    void QSort(int low,int high) throws InterruptedException
    {
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition( low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
//            reDraw(pi,final_color);

//            TimeUnit.MILLISECONDS.sleep(delay);
//            drawRectangle(pi-1,highlighted_color);
            QSort(low, pi-1); 
//            reDraw(pi-1,initial_color);
//            drawRectangle(high,highlighted_color);
            QSort(pi+1, high); 
//            reDraw(high,initial_color);
        } 
        else if(low==high)
        {
            if(visited[low]==0)
            {
                reDraw(low,final_color);
//                Thread.sleep(delay);
                visited[low]=1;
            }
        }
    }
    int partition(int low, int high) throws InterruptedException
    {
        int pivot = array[high];  
        drawRectangle(high,highlighted_color);
//        TimeUnit.MILLISECONDS.sleep(delay);
//        resetAndRedraw(high);
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (array[j] < pivot) 
            { 
                i++;  
                drawRectangle(i,temp_color);
                drawRectangle(j,secondary_color);
                // swap arr[i] and arr[j] 
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp;  
                Thread.sleep(delay);
                resetAndRedraw(i);
                resetAndRedraw(j);
            } 
 
        }  
        drawRectangle(i+1,temp_color);
        Thread.sleep(delay);
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = array[i+1]; 
        array[i+1] = array[high]; 
        array[high] = temp; 
        
        resetAndRedraw(high);
        reDraw(i+1,final_color);
        visited[i+1]=1;
        return i+1; 
    }
    
    void heapSort(Graphics g) throws InterruptedException
    {
        System.out.println("Heap Sort");
        HSort();
        sorted=true;
        printArray();
    }
    public void HSort() throws InterruptedException 
    { 
        int n = arraySize; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify( n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
//            reDraw(0,highlighted_color);
//            Thread.sleep(delay/2);
            int temp = array[0]; 
            array[0] = array[i]; 
            array[i] = temp; 
            
            reDraw(i,final_color);
//            Thread.sleep(delay/2);
            // call max heapify on the reduced heap 
            heapify( i, 0); 
        } 
        reDraw(0,final_color);
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify( int n, int i) throws InterruptedException 
    { 
 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && array[l] > array[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && array[r] > array[largest]) 
            largest = r; 
        
        if(largest==i)
        {
            reDraw(largest,secondary_color);
            Thread.sleep(delay);
            reDraw(i,initial_color);
        }
        // If largest is not root 

        if (largest != i) 
        { 
            int swap = array[i]; 
            array[i] = array[largest]; 
            array[largest] = swap; 

            reDraw(i,secondary_color);
            reDraw(largest,highlighted_color);
            Thread.sleep(delay);
            reDraw(i,initial_color);
            reDraw(largest,initial_color);
            // Recursively heapify the affected sub-tree 
            heapify( n, largest); 
        } 

    } 
    
//    public static void main(String[] args) {
//        new Sorting().setVisible(true);
//    }
    void changeDelay(int newTime)
    {
        delay=newTime;
    }
    
    void resizeArray(int newSize)
    {
        for(int i=0;i<arraySize;i++)
            resetRectangle(i);
        arraySize=newSize;
        array=new int[arraySize];
        width=window/arraySize;
        resetBoard();
     
    }
    
    void resetBoard()
    {
        for(int i=0;i<arraySize;i++)
            resetRectangle(i);
        sorted=false;
        generateArray();
        try {
            drawArray(graphics);

        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void generateArray()
    {
        for(int i=0;i<arraySize;i++)
        {
            array[i]=(int)(Math.random()*1000)%max_height+5;
//            array[i]=arraySize-i+1;
//            array[i]=(i+1)*2;
        }
        printArray();
    }
    void printArray()
    {
        for(int i=0;i<arraySize;i++)
            System.out.print(array[i]+" ");
        System.out.println("END");
    }
    void drawRectangle(int index, Color color)
    {
        graphics.setColor(color);
        graphics.fillRect(x_min+index*width, y_min, width-margin, array[index]);
    }       
    private void resetRectangle(int i,int j, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        g.fillRect(x_min+(j)*width, y_min, width-margin, max_height+5);
    }
    private  void resetRectangle(int i,int j)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        graphics.fillRect(x_min+(j)*width, y_min, width-margin, max_height+5);
    }
    private void resetRectangle(int i)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);       
    }
    private void resetRectangle(int i, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
    }
    private void resetAndRedraw(int i)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        graphics.setColor(initial_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, array[i]);
    }
    void reDraw(int i,Color color)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        graphics.setColor(color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, array[i]);
    }
}
