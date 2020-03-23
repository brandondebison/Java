/*
Brandon Debison - W0154032
6-Apr-19
Assignment 5 part 1 
Note: you can only create one pizza at a time, you can select as many toppings as you wish. 
If you change your pizza size you start over.
*/
    import java.awt.Color;
    import java.awt.Container;
    import java.awt.FlowLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;    
    import javax.swing.DefaultListModel;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JList;
    
    
    public class Pizzeria extends JFrame implements ActionListener {

        private String[] str_pizzaSize = {"Small ($7)","Medium ($9)","Large ($11)","Extra-Large ($14)"};        
        private DefaultListModel dlm_pizzaSizes = new DefaultListModel();
        private JList jl_pizzaSizes = new JList(dlm_pizzaSizes);
        private DefaultListModel dlm_selectedSize = new DefaultListModel();
        private JList jl_selectedSize = new JList(dlm_selectedSize);
        private JButton size_move = new JButton("->");
        private Container content = getContentPane();
        // create personal Domino's order 
        private String[] str_toppings = {"Cheese","Pepperoni","Green Peppers","Tomatoes", "Mushrooms","Ham"};
        private DefaultListModel dlm_toppings = new DefaultListModel();
        private JList jl_toppings = new JList(dlm_toppings);
        private DefaultListModel dlm_selectedtoppings = new DefaultListModel();
        private JList jl_selectedtoppings = new JList(dlm_selectedtoppings);
        private JButton topping_move = new JButton("->");

        private JButton order_clear = new JButton("Clear");

        private double totalCost = 0;
        private JLabel total = new JLabel("Total = $"+totalCost);
        private JLabel informationA = new JLabel("Welcome! please select a size before choosing toppings.");
        private JLabel informationB = new JLabel("Changing sizes will reset your order. Cowabunga!");

        
    
        public Pizzeria() {
            setSize(330,500);
            content.setBackground(Color.white);
            content.setLayout(new FlowLayout());
            
            fillTheList();

            content.add(informationA);
            content.add(informationB);
            
            content.add(jl_pizzaSizes);
            content.add(size_move);
            size_move.addActionListener (this);
            
            content.add(jl_selectedSize);
                       
    
            content.add(new JLabel("Select a pizza size and click -> to order"));

            content.add(jl_toppings);
            content.add(topping_move);
            topping_move.addActionListener (this);
            
            content.add(jl_selectedtoppings);
            
    
            content.add(new JLabel("Select toppings you would like on your pizza and click -> to add"));

            content.add(order_clear);
            order_clear.addActionListener (this);
            
            content.add(total);
            


            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        
        // function to populate the lists and set the Total cost to 0.
        // strings are setup to match the selection boxes
        public void fillTheList() {

            for (int i=0; i<str_pizzaSize.length; i++) {
                dlm_pizzaSizes.add(i,  str_pizzaSize[i]);
            }

            for (int i=0; i<str_toppings.length; i++) {
                dlm_toppings.add(i,  str_toppings[i]);
            }
            totalCost = 0;
            total.setText("Total = $"+totalCost);
        }
        
        public void actionPerformed(ActionEvent evt) {
            
            if (evt.getSource() == size_move) {
                dlm_selectedSize.removeAllElements();
                dlm_selectedtoppings.removeAllElements();
                totalCost = 0;
                dlm_selectedSize.addElement(jl_pizzaSizes.getSelectedValue());
                
                int testsize = jl_pizzaSizes.getSelectedIndex();                
                // Setup to match the fill function 
                switch (testsize) {

                    case 0: totalCost = +7;break;
                    case 1: totalCost = +9;break;
                    case 2: totalCost = +11;break;
                    case 3: totalCost = +14;break;
                    default: ;
                }
                total.setText("Total = $"+totalCost);
            }
        // cheese is setup to cost 0 by matching the string list 
            if (evt.getSource() == topping_move) {
                dlm_selectedtoppings.addElement(jl_toppings.getSelectedValue());
            
                int testtop = jl_toppings.getSelectedIndex();                

                switch (testtop) {

                    case 0: ;break;
                    
                    default: totalCost = totalCost +1;
                }
                total.setText("Total = $"+totalCost);

            }
            
            if (evt.getSource() == order_clear) {
                dlm_selectedSize.removeAllElements();
                dlm_pizzaSizes.removeAllElements();
                dlm_selectedtoppings.removeAllElements();
                dlm_toppings.removeAllElements();
                fillTheList();
            }
            
        }
    
        public static void main(String[] args) {
            Pizzeria myFrame = new Pizzeria();
            myFrame.setVisible(true);
        }
    
    }
    