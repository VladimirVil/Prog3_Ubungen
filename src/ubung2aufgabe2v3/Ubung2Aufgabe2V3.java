/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package trainings;

//import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author Vova-PC
 */
public class Ubung2Aufgabe2V3 extends Application {
    
    Stage window;
    private String currentText, searchedText;
    private final int TEXTSIZE = 1024;
    
    private GridPane grid;
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private Label inputLabel;
    private TextField searched;
    private Button searchButton;
    private Label addLabel;
    private TextField addInput;
    private TextArea freeText;
    private Button execButton;
    private int loopKey=0;
    //private static int wordAppearanceCounter = 0;
    private boolean firstPaint = true;
    //firstrun is currently not used
    private boolean firstRun = false;
    // array, to contain all the places of the start of a match in the txt area to a searched word 
    private boolean[] indexOfMatches= new boolean[TEXTSIZE];
    boolean[] indexOfExchanges = new boolean[TEXTSIZE];
    // index, to use to indicate which parts of the text to exchange 
    private int index = 0; 
    // used to see if the searched word was recently changed, after the indexes of apearance already filled 
    private String currentSearchedWord = "";
    // shift keeps the change in index, caused by different length of searched word, and new word 
    private int shift = 0;
    
    //edit menu buttons implementing dialog 
    //private Alert search;
    // Dialogue
    TextInputDialog searchedWord;
    TextInputDialog exchangeWord;
    
    //Button to close resize the window to the starting size 
    private Button closeSearchFields;
    
    // Input from Edit-> search
    String testContentFromDialogue;
    
    //Input from edit->exchange
    String testContentFromDialogExchange;
    
    //Radio buttons
    
   /* RadioButton caseSensitive = new RadioButton("A");
    RadioButton caseNotSensitive = new RadioButton("B"); */
    
    
    
    // Group for Radio buttons
    
    //ButtonGroup groupB = new ButtonGroup();
    

    public void setTestContentFromDialogExchange(String testContentFromDialogExchange) {
        this.testContentFromDialogExchange = testContentFromDialogExchange;
    }

    public String getTestContentFromDialogExchange() {
        return testContentFromDialogExchange;
    }
    
    // inex of currently painted element, that is used in the exchange methode 
    private int currentlyPainted = 0;

    public void setTestContentFromDialogue(String testContentFromDialogue) {
        this.testContentFromDialogue = testContentFromDialogue;
    }

    public String getTestContentFromDialogue() {
        return testContentFromDialogue;
    }
    
    public String getCurrentText()
    {
        return currentText;
    }
    
    public String getsearchedText()
    {
        return searchedText;
    }
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Text Editor - JavaFX");
        
        grid = new GridPane();
        grid.setPadding(new Insets(0, 0, 0, 0));
        
        //grid.setVgap(8);
        //grid.setHgap(10);
        grid.getColumnConstraints().add(new ColumnConstraints(120));
        grid.getColumnConstraints().add(new ColumnConstraints(50));
        grid.gridLinesVisibleProperty();
        
        
        
        //Menu
        
        menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
      //  root.setTop(menuBar);
            // File menu - new, save, exit
            
        GridPane.setConstraints(menuBar, 0, 0, 5, 4);
        fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("Datei neu");
        MenuItem openMenuItem = new MenuItem("Datei öffnen");
        MenuItem saveMenuItem = new MenuItem("Datei speichern unter");
        MenuItem exitMenuItem = new MenuItem("Programm beenden");
        
        editMenu = new Menu("Edit");
        MenuItem searchItem = new MenuItem("Search");
        MenuItem exchangeItem = new MenuItem("Exchange");
        MenuItem restartItem = new MenuItem("Restart(out of service)");
        // Added 02.06 10:35
        CheckMenuItem caseSensitiveCheck = new CheckMenuItem("Case Sensitive");
        caseSensitiveCheck.setSelected(true);
        
        
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem,
        new SeparatorMenuItem(), exitMenuItem);
        editMenu.getItems().addAll(searchItem,exchangeItem,caseSensitiveCheck);
        menuBar.getMenus().addAll(fileMenu,editMenu);
         
       searchedWord = new TextInputDialog();
       searchedWord.setTitle("Text Input Dialog");
       searchedWord.setHeaderText("Search");
       searchedWord.setContentText("Please enter the searched word ");
       //searchedWord.
       searchedWord.setX(800);
       searchedWord.setY(200);
       searchedWord.getDialogPane().setStyle(" -fx-max-width:300px; -fx-max-height: 200px; -fx-pref-width: 300px; -fx-pref-height: 200px;");
       //searchedWord.getDialogPane().getButtonTypes().setAll(caseSensitiv, caseNotSensitiv);
        
       exchangeWord= new TextInputDialog();
       exchangeWord.setTitle("Exchange");
       exchangeWord.setHeaderText("Exchange with");
       exchangeWord.setContentText("Please enter the new word ");
       exchangeWord.setX(800);
       exchangeWord.setY(200);
       exchangeWord.getDialogPane().setStyle(" -fx-max-width:300px; -fx-max-height: 200px; -fx-pref-width: 300px; -fx-pref-height: 200px;");
       
        
       
        // Name label
        inputLabel = new Label("Input");
        //GridPane.setConstraints(inputLabel, 0, 2);
        //GridPane.setConstraints(inputLabel, 0, 2, 1, 2);
        
        // search button
        searchButton = new Button("search");
        //GridPane.setConstraints(searchButton, 0, 4);  
        //GridPane.setConstraints(searchButton, 0, 6, 1, 2);
        searchButton.setVisible(false);
        GridPane.setConstraints(searchButton, 2, 10, 1, 2);
        
        // Exchange / exec button 
        execButton = new Button("Execute");
        execButton.setVisible(false);
        GridPane.setConstraints(execButton, 3, 10, 1, 2);
        
        // Close search fields button
        closeSearchFields = new Button("Close");
        closeSearchFields.setVisible(false);
        GridPane.setConstraints(closeSearchFields, 4, 10, 1, 1);
        //Input field
        searched= new TextField();
        searched.setPromptText("Input field");
        searched.setVisible(false);
        GridPane.setConstraints(searched, 2, 12, 1, 2);
        
        //Echange field
        addInput = new TextField();
        addInput.setPromptText("additional field");
        addInput.setVisible(false);
        GridPane.setConstraints(addInput, 3, 12, 1, 1);
        
        // Password label
        addLabel = new Label("Additional field");
        
        //Radio buttons
        
        //caseNotSensitive.setSelected(true);
        //caseNotSensitive.setActionCommand("NotSensitiv");
        
        //caseSensitive.setActionCommand("Sensitiv");
        // Group for radio groups
      /*  ToggleGroup groupB = new ToggleGroup();
        caseSensitive.setToggleGroup(groupB);
        caseSensitive.setSelected(false);
        caseNotSensitive.setToggleGroup(groupB);
        caseNotSensitive.setSelected(true); */
        
        //Wrong design of buttons placement, to be changed later !!!
        /*GridPane.setConstraints(caseSensitive, 9, 0, 1, 1);
        GridPane.setConstraints(caseNotSensitive, 11, 0, 1, 1); */
        
       // caseSensitive.addActionListener((ActionListener) this);
        //caseNotSensitive.addActionListener((ActionListener) this);
        
           
        //Text text area
        
        freeText = new TextArea();
        freeText.setPrefSize(580, 430);  //width, heigth
        //freeText.setPre
        freeText.setPromptText("Type here..");
        //GridPane.setConstraints(freeText, 0, 5, 5, 4);  // column, raw 
        //GridPane.setConstraints(freeText, 0, 5, 5, 6);
        GridPane.setConstraints(freeText, 0, 5, 7, 8);
        
        grid.getChildren().addAll(inputLabel,searched,addLabel,addInput,execButton,freeText,menuBar,searchButton,closeSearchFields);
       // grid.getChildren().addAll(freeText, menuBar);
        
                // Events
                
                // test event 
        execButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //System.out.println(freeText.getText());
               // isInt(freeText,freeText.getText());
                
            }
        });
        
        //This action listener implements closing of the "buttons panel"
        closeSearchFields.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                        searchButton.setVisible(false);
                        execButton.setVisible(false);
                        searched.setVisible(false);
                        addInput.setVisible(false);
                        closeSearchFields.setVisible(false);
                        GridPane.setConstraints(freeText, 0, 5, 7, 8);
                        
            }
        });
        
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent e)
            {
                currentText = freeText.getText();
                System.out.println("Test - the current text it " + currentText);
            }
        
        });
        
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                
                Platform.exit();
            }
        });
        
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                restartAll();  
            }
        } ) ;
        
     /*     searchItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Optional<String> result = searchedWord.showAndWait();
                    if (result.isPresent()){
                      //while (result.isPresent()) {
                        setTestContentFromDialogue(result.get());
                        searched.setText(getTestContentFromDialogue());  //added 17.05 12:36
                        //Calls the methode to color the first found word 
                        searchTheWord();
                        
                        
                        System.out.println(getTestContentFromDialogue()+ "THIS IS THE CONTENt");
                        
                        //Effekt
                        GridPane.setConstraints(freeText, 0, 5, 5, 4);  // column, raw
                        searchButton.setVisible(true);
                        execButton.setVisible(true);
                        searched.setVisible(true);
                        addInput.setVisible(true);
                        closeSearchFields.setVisible(true);
                        //grid.getChildren().addAll(inputLabel,searched,addLabel,addInput,execButton,freeText,menuBar,searchButton);
                        
                    }
                
            }  */
     
            // New version, implements the choice of case conditions 
            searchItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Optional<String> result = searchedWord.showAndWait();
                    if (result.isPresent() && (caseSensitiveCheck.isSelected()==true)){
                      //while (result.isPresent()) {
                        setTestContentFromDialogue(result.get());
                        searched.setText(getTestContentFromDialogue());  //added 17.05 12:36
                        //Calls the methode to color the first found word 
                        searchTheWord();
                    
                        
                        
                        System.out.println(getTestContentFromDialogue()+ "THIS IS THE CONTENt");
                        
                        //Effekt
                        GridPane.setConstraints(freeText, 0, 5, 5, 4);  // column, raw
                        searchButton.setVisible(true);
                        execButton.setVisible(true);
                        searched.setVisible(true);
                        addInput.setVisible(true);
                        closeSearchFields.setVisible(true);
                        
                        //grid.getChildren().addAll(inputLabel,searched,addLabel,addInput,execButton,freeText,menuBar,searchButton);
                        
                    }
                    
                    else if ((result.isPresent() && (caseSensitiveCheck.isSelected()!=true))){  // here should be not equal
                       //while (result.isPresent()) {
                        setTestContentFromDialogue(result.get());
                        searched.setText(getTestContentFromDialogue());  //added 17.05 12:36
                        //Calls the methode to color the first found word 
                        //freeText.setText("TEST ONLY!");  //DEBUG!!!
                        searchTheWordCasenotSensitive();
                    
                        
                        
                        System.out.println(getTestContentFromDialogue()+ "THIS IS THE CONTENt");
                        
                        //Effekt
                        GridPane.setConstraints(freeText, 0, 5, 5, 4);  // column, raw
                        searchButton.setVisible(true);
                        execButton.setVisible(true);
                        searched.setVisible(true);
                        addInput.setVisible(true);
                        closeSearchFields.setVisible(true);                       
                    }
                
            } 
            
            
        });
          
          exchangeItem.setOnAction(new EventHandler<ActionEvent>() {   //added 19.05 13:45
              @Override
              public void handle(ActionEvent event) {
                  Optional<String> resultEx = exchangeWord.showAndWait();
                   if (resultEx.isPresent()){
                        setTestContentFromDialogExchange(resultEx.get());
                        addInput.setText(getTestContentFromDialogExchange());  
                        
                        if (index >= 3 )
                        {
                            ersetzenWord(freeText.getText().substring(index-3, index)+freeText.getSelectedText(), freeText.getText().substring(index-3, index)+addInput.getText(), freeText);
                        }
                        else
                        {
                            ersetzenWord(freeText.getSelectedText(), addInput.getText(), freeText);
                        }
                        
                        System.out.println(getTestContentFromDialogExchange()+ "THIS IS THE CONTENt");  
                        
                        //Effekt
                        GridPane.setConstraints(freeText, 0, 5, 5, 4);  // column, raw
                        searchButton.setVisible(true);
                        execButton.setVisible(true);
                        searched.setVisible(true);
                        addInput.setVisible(true);
                        closeSearchFields.setVisible(true);
                    }  
              }
              
              
          });
          
          /*restartItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //Optional <String> resultRestart =
                
                //currentText = null;
                searchedText = null;
                searched.setText(null);
                addInput.setText(null);
               // freeText.setText(null);
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfMatches[i]=false;
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfExchanges[i] = false;
                
                loopKey=0;
                firstPaint = true;
                firstRun = false;
                index =0;
                currentSearchedWord = "";
                shift = 0;
                setTestContentFromDialogue("");
                
                searchButton.setVisible(false);
                execButton.setVisible(false);
                searched.setVisible(false);
                addInput.setVisible(false);
                closeSearchFields.setVisible(false);
                GridPane.setConstraints(freeText, 0, 5, 7, 8);
            }
        });  */
       
       
       searchButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle (ActionEvent e)
           {
               //click on this menu button, calls the methode to color the first found word 
               if (caseSensitiveCheck.isSelected()==true)
                   searchTheWord();
               else if (caseSensitiveCheck.isSelected() == false)
                   searchTheWordCasenotSensitive(); 

                
               
           }
       });
       
       caseSensitiveCheck.setOnAction(new EventHandler<ActionEvent>() {
           public void handle (ActionEvent e)
           {
               
                searchedText = null;
              // searched.setText(null);   18.10.2017!!!!!
                addInput.setText(null);
               // freeText.setText(null);
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfMatches[i]=false;
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfExchanges[i] = false;
                
                loopKey=0;
                firstPaint = true;
                firstRun = false;
                index =0;
                currentSearchedWord = "";
                shift = 0;
                setTestContentFromDialogue("");
                
                /*searchButton.setVisible(false);
                execButton.setVisible(false);
                searched.setVisible(false);
                addInput.setVisible(false);
                closeSearchFields.setVisible(false);
                GridPane.setConstraints(freeText, 0, 5, 7, 8);  */
           }
                   
       });
              
               
        Scene scene = new Scene(grid,600,450);
        window.setScene(scene);
       
        window.show();
        
    }
    
     public void paintText(boolean[] indexes, int lengthOfText)
    {
        int i;
        
        firstPaint = false;
        //freeText.selectRange(i, lengthOfText);
        String container = freeText.getText();
        int containerLength = container.length();
        System.out.println("Inside the painting methode");
        String content = searched.getText();
        int contentLength = content.length();
        
        //for (i=0; i<containerLength;i++)
        for (i=0;i<containerLength;i++)  // 16.05 16;30
        {
            //if (indexes[i+shift] == true)  // 16.05 16;30
            if (indexes[i] == true)
            {
                freeText.selectRange(i,i+contentLength);
                //index = i+shift;  // 16.05 16:30
                index = i;
                //indexes[i+shift]=false;  //// 16.05 16;30
                indexes[i]=false;
                break;
            } 
            
            currentlyPainted=i;
             
        }  
        
        
        execButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        if (index >= 3 )
                        {
                            ersetzenWord(freeText.getText().substring(index-3, index)+freeText.getSelectedText(), freeText.getText().substring(index-3, index)+addInput.getText(), freeText);
                        }
                        else
                        {
                            ersetzenWord(freeText.getSelectedText(), addInput.getText(), freeText);
                        }
                    }
                    
                } );
    }
  
     
     private void ersetzenWord (String oldWord, String newWord, TextArea textField)
     {
         String text = textField.getText();         
         //recognise the index of the begin of exchange
         int startOfExchange = 0;
         for (int j=0; j<TEXTSIZE;j++)
         {
             if (indexOfMatches[j]==true)
             {
                 startOfExchange = j;
             }
         }
         this.freeText.setText(text.replaceFirst(oldWord, newWord));
         //in case the new word is smaller, it will installled in the right place, and all the 
         // proccess will be restarted 
         firstPaint = true;  //18.05 11:33
         for (int i=0;i<TEXTSIZE;i++) //18.05 11:35
         {
             indexOfMatches[i]=false;
             for (int k=0;k<TEXTSIZE;k++)
                    indexOfMatches[k]=false;
             for (int k=0;k<TEXTSIZE;k++)
                    indexOfExchanges[k] = false;
                
             loopKey=0;
             firstPaint = true;
             firstRun = false;
             index =0;
             currentSearchedWord = "";
             shift = 0;
             setTestContentFromDialogue("");
         }
     }
         
         // methoe to look for the input, when case is matter 
         private void searchTheWord ()
         {
             //if (caseNotSensitive.isSelected() == true)
            if (!currentSearchedWord.equals(searched.getText()))
               {
                   currentSearchedWord = searched.getText();
                   firstPaint = true;
                   loopKey=0;
                   shift = 0;
                   for (int temp=0;temp<TEXTSIZE;temp++)
                   {
                       indexOfMatches[temp] = false;
                       indexOfExchanges[temp] = false;
                   }
               } 
               String container = freeText.getText();
               int containerLength = container.length();
               //System.out.println("Test container" + container + " " + containerLength);
               String content = searched.getText();
               int contentLength = content.length();
               // indexOfMatches will have true for every index where the searched string started and found 
               // here he creates indexOfMatches again, instead using the old one , and thus, changes the values 
               // boolean[] indexOfMatches = new boolean[containerLength]; 

               int lastIndex = 0;
               int count = 0;
               System.out.println("first paint is " + firstPaint);
               //checks if anything already was marked. if was marked, no need to search for indexes again
               if (firstPaint == true)
                {
                    // checks where the searched word occures
                    while (lastIndex != -1) {

                        lastIndex = container.indexOf(content, lastIndex);
                        System.out.println("Here i should visit only once !");
                        if (lastIndex != -1) {
                            count++;
                            indexOfMatches[lastIndex] = true ;
                            lastIndex += contentLength; 
                        }
                 
                            
                    
                    }
               }
                int k = loopKey;
                System.out.println("16.05.2017  " + k);
                indexOfExchanges=indexOfMatches;  //added 15.05 at 18:05
                for (; k<containerLength;k++)
                {
                    loopKey++;
                    Arrays.toString(indexOfMatches);  // Debug purposes 
                    if (indexOfMatches[k] == true)
                    //if (indexOfMatches[k+shift] == true)  //16.05 16:30
                    {
                        paintText(indexOfMatches, containerLength);
                        for (int j=0;j<containerLength;j++)  // debug purposes 
                        {
                             System.out.println(indexOfMatches[j] +  "  ");
                        }
                        //indexOfMatches[k+shift] = false;  //16.06 16:30
                        indexOfMatches[k]=false;
                        //loopKey++;
                       
                        System.out.println(k + " deep inside the loop, current bolean value is" + indexOfMatches[k]);
                        break;
                    }
                    
                }
         }
         
         
         // Methode to look for the input, when case doesn't matter 
         private void searchTheWordCasenotSensitive()
         {
                if (!currentSearchedWord.equals(searched.getText()))
               {
                   currentSearchedWord = searched.getText();
                   firstPaint = true;
                   loopKey=0;
                   shift = 0;
                   for (int temp=0;temp<TEXTSIZE;temp++)
                   {
                       indexOfMatches[temp] = false;
                       indexOfExchanges[temp] = false;
                   }
               }
                
               String container = freeText.getText();
               int containerLength = container.length();
               String containerNotSensitiv = freeText.getText();
               //System.out.println("Test container" + container + " " + containerLength);
               String content = searched.getText();
               String contentNotSensitiv = searched.getText();
               int contentLength = content.length();
               
               // indexOfMatches will have true for every index where the searched string started and found 
               // here he creates indexOfMatches again, instead using the old one , and thus, changes the values 
               // boolean[] indexOfMatches = new boolean[containerLength]; 

               int lastIndex = 0;
               int count = 0;
               System.out.println("first paint is " + firstPaint);
               // copy of the text is converted to lower case (case doesn't matter anymore )
               
               containerNotSensitiv=container.toLowerCase();
               contentNotSensitiv=content.toLowerCase();
               //System.out.println(containerNotSensitiv + "Debug TEST 31.05");
               //freeText.setText(containerNotSensitiv); // for debug only!!!!
               //checks if anything already was marked. if was marked, no need to search for indexes again
               if (firstPaint == true)
                {
                    // checks where the searched word occures
                    while (lastIndex != -1) {

                        lastIndex = containerNotSensitiv.indexOf(contentNotSensitiv, lastIndex);
                        System.out.println("Here i should visit only once !");
                        if (lastIndex != -1) {
                            count++;
                            indexOfMatches[lastIndex] = true ;
                            lastIndex += contentLength; 
                        }
                 
                            
                    
                    }
               }
                int k = loopKey;
                System.out.println("16.05.2017  " + k);
                indexOfExchanges=indexOfMatches;  //added 15.05 at 18:05
                for (; k<containerLength;k++)
                {
                    loopKey++;
                    Arrays.toString(indexOfMatches);  // Debug purposes 
                    if (indexOfMatches[k] == true)
                    //if (indexOfMatches[k+shift] == true)  //16.05 16:30
                    {
                        paintText(indexOfMatches, containerLength);
                        for (int j=0;j<containerLength;j++)  // debug purposes 
                        {
                             System.out.println(indexOfMatches[j] +  "  ");
                        }
                        //indexOfMatches[k+shift] = false;  //16.06 16:30
                        indexOfMatches[k]=false;
                        //loopKey++;
                       
                        System.out.println(k + " deep inside the loop, current bolean value is" + indexOfMatches[k]);
                        break;
                    }
                    
                }
        }
         
         // restartAll clears all the data, and displays the app new from scratch
         private void restartAll()
         {
                currentText = null;
                searchedText = null;
                searched.setText(null);
                addInput.setText(null);
                freeText.setText(null);
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfMatches[i]=false;
                for (int i=0;i<TEXTSIZE;i++)
                    indexOfExchanges[i] = false;
                
                loopKey=0;
                firstPaint = true;
                firstRun = false;
                index =0;
                currentSearchedWord = "";
                shift = 0;
                setTestContentFromDialogue("");
         }

                
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}