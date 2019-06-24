
package budgetapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author david
 */
public class maaserAccount extends Account {

    private double balance;

    @Override
    public double getBalance() {
        return balance;
    }

    
    @Override
        public void withdraw(double amount){
        balance -= amount;
    }
        
        public void withdraw(double amount,String org) {
        balance -= amount;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        BudgetJDBC.insertMaaserInfo(amount, org,dtf.format(localDate));
    }

    @Override
    public void deposit(double amount) {
        balance += (amount * .10);
        System.out.println(balance);

    }

}
