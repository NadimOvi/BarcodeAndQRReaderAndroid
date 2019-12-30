package com.nextinnovation.pt.barcodescanner.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String TAG = "@string/app_name";

    // Your IP address must be static otherwise this will not work. You //can get your Ip address
    //From Network and security in Windows.
    String ip = "191.168.0.104";
    // This is default if you are using JTDS driver.
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    // Name Of your database.
    String db = "DB_A5131E_Barcode";
    // Userame and password are required for security.
    //so Go to sql server and add username and password for your database.
    String un = "DB_A5131E_Barcode_admin";
    String password = "ovi01912992147";
    Connection conn;

    @SuppressLint("NewApi")
    public Connection dbCon() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        }
        catch (SQLException se)
        {
            Log.e("error", se.getMessage());
        }
        catch (ClassNotFoundException e) {
        }
        catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return conn;
    }

//    public void dbCon() {
//        try {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
//            conn = DriverManager.getConnection(""
//                    + "jdbc:jtds:sqlserver://172.16.160.81/northwind;instance=SQL2008;"
//                    + "user=sa;password=sa;");
//
//        } catch (Exception e) {
//            tv.setText(e.toString());
//        }
//    }
//call this function after adding comment
    public void dbStoreData(String product_code, String scan_time, String scan_date){
//        btnAdd = (Button) findViewById(R.id.btnAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                dbCon();
                PreparedStatement comm;
                try {
                    comm = conn.prepareStatement("insert into product("
                            + "product_code, scan_time, scan_date) values("+product_code+","+scan_time+","+scan_date+")");
//                    comm.setString(1, etFirst.getText().toString());
//                    comm.setString(2, etLast.getText().toString());
                    comm.executeUpdate();
//                    Toast.makeText(,"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
//                    tv.setText(e.toString());
                }
//                dbGetData();
//            }
//        });
    }

/*    public void dbGetData() {
        dbCon();
        Statement comm;
        try {
            comm = conn.createStatement();
            ResultSet rs = comm.executeQuery("Select EmployeeID, Firstname From Employees");
            String msg = "";
            while (rs.next()) {
                msg += "\nID: " + rs.getInt("EmployeeID") + " Name: "
                        + rs.getString("Firstname");
            }
            tv.setText(msg); //tv is text view
        } catch (SQLException e) {
            tv.setText(e.toString());
        }
    }*/
}
