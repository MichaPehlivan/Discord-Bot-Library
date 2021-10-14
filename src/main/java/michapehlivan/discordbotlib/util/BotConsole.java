package michapehlivan.discordbotlib.util;

import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class for creating and managing a console window to display output to
 * @author Micha Pehlivan
 */
public class BotConsole extends JFrame{
    
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private PrintStream printer;

    /**
     * Constructs a console window with the specified variables
     * @param title The title of the console window
     * @param width The widht of the window
     * @param height The height of the window
     */
    public BotConsole(String title, int width, int height){
        setSize(width, height);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        try {
            setIconImage(ImageIO.read(new File("src/main/java/michapehlivan/discordbotlib/util/discord_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));

        printer = new PrintStream(new OutputStream(){

            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char)b));
            }
            
        });

        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setBorder(null);
        scrollPane.setBorder(null);

        add(scrollPane);
        setVisible(true);
    }

    /**
     * Get the printstream associated with this console window
     * @return The printstream of this console window
     */
    public PrintStream getPrintStream(){
        return printer;
    }
}
