package budgetapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Output {

    private String maaserInfo;

    public void setMaaserInfo(String maaserInfo) {
        this.maaserInfo = maaserInfo;
    }

    public String getMaaserInfo() {
        return maaserInfo;
    }

    public static void fileWriter(ResultSet rs) throws IOException {
        ArrayList<String> qresults = new ArrayList<String>();
        try {
            int transNum = rs.getInt(1);
            double amount = rs.getDouble(2);
            String org = rs.getString(3);
            String date = rs.getString(4);

            while (rs.next()) {
                String qresult = String.format("%d, %f, %s, %s", transNum, amount,
                        org, date);
                qresults.add(qresult);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
        }

        File f = new File("C:\\Users\\david\\Documents\\java\\maaserInfo.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fos = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fos);

        for (String q : qresults) {
            fos.write(q);
            fos.flush();
        }

        fos.close();
    }

}
