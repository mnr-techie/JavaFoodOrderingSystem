import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FoodOrderingSystem extends JFrame implements ActionListener {
    private JList<String> menuList, orderList;
    private DefaultListModel<String> menuListModel, orderListModel;
    private JButton addButton, removeButton, checkoutButton;
    private JLabel orderLabel;
    private double totalCost = 0.0; // Updated to double

    public FoodOrderingSystem() {
        setTitle("Food Ordering System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuListModel = new DefaultListModel<>();
        menuListModel.addElement("Dosa");
        menuListModel.addElement("Idli");
        menuListModel.addElement("Vada");
        menuListModel.addElement("Utappam");
        menuListModel.addElement("Manchurian");
        menuListModel.addElement("Tomato Pizza");
        menuListModel.addElement("Pizza Pepperonion");
        menuListModel.addElement("Burger");
        menuListModel.addElement("Fries");

        menuList = new JList<>(menuListModel);

        orderListModel = new DefaultListModel<>();

        orderList = new JList<>(orderListModel);

        addButton = new JButton("Add Item");
        addButton.addActionListener(this);
        addButton.setBackground(Color.BLUE); // set background color
        addButton.setFont(new Font("Arial", Font.BOLD, 14)); // set font size to 16


        removeButton = new JButton("Remove From List");
        removeButton.addActionListener(this);
        removeButton.setBackground(Color.RED); // set background color
        removeButton.setFont(new Font("Arial", Font.BOLD, 14)); // set font size to 16


        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);
        checkoutButton.setBackground(Color.GREEN); // set background color
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14)); // set font size to 16


        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(new JLabel("Menu:"), BorderLayout.NORTH);
        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);
        

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.add(new JLabel("Order:"), BorderLayout.NORTH);
        orderPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);
        orderPanel.add(removeButton, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(menuPanel);
        centerPanel.add(orderPanel);

        orderLabel = new JLabel("Total Cost: Rs.0.00");
        orderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        orderLabel.setFont(new Font("Arial", Font.BOLD, 1));


        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(orderLabel, BorderLayout.CENTER);
        bottomPanel.add(checkoutButton, BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);
        add(addButton, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private double getCost(String item) {
        if (item.contains("Tomato Pizza")) {
            return 199.00;
        } else if (item.contains("Burger")) {
            return 129.00;
        } 
        else if (item.contains("Pizza Pepperonion")) {
            return 159.00;
        } else if (item.contains("Fries")) {
            return 3.00;
        }
        else if (item.contains("Dosa")) {
            return 50.00;
        } 
        else if (item.contains("Idli")) {
            return 40.00;
        }
        else if (item.contains("Vada")) {
            return 40.00;
        }
        else if (item.contains("Utappam")) {
            return 60.00;
        }
        else if (item.contains("Manchurian")) {
            return 80.00;
        }else {
            return 0.0;
        }
    }

    private void updateOrderLabel() {
        orderLabel.setText(String.format("Total Cost: Rs.%.2f", totalCost));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String selectedItem = menuList.getSelectedValue();
            if (selectedItem != null) {
                orderListModel.addElement(selectedItem);
                totalCost += getCost(selectedItem);
                updateOrderLabel();
            }
        } else if (e.getSource() == removeButton) {
            int selectedIndex = orderList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = orderList.getSelectedValue();
                orderListModel.remove(selectedIndex);
                totalCost -= getCost(selectedItem);
                updateOrderLabel();
            }
        } else if (e.getSource() == checkoutButton) {
            JOptionPane.showMessageDialog(this, "Your total cost is Rs." + String.format("%.2f", totalCost));
        }
    }

    public static void main(String[] args) {
        new FoodOrderingSystem();
    }
}