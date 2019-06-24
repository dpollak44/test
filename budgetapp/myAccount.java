
package budgetapp;

/**
 *
 * @author david
 */
public class myAccount extends Account {


    private double balance;
         
    
    

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void withdraw(double amount){
        balance -= amount;
    }
    @Override
    public void deposit(double amount){
         balance += amount;
       
    }
    
    }