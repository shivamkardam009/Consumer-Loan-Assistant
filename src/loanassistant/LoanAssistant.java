package loanassistant;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;

public class LoanAssistant extends JFrame 
{
	JLabel loanbalancelabel,interestratelabel,monthslabel,paymentlabel,analysislabel;
	JTextField loanbalancetextfield,interestratetextfield,monthstextfield,paymenttextfield;
	JButton computebutton,newloanbutton,monthsbutton,paymentbutton,exitbutton;
	Container c;
	JTextArea analysistextarea;
	boolean computepayment;
	Font myFont = new Font("Arial", Font.PLAIN, 16);
	Color lightYellow = new Color(255, 255, 128);

	public static void main(String[] args)
	{
		new LoanAssistant().show();
	}
	public LoanAssistant()
	{
		super("Loan Assistant");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height-getHeight())), getWidth(), getHeight());
		setResizable(false);
		c=getContentPane();
		setVisible(true);
		c.setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;	
		addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent evt)
		{ System.exit(0);  }
  		});
		
		loanbalancelabel=new JLabel("Loan Balance");
		loanbalancelabel.setFont(myFont);
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=0;
		gridConstraints.anchor=GridBagConstraints.WEST;
		gridConstraints.insets=new Insets(10,10,0,0);
		c.add(loanbalancelabel,gridConstraints);
		
		loanbalancetextfield=new JTextField();
		loanbalancetextfield.setPreferredSize(new Dimension(100, 25));
	    loanbalancetextfield.setHorizontalAlignment(SwingConstants.RIGHT);
	    loanbalancetextfield.setFont(myFont);
	    gridConstraints = new GridBagConstraints();
	    gridConstraints.gridx=1;
	    gridConstraints.gridy=0;
	    gridConstraints.insets=new Insets(10,10,0,10);
	    c.add(loanbalancetextfield,gridConstraints);
	    
	    //Focus Transfer(By Enter)
	    loanbalancetextfield.addActionListener(new ActionListener()
	    		{	public void actionPerformed(ActionEvent e)
	    			{
	    			loanbalancetextfield.transferFocus();
	    			}
	    		});

		interestratelabel=new JLabel("Interest Rate");
		interestratelabel.setFont(myFont);
	    gridConstraints = new GridBagConstraints();
	    gridConstraints.gridx=0;
	    gridConstraints.gridy=1;
	    gridConstraints.anchor=GridBagConstraints.WEST;
	    gridConstraints.insets=new Insets(10,10,0,0);
	    c.add(interestratelabel,gridConstraints);
		
		interestratetextfield=new JTextField();
		interestratetextfield.setPreferredSize(new Dimension(100,25));
	    interestratetextfield.setHorizontalAlignment(SwingConstants.RIGHT);
	    interestratetextfield.setFont(myFont);
	    gridConstraints = new GridBagConstraints();
	    gridConstraints.gridx=1;
	    gridConstraints.gridy=1;
	    gridConstraints.insets=new Insets(10,10,0,10);
	    c.add(interestratetextfield,gridConstraints);
	    //Focus Transfer(By Enter)
	    interestratetextfield.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{
			interestratetextfield.transferFocus();
			}
		});
	    
	    monthslabel=new JLabel("Number of Payment");
		monthslabel.setFont(myFont);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=2;
		gridConstraints.anchor=GridBagConstraints.WEST;
		gridConstraints.insets=new Insets(10,10,0,0);
		c.add(monthslabel,gridConstraints);
		
		monthstextfield=new JTextField();
		monthstextfield.setPreferredSize(new Dimension(100, 25));
		monthstextfield.setHorizontalAlignment(SwingConstants.RIGHT);
		monthstextfield.setFont(myFont);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=2;
		gridConstraints.insets=new Insets(10,10,0,10);
		getContentPane().add(monthstextfield, gridConstraints);
		//Focus Transfer(By Enter)
		monthstextfield.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{
			monthstextfield.transferFocus();
			}
		});
		
		paymentlabel=new JLabel("Monthly Payment");
		paymentlabel.setFont(myFont);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=3;
		gridConstraints.anchor=GridBagConstraints.WEST;
		gridConstraints.insets=new Insets(10,10,0,0);
		c.add(paymentlabel,gridConstraints);
		
		paymenttextfield=new JTextField();
		paymenttextfield.setPreferredSize(new Dimension(100, 25));
		paymenttextfield.setHorizontalAlignment(SwingConstants.RIGHT);
		paymenttextfield.setFont(myFont);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,10,0,10);
		getContentPane().add(paymenttextfield, gridConstraints);
		//Focus Transfer(By Enter)
		paymenttextfield.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent e)
			{
			paymenttextfield.transferFocus();
			}
		});
		
		computebutton=new JButton("Compute Monthly Payment");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=4;
		gridConstraints.gridwidth=2;
		gridConstraints.insets=new Insets(10,0,0,0);
		c.add(computebutton,gridConstraints);
		
        newloanbutton=new JButton("New Loan Analysis");
		newloanbutton.setEnabled(false);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=5;
		gridConstraints.gridwidth=2;
		gridConstraints.insets=new Insets(10,0,10,0);
		c.add(newloanbutton,gridConstraints);

		computebutton.addActionListener(new ActionListener()
	      {
			public void actionPerformed(ActionEvent ae)
			{	
				double balance,interest,payment;
				int months;
				double monthlyinterest,multiplier;
				double loanbalance, finalpayment;
				if(validateDecimalNumber(loanbalancetextfield))
				balance=Double.parseDouble(loanbalancetextfield.getText());
				else
				{
					JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\n Please correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);	
					return;
				}
				if(validateDecimalNumber(interestratetextfield))
				interest=Double.parseDouble(interestratetextfield.getText());
				else
				{
					JOptionPane.showConfirmDialog(null, "Invalid or empty Interest Rate entry.\n Please correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				monthlyinterest=interest/1200;
				if(computepayment)
				{
				    //Compute loan payment
					if(validateDecimalNumber(monthstextfield))
					months=Integer.parseInt(monthstextfield.getText());
					else
					{
						JOptionPane.showConfirmDialog(null, "Invalid or empty Number of payment entry.\n Please correct.", "payment Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(interest==0)
					payment=balance/months;
					else
					{
					multiplier = Math.pow((1 + monthlyinterest), months);
					payment = (balance * monthlyinterest * multiplier) / (multiplier - 1);
					}
					paymenttextfield.setText(new DecimalFormat("0.00").format(payment));
				 }
				else
				{
					//compute number of payments
				  if(validateDecimalNumber(paymenttextfield))
				  {
					payment=Double.parseDouble(paymenttextfield.getText());
					if(payment<=(balance * monthlyinterest+1.0))
					 {
						if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $" + new DecimalFormat("0.00").format((int)(balance * monthlyinterest + 1.0)) + "\n Do you want to use the minimum payment?", "Input Error", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
						{
							paymenttextfield.setText(new DecimalFormat("0.00").format((int)(balance * monthlyinterest + 1.0)));
							payment = Double.parseDouble(paymenttextfield.getText());
						}
						else
						{
							paymenttextfield.requestFocus();
							return;
						}	
					  }
					
				   }
				   else
				   {
						JOptionPane.showConfirmDialog(null, "Invalid or empty Monthly payment entry.\n Please correct.", "Monthly payment Input Error", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
						return;
				   }
					
					if(interest==0)
					months=(int)(balance/payment);
					else
					{
					months=(int)((Math.log(payment)-Math.log(payment-balance*monthlyinterest))/Math.log(1+monthlyinterest));
					}
					monthstextfield.setText(""+months);
				 }			
				
				//reset payment prior to analysis to fix 2 decimal:
				payment=Double.parseDouble(paymenttextfield.getText());
				
				//show analysis
				analysistextarea.setText("Loan Balance:$"+new DecimalFormat("0.00").format(balance));
				analysistextarea.append("\nInterest Rate:"+new DecimalFormat("0.00").format(interest)+"%");
				
				//process all but last payment
				loanbalance=balance;
				for(int paymentnumber=1;paymentnumber<=months-1;paymentnumber++)
					loanbalance+=loanbalance*monthlyinterest-payment;
				
				//find final payment
				finalpayment=loanbalance;
				if(finalpayment>payment)
				{
					//apply one more payment
					loanbalance+=loanbalance*monthlyinterest-payment;
					finalpayment=loanbalance;
					months++;
					monthstextfield.setText(""+months);
				}
				
				analysistextarea.append("\n\n"+String.valueOf(months-1)+"Payment of: $"+new DecimalFormat("0.00").format(payment));
				analysistextarea.append("\n"+"Final Payment of:$"+new DecimalFormat("0.00").format(finalpayment));
				analysistextarea.append("\n"+"Total payments:$"+new DecimalFormat("0.00").format((months-1)*payment+finalpayment));	
				analysistextarea.append("\n"+"Interest paid:$"+new DecimalFormat("0.00").format((months-1)*payment+finalpayment-balance));
				computebutton.setEnabled(false);
				newloanbutton.setEnabled(true);
				newloanbutton.requestFocus();
			}
	      }
			);
		
		newloanbutton.addActionListener(new ActionListener()
	      {
			public void actionPerformed(ActionEvent ae)
			{	
				//clear computed value and analysis
				if(computepayment)
				paymenttextfield.setText("");
				else
				monthstextfield.setText("");
				
				analysistextarea.setText("");
				computebutton.setEnabled(true);
				newloanbutton.setEnabled(false);
				loanbalancetextfield.requestFocus();
			}
	      }
			);
		
		monthsbutton=new JButton("X");
		monthsbutton.setFocusable(false);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=2;
		gridConstraints.insets=new Insets(10,0,0,0);
		c.add(monthsbutton,gridConstraints);
		
		monthsbutton.addActionListener(new ActionListener()
	      {
			public void actionPerformed(ActionEvent ae)
			{		
				computepayment=false;
				paymentbutton.setVisible(true);
				monthsbutton.setVisible(false);
				monthstextfield.setText("");
				monthstextfield.setBackground(lightYellow);
				monthstextfield.setFocusable(false);
				paymenttextfield.setEditable(true);				
				paymenttextfield.setBackground(Color.WHITE);
				computebutton.setText("Compute Number of Payments");
				monthstextfield.setEditable(false);
				paymenttextfield.setFocusable(true);
				loanbalancetextfield.requestFocus();
			}
	      });
		
		paymentbutton=new JButton("X");
		paymentbutton.setFocusable(false);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,0,0,0);
		c.add(paymentbutton,gridConstraints);
		
		paymentbutton.addActionListener(new ActionListener()
	      {
			public void actionPerformed(ActionEvent ae)
			{	
			//compute payment
			computepayment = true;
			paymentbutton.setVisible(false);
			monthsbutton.setVisible(true);
			monthstextfield.setEditable(true);
			monthstextfield.setBackground(Color.WHITE);
			monthstextfield.setFocusable(true);
			paymenttextfield.setText("");
			paymenttextfield.setEditable(false);
			paymenttextfield.setBackground(lightYellow);
			computebutton.setText("Compute Monthly Payment");		
			paymenttextfield.setFocusable(false);
			loanbalancetextfield.requestFocus();	
			}
	      }
			);
		
		analysislabel=new JLabel("Loan Analysis:");
		analysislabel.setFont(myFont);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=3;
		gridConstraints.gridy=0;
		gridConstraints.anchor=GridBagConstraints.WEST;
		gridConstraints.insets=new Insets(0,10,0,0);
		getContentPane().add(analysislabel,gridConstraints);
		
		analysistextarea=new JTextArea();
		analysistextarea.setPreferredSize(new Dimension(250,150));
		analysistextarea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		analysistextarea.setFont(new Font("Courier New", Font.PLAIN, 14));
		analysistextarea.setEditable(false);
		analysistextarea.setFocusable(false);
		analysistextarea.setBackground(Color.WHITE);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=3;
		gridConstraints.gridy=1;
		gridConstraints.gridheight=4;
		gridConstraints.insets=new Insets(0,10,0,10);
		c.add(analysistextarea,gridConstraints);
		
		exitbutton=new JButton("Exit");
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx=3;
		gridConstraints.gridy=5;
		c.add(exitbutton,gridConstraints);
		exitbutton.setFocusable(false);
		
		exitbutton.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent ae)
			{	
				System.exit(0);	
			}
	    });
		pack();	
	}

    public boolean validateDecimalNumber(JTextField tf)
    {
    	   //check to see if textfield contain and validate decimal number with only digit a single decimal point
    		String s=tf.getText().trim();
    		boolean hasdecimal=false;
    		boolean valid=true;
    	if(s.length()==0)
    		valid=false;
    	else
    	{
    		for(int i=0;i<s.length();i++)
    		{
    			char c=s.charAt(i);
    			if(c>='0' && c<='9')
    			{
    				continue;
    			}
    			else if(c=='.' && !hasdecimal)
    			{
    				hasdecimal=true;
    			}
    			else
    			{
    				valid=false;
    			}
    		}
    	}
    	tf.setText(s);
    	if(!valid)
    	{
    		tf.requestFocus();
    	}
    	return valid;
      }
}
