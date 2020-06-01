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
    int delay=50;
    boolean sorted=false;
    Graphics graphics;
    
    public Controller() {
        initComponents();
        setSize(x_window,y_window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Visulaisation of Soritng algorithms");
        generateArray(); 
    }

    

    
    public void paint(Graphics g)
    {
        graphics=this.getGraphics();
        System.out.println("a");
        if(sorted)
            return;
//        for(int i=0;i<arraySize;i++)
//            resetRectangle(i);
            super.paint(g);
        try {
            drawArray(graphics);
//            bubbleSort(g);
//            insertionSort(g);
//            mergeSort(g);
//            quickSort(g);
//            heapSort(g);
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
        graphics=this.graphics;
        System.out.print("bubble sort");
        for(int i=0;i<arraySize;i++)
        {
            for(int j=0;j<arraySize-i-1;j++)
            {
//                drawRectangle(j,highlighted_color);
//                drawRectangle(j+1,secondary_color);                
//                TimeUnit.MILLISECONDS.sleep(delay);          
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
//        drawRectangle(j,secondary_color);
//        drawRectangle(k,highlighted_color);
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
  
                // swap arr[i] and arr[j] 
                drawRectangle(i,secondary_color);
                drawRectangle(j,secondary_color);
                Thread.sleep(delay);
                int temp = array[i]; 
                array[i] = array[j]; 
                array[j] = temp; 
                resetAndRedraw(i);
                resetAndRedraw(j);
            } 
        }  
        // swap arr[i+1] and arr[high] (or pivot) 
        drawRectangle(i+1,temp_color);
        Thread.sleep(delay);
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Bubble Sort");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Insertion Sort");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Merge Sort");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Heap Sort");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Quick Sort");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSlider1.setMaximum(60);
        jSlider1.setMinimum(5);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Array Size");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 938, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        for(int i=0;i<arraySize;i++)
            resetRectangle(i);
        sorted=false;
        generateArray();
        try {
            drawArray(this.getGraphics());

        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            bubbleSort(this.getGraphics());
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            insertionSort(this.getGraphics());
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            mergeSort(this.getGraphics());
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
            heapSort(this.getGraphics());
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     try {
            quickSort(this.getGraphics());
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }         // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        for(int i=0;i<arraySize;i++)
            resetRectangle(i);
        arraySize=jSlider1.getValue();
        array=new int[arraySize];
        width=window/arraySize;
        jButton4.doClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider1StateChanged

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
