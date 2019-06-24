
package budgetapp;

/**
 *
 * @author david
 */
public class  Account {


    private double balance;
         
    
    

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount){
        balance -= amount;
    }
    public void deposit(double amount){
         balance += amount;
         System.out.println(balance);
         
    }
    
 
//    public String balance(){
//          StringBuilder sb = new StringBuilder();
//        sb.append("Balance: ").append(balance).append("\n");
//        
//        return sb.toString();  

// }  

    
}
