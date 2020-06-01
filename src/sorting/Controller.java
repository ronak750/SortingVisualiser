/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Apar
 */
public class Controller extends javax.swing.JFrame {

    /**
     * Creates new form Controller
     */
    
    int arraySize=50;
    int[] array=new int[arraySize];
    int x_min=50,y_min=50;
    int x_window=1000,y_window=800;
    int window=700;
    int width=window/arraySize,margin=2;
    int max_height=600;
    Color background_color=Color.decode("#d6d9e0");
    Color initial_color=Color.yellow,
            highlighted_color=Color.red,
            secondary_color=Color.magenta,
            final_color=Color.blue,
            temp_color=Color.green;
    int delay=100;
    boolean sorted=false;
    Graphics graphics;
    
    public Controller() {
        initComponents();
               setSize(x_window,y_window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Visulaisation of Soritng algorithms");
        for(int i=0;i<arraySize;i++)
        {
            array[i]=(int)(Math.random()*1000)%max_height+5;
//            array[i]=arraySize-i+1;
//            array[i]=i+1;
        }
        printArray();    
    }

    

    
    public void paint(Graphics g)
    {
        graphics=g;
        if(sorted)
            return;
        super.paint(g);
        try {
            drawArray(g);
//            bubbleSort(g);
//            insertionSort(g);
//            mergeSort(g);
            quickSort(g);
        } catch (InterruptedException ex) {
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

    void bubbleSort(Graphics g) throws InterruptedException
    {
        System.out.print("bubble sort");
        for(int i=0;i<arraySize;i++)
        {
            for(int j=0;j<arraySize-i-1;j++)
            {
                drawRectangle(j,highlighted_color);
                drawRectangle(j+1,secondary_color);                
                TimeUnit.MILLISECONDS.sleep(delay/2);          
                if(array[j]>array[j+1])
                {
                    swap(j,j+1,g);
                    TimeUnit.MILLISECONDS.sleep(delay/2);
                }             
//                else {
                    drawRectangle(j,initial_color);
                    drawRectangle(j+1,initial_color);
//                }
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
        drawRectangle(j,secondary_color);
        drawRectangle(k,highlighted_color);
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
    
    int visited[]=new int[arraySize];
    void quickSort(Graphics g) throws InterruptedException
    {
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
                Thread.sleep(delay/2);
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
  
                // swap arr[i] and arr[j] 
                drawRectangle(i,secondary_color);
                drawRectangle(j,secondary_color);
                Thread.sleep(delay/2);
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
                resetAndRedraw(i);
                resetAndRedraw(j);
            } 
        }  
        // swap arr[i+1] and arr[high] (or pivot) 
        drawRectangle(i+1,temp_color);
        Thread.sleep(delay/2);
        int temp = array[i+1]; 
        array[i+1] = array[high]; 
        array[high] = temp; 
        resetAndRedraw(high);
        reDraw(i+1,final_color);
        visited[i+1]=1;
        return i+1; 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 984, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 894, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Controller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Controller().setVisible(true);
//                new Sorting().setVisible(true);
            }
        });
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
    void resetRectangle(int i,int j, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        g.fillRect(x_min+(j)*width, y_min, width-margin, max_height+5);
    }
    void resetRectangle(int i,int j)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
        graphics.fillRect(x_min+(j)*width, y_min, width-margin, max_height+5);
    }
    void resetRectangle(int i)
    {
        graphics.setColor(background_color);
        graphics.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);       
    }
    void resetRectangle(int i, Graphics g)
    {
        g.setColor(background_color);
        g.fillRect(x_min+(i)*width, y_min, width-margin, max_height+5);
    }
    void resetAndRedraw(int i)
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
