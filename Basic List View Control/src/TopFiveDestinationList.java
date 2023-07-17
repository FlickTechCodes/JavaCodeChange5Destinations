/*
 * Author: Randal James Flick
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and show TopDestinationListFrame
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}

class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();

        //Updated Top 5 destinations with new places and descriptions. Also added corresponding images
        addDestinationNameAndPicture("1. Santorini, Greece (Breathtaking views with beautiful sunsets)", new ImageIcon(getClass().getResource("/resources/Santorini.jpg")));
        addDestinationNameAndPicture("2. Tokyo, Japan (Blend of traditional and futuristic)", new ImageIcon(getClass().getResource("/resources/Tokyo.jpg")));
        addDestinationNameAndPicture("3. Paris, France (City of love)", new ImageIcon(getClass().getResource("/resources/Paris.jpg")));
        addDestinationNameAndPicture("4. Bora Bora, French Polynesia (Tropical paradise)", new ImageIcon(getClass().getResource("/resources/BoraBora.jpg")));
        addDestinationNameAndPicture("5. Queenstown, New Zealand (Adventure capital)", new ImageIcon(getClass().getResource("/resources/Queenstown.jpg")));

        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}

class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}

class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        TextAndIcon tai = (TextAndIcon) value;
        setText(tai.getText());
        setIcon(tai.getIcon());

        // Changed background and foreground color for selected and non-selected list items
        if (isSelected) {
            setBackground(Color.DARK_GRAY);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
        }

        Border outsideBorder;
        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override
    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}
