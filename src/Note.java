import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class Note extends JFrame {
    private JTextPane textPane;
    private final Stack<String> undoStack = new Stack<>();
    private String currentText = "";

    public Note() {
        setTitle("Note");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane);

        textPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!e.isControlDown() && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    currentText = textPane.getText();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_B) {
                    toggleBoldStyle();
                } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_U) {
                    toggleUnderlineStyle();
                } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    undo();
                } else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
                	textchange();
                } else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W) {
                	downtextchange();
                } else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_E) {
                	fontchange();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setVisible(true);
    }

    private void toggleBoldStyle() {//b
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (end > start) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setBold(attr, !StyleConstants.isBold(attr));
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }	

    private void toggleUnderlineStyle() {//u
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (end > start) {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setUnderline(attr, !StyleConstants.isUnderline(attr));
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
    }

    private void textchange() {
    	StyledDocument doc = textPane.getStyledDocument();
    	int start = textPane.getSelectionStart();
    	int end = textPane.getSelectionEnd();
    	
    	if(end > start) {
    		SimpleAttributeSet attr = new SimpleAttributeSet();
    		StyleConstants.setSuperscript(attr, !StyleConstants.isSuperscript(attr));
    		doc.setCharacterAttributes(start, end - start, attr, false);
    	}
    }
    
    private void fontchange() {
    	StyledDocument doc = textPane.getStyledDocument();
    	int start = textPane.getSelectionStart();
    	int end = textPane.getSelectionEnd();
    	
    	if(end > start) {
    		SimpleAttributeSet attr = new SimpleAttributeSet();
    		String Fontfamily = "Arial";
    		StyleConstants.setFontFamily(attr, Fontfamily);
    		doc.setCharacterAttributes(start, end - start, attr, false);
    		
    	}
    }
    
    private void downtextchange() {
    	StyledDocument doc = textPane.getStyledDocument();
    	int start = textPane.getSelectionStart();
    	int end = textPane.getSelectionEnd();
    	
    	if(end > start) {
    		SimpleAttributeSet attr = new SimpleAttributeSet();
    		StyleConstants.setSubscript(attr, !StyleConstants.isSubscript(attr));
    		doc.setCharacterAttributes(start, end - start, attr, false);
    	}
    }
    
    private void undo() {
        if (!undoStack.isEmpty()) {//z
            String textToRestore = undoStack.pop();
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontFamily(attr, "SansSerif");
            StyleConstants.setFontSize(attr, 12);
            try {
                doc.insertString(doc.getLength(), textToRestore, attr);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Note::new);
    }
}
