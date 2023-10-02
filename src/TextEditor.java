

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener{
    // Declaring properties of TextEditor.

    JFrame frame;
    JMenuBar menuBar;
    JMenu file ,edit;

    // File menuItems
    JMenuItem newFile , openFile , SaveFile;

    // Edit menuItems
    JMenuItem cut , copy , paste , selectall , close;

    JTextArea TextArea;
    TextEditor(){
        // Initialise a Frame
          frame  = new JFrame();

          // Initialise a menuBar
          menuBar  = new JMenuBar();

          // Initialise a TextArea
          TextArea  = new JTextArea();

          // Initialise menus
          file = new JMenu("File");
          edit  = new JMenu("Edit");

          // Initialise File menu Item
          newFile  = new JMenuItem("New Window");
          openFile  = new JMenuItem("Open File");
          SaveFile  = new JMenuItem("Save File");

        // Add action Listener to file menu Items
          newFile.addActionListener(this);
          openFile.addActionListener(this);
          SaveFile.addActionListener(this);

          // Add menu Item to file menu
          file.add(newFile);
          file.add(openFile);
          file.add(SaveFile);

        // Initialise File menu Item
        cut  = new JMenuItem("Cut");
        copy  = new JMenuItem("Copy");
        paste  = new JMenuItem("Paste");
        selectall = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        // Add action Listener to Edit menu Items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        // Add menu Item to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

          // Add menus to menuBar
           menuBar.add(file);
           menuBar.add(edit);

           // Set menuBar to frame
            frame.setJMenuBar(menuBar);

            // Create content Pane
            JPanel panel  = new JPanel();
            panel.setBorder(new EmptyBorder(5  , 5 , 5 ,5));
            panel.setLayout(new BorderLayout(0 , 0));
            // Add area to panel
            panel.add(TextArea , BorderLayout.CENTER);
            // create scroll pane
            JScrollPane scrollPane  = new JScrollPane(TextArea , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            // Add ScrollPane to panel
            panel.add(scrollPane);
            // Add Panel to frame
            frame.add(panel);



          // Set Dimensions of a Frame
          frame.setBounds(700,100 , 600, 600);
          frame.setTitle("Text Editor");
          frame.setVisible(true);
          frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent  actionEvent){
         if(actionEvent.getSource()==cut){
             // perform cut Operation
             TextArea.cut();
         }
        if(actionEvent.getSource()==copy){
            // perform copy Operation
            TextArea.copy();
        }
        if(actionEvent.getSource()==paste){
            // perform paste Operation
            TextArea.paste();
        }
        if(actionEvent.getSource()==selectall){
            // perform selectAll Operation
            TextArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            // perform close Operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            // Open a file chooser
            JFileChooser fileChooser  = new JFileChooser("C:");
            int chooseOption  = fileChooser.showOpenDialog(null);
            // If we have clicked on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting Selected File
                File file  = fileChooser.getSelectedFile();
                // Get the path of Selected File
                String filepath  = file.getPath();

                try{
                    // Initialise File Reader
                    FileReader fileReader  = new FileReader(filepath);
                    // Initialise Buffered reader
                    BufferedReader bufferedReader  = new BufferedReader(fileReader);
                    String intermediate  = "", output = "";

                    // Read Content of File Line by Line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    // Set the output String to text Area
                    TextArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()== SaveFile){
            // Initialise File Picker
            JFileChooser  fileChooser  = new JFileChooser("C:");
            // get Choose option from fileChooser
            int chooseOption  = fileChooser.showSaveDialog(null);
            // Check if we clicked on Save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new File with Chosen Directory path and File name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".text");
                try{
                    // Initialise File writer
                    FileWriter fileWriter  = new FileWriter(file);
                    // Initialise Buffered writer
                    BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);
                    // Writing contents of text area to file
                    TextArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor textEditor  = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}