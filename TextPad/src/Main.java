import java.awt.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
//editor class for do all the text editor function,jframe for project frame,implements actionlisterner for track the user,
class editor extends JFrame implements ActionListener{
    JFrame f;//for whole window
    JTextArea t;//for text area

    editor(){//constructor
        //initializing the frame with title
        f=new JFrame("Textpad");//for title Textpad

        //setting the overall theme of the application
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//importing theme
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());//applying theme
        }catch (Exception e){//if there is any error in try then run this,handle by catch,not shown error

        }
        //create the text component
        t=new JTextArea();

        //create the menu bar
        JMenuBar menu=new JMenuBar();

        //creating menu file
        JMenu file=new JMenu("File");

        //creating menu items
        JMenuItem New=new JMenuItem("New");
        JMenuItem open=new JMenuItem("Open");
        JMenuItem save=new JMenuItem("Save");
        JMenuItem print=new JMenuItem("Print");

        //adding actionListener->when item is click action listener will triggered and it goes to action performed fun
        New.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        //adding items to file
        file.add(New);
        file.add(open);
        file.add(save);
        file.add(print);

        //creating edit menu
        JMenu edit=new JMenu("Edit");
        //creating menu items
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem paste=new JMenuItem("Paste");

        //adding actionListener->when item is click action listener will triggered and it goes to action performed fun
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);

        //adding items to edit
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(this);

        //adding the menus to the menubar
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        //adding the menubar and the textarea to the frame
        f.setJMenuBar(menu);
        f.add(t);//text area added to the frame
        //setting the size of window
        f.setSize(800,400);
        f.show();

    }

    //function for adding the functinalities to each of the menu items or buttons
    public void actionPerformed(ActionEvent ae){
        //Extracting the button pressed
        String str=ae.getActionCommand();
        if(str.equals("New")){
            t.setText("");//set the text area to the blank string,override with blank string
        }
        else if(str.equals("Open")){
            //initializing the jfile chooser in desired directory,open first in E drive
            JFileChooser j=new JFileChooser("e:");//which file user want to open

            //invoking the open dialog box with an integer
            int r=j.showOpenDialog(null);//like ok,cancel user what selects,like 1 is ok,0 is cancel

            //if user is click on some file after open
            if(r==JFileChooser.APPROVE_OPTION){
                //set the label to the path of the selected file location
                File fi=new File(j.getSelectedFile().getAbsolutePath());//get whole path for file
                //string to copy the data from the chosen file
                try {
                    String s1 = "", s2 = "";

                    //for read a file
                    FileReader fr = new FileReader(fi);//fr variable contains all data in a file

                    BufferedReader br = new BufferedReader(fr);//reading char by char,buffer one by one char and stores it

                    s2 = br.readLine();//reading line by line

                    //till the file line not ends
                    while((s1=br.readLine())!=null){
                        s2 = s2+ '\n' + s1;//adding all the line one by one and store in s2
                    }
                    //setting the textarea to s2
                    t.setText(s2);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(f,ex.getMessage());//what error found shows in the frame
                }


            }
        }
        else if(str.equals("Save")){
            //initializing the jfile chooser in desired directory,save first in E drive
            JFileChooser j=new JFileChooser("e:");
            //invoking the save dialog box with an integer
            int r= j.showSaveDialog(null);
            //if user is click on some file after open
            if(r==JFileChooser.APPROVE_OPTION){//if ok
                //set the label to the path of the selected file location
                File fi=new File(j.getSelectedFile().getAbsolutePath());//get whole path for file
                try{
                    FileWriter fw=new FileWriter(fi,false);
                    BufferedWriter bw=new BufferedWriter(fw);//writing char by char,buffer one by one char and stores it
                    bw.write(t.getText());//getting the text char by char and write in fw
                    //after writing is complete flush the stream
                    bw.flush();//bw is empty now
                    bw.close();//bw is close now
                }catch (Exception e){
                    JOptionPane.showMessageDialog(f,e.getMessage());//for error show msg in frame
                }
            }

        }
        else if(str.equals("Print")){
            try {
                t.print();
            } catch (PrinterException e) {
                throw new RuntimeException(e);
            }
        }
        else if(str.equals("Copy")){
            t.copy();//for copy text inbuilt function,in swing.text header file
        }
        else if(str.equals("Cut")){
            t.cut();//for cut text  inbuilt function,in swing.text header file
        }
        else if(str.equals("Paste")){
            t.paste();//for for paste inbuilt function,in swing.text header file
        }
        else if(str.equals("Close")){
            f.setVisible(false);//for close the window
        }


    }

    public static void main(String[] args) {
        editor e=new editor();
    }
}
