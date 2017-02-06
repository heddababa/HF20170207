package modell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdatBazisKezeles implements AdatbazisKapcsolat {
  private static Connection kapcsolat;

  private static void kapcsolatNyit() {
    try {
      Class.forName(DRIVER);
      kapcsolat = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    catch (ClassNotFoundException e) {
      System.out.println("Hiba! Hiányzik a JDBC driver.");
    }
    catch (SQLException e) {
      System.out.println("Hiba! Nem sikerült megnyitni a kapcsolatot az adatbázis-szerverrel.");
    }
  }

  private static void kapcsolatZar() {
    try {
      kapcsolat.close();
    }
    catch (SQLException e) {
      System.out.println("Hiba! Nem sikerült lezárni a kapcsolatot az adatbázis-szerverrel.");
    }
  }
  
  //TODO szukseges lekerdezesek es ezek visszaadasa
  
//  public ArrayList<String> lekerdezOsszesDolgozoListaja(String reszleg) { //Osszes dolgozo
//    ArrayList<String> lista=new ArrayList<>();
//    try {
//      kapcsolatNyit();
//      Statement s = kapcsolat.createStatement();
//      ResultSet rs = s.executeQuery(
//        "SELECT FIRST_NAME || ' ' || LAST_NAME AS NÉV\n"+
//        "FROM EMPLOYEES E\n" +
//        "ORDER BY NÉV");
//      while(rs.next())
//        lista.add(rs.getString("NÉV"));
//      kapcsolatZar();
//    }
//    catch(SQLException e) {
//      System.out.println(e.getMessage());
//    }
//    return lista;    
//  } 
  
//  public ArrayList<String> lekerdezDolgozokListajaAdottReszleghez(String reszleg) { //Adott reszleg dolgozoi
//    ArrayList<String> lista=new ArrayList<>();
//    try {
//      kapcsolatNyit();
//      PreparedStatement ps=kapcsolat.prepareStatement(
//        "SELECT FIRST_NAME || ' ' || LAST_NAME AS NÉV "+
//        "FROM DEPARTMENTS D, EMPLOYEES E\n" +
//        "WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID AND D.DEPARTMENT_NAME=?"+
//        "ORDER BY NÉV");
//      ps.setString(1, reszleg);
//      ResultSet rs=ps.executeQuery();
//      while(rs.next())
//        lista.add(rs.getString("NÉV"));
//      kapcsolatZar();
//    }
//    catch(SQLException e) {
//      System.out.println(e.getMessage());
//    }
//    return lista;    
//  }  
  
  public int[] lekerdezMinMaxFizetes(String munkakorAzonosito) { //Adott munkakorhoz tartozo min es max fizetes
    int[] minmaxFizetes={0,0};
    try {
      kapcsolatNyit();
      PreparedStatement ps=kapcsolat.prepareStatement(
        "SELECT MIN_SALARY AS MINFIZETÉS, MAX_SALARY AS MAXFIZETÉS \n" +
        "FROM JOBS\n" +
        "WHERE JOB_ID=?");
      ps.setString(1, ""+munkakorAzonosito);
      ResultSet rs=ps.executeQuery();        
      rs.next();
      minmaxFizetes[0]=rs.getInt("MINFIZETÉS");
      minmaxFizetes[1]=rs.getInt("MAXFIZETÉS");
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    kapcsolatZar();
    return minmaxFizetes;
  }
    
  public int lekerdezMinFizetes(String munkakorAzonosito) { //Adott munkakorhoz tartozo min fizetes
    int fizetes=0;
    try {
      kapcsolatNyit();
      PreparedStatement ps=kapcsolat.prepareStatement(
        "SELECT MIN_SALARY AS MINFIZETÉS \n" +
        "FROM JOBS\n" +
        "WHERE JOB_TITLE=?");
      ps.setString(1, ""+munkakorAzonosito);
      ResultSet rs=ps.executeQuery();        
      rs.next();
      fizetes=rs.getInt("MINFIZETÉS");
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    kapcsolatZar();    
    return fizetes;
  }

  public int lekerdezMaxFizetés(String munkakorAzonosito) { //Adott munkakorhoz tartozo max fizetes
    int fizetes=0;
    try {
      kapcsolatNyit();
      PreparedStatement ps=kapcsolat.prepareStatement(
        "SELECT MAX_SALARY AS MAXFIZETÉS \n" +
        "FROM JOBS\n" +
        "WHERE JOB_TITLE=?");
      ps.setString(1, ""+munkakorAzonosito);
      ResultSet rs=ps.executeQuery();        
      rs.next();
      fizetes=rs.getInt("MAXFIZETÉS");
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    kapcsolatZar();
    return fizetes;
  }

/*  
public static void modositFizetés(int dolgozoID, int ujFizetes) { //Adott dolgozo fizetesenek modositasa adott osszegre
    try {
//      kapcsolatNyit();
//      PreparedStatement ps=kapcsolat.prepareStatement(
//        "UPDATE EMPLOYEES \n" +
//        "SET SALARY=?\n" +
//        "WHERE EMPLOYEE_ID=?");
//      ps.setInt(1, dolgozoID);
//      ps.setInt(2, ujFizetes);
//      ps.executeUpdate(); 
//Teszthez
      Statement s = kapcsolat.createStatement();
      s.executeUpdate(
              "update employees \n" +
              "set salary=9000\n" +
              "where employee_id=110");
      kapcsolatZar();
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
*/

  //******************Insert!
  /*
  	private static void insertRecordIntoDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO DBUSER"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+ "(1,'mkyong','system', " + "to_date('"
				+ getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
  
  	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return dateFormat.format(today.getTime());

	}
  
  try {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO MYTABLE (USERID, USERNAME, EMAILADDRESS, PHONENUMBER, PROFILEPICTURE )"
                    + " VALUES (?, ?, ?, ?, ?)");
            prepareStatement.setString(1, "10");
            prepareStatement.setString(2, "ALI");
            prepareStatement.setString(3, "gdgrgrregeg");
            prepareStatement.setString(4, "0501977498");
            prepareStatement.setNull(5, NULL);
            prepareStatement.execute();
 } catch (SQLException e) {
            System.out.println("IT DOES NOT WORK");
        }
  
  */
 
public static boolean modositFizetés(int dolgozoID, int ujFizetes){
  PreparedStatement ps = null;
  boolean ok=false;
  String fizetesModositoSQL =
      "UPDATE EMPLOYEES \n" +
      "SET SALARY=? \n" +
      "WHERE EMPLOYEE_ID=? ";
  
  kapcsolatNyit();
  try {
      kapcsolat.setAutoCommit(false);
      ps = kapcsolat.prepareStatement(fizetesModositoSQL);
      ps.setString(1, ""+ujFizetes);
      ps.setDouble(2, dolgozoID);
      ps.executeUpdate();
      kapcsolat.commit();
      ok=true;
  }
  catch (SQLException e ) {
    System.out.println(e.getMessage());
    //JDBCTutorialUtilities.printSQLException(e);
    if (kapcsolat != null) {
      try {
        System.err.print("A tranzakció visszagörgetésre kerül!");
        kapcsolat.rollback();
      } catch(SQLException excep) {
        System.out.println(e.getMessage());
        //JDBCTutorialUtilities.printSQLException(excep);
        }
      }
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
        kapcsolat.setAutoCommit(true);
      } catch (SQLException sQLException) {
        sQLException.printStackTrace();
      }
    }
    kapcsolatZar();
    return ok;
  }

  public ArrayList<Dolgozo> lekerdezDolgozokListajaAdottReszleghez(int reszlegId) { //Adott reszleg dolgozoi
    ArrayList<Dolgozo> lista=new ArrayList<>();
    try {
      kapcsolatNyit();
      PreparedStatement ps;
      if(reszlegId==-1){
      ps=kapcsolat.prepareStatement("SELECT E.EMPLOYEE_ID as empId, \n" +
                                    "E.FIRST_NAME || ' ' || E.LAST_NAME AS name, \n" +
                                    "D.DEPARTMENT_ID as depId, \n" +
                                    "D.DEPARTMENT_NAME AS depName, \n" +
                                    "JOBS.JOB_TITLE as jobTitle,\n" +
                                    "E.SALARY as SALARY,\n" +
                                    "E. HIRE_DATE \n" +
                                    "FROM JOBS, EMPLOYEES E \n" +
                                    "LEFT JOIN DEPARTMENTS D \n" +
                                    "ON D.DEPARTMENT_ID = E.DEPARTMENT_ID \n" +
                                    "WHERE JOBS.JOB_ID=E.JOB_ID \n" +
                                    "ORDER BY name");
        
      }else{
        ps=kapcsolat.prepareStatement(
              "SELECT EMP_DETAILS_VIEW.EMPLOYEE_ID as empId,\n" +
              "EMP_DETAILS_VIEW.FIRST_NAME || ' ' || EMP_DETAILS_VIEW.LAST_NAME as name,\n" +
              "EMP_DETAILS_VIEW.DEPARTMENT_ID as depId,\n" +
              "EMP_DETAILS_VIEW.DEPARTMENT_NAME as depName,\n" +
              "EMP_DETAILS_VIEW.JOB_TITLE as jobTitle,\n" +
              "EMP_DETAILS_VIEW.SALARY as SALARY,\n" +
              "FROM JOBS JOBS,\n" +
              "EMP_DETAILS_VIEW EMP_DETAILS_VIEW\n" +
              "AND EMP_DETAILS_VIEW.DEPARTMENT_ID=?\n" +
              "ORDER BY NAME");
        ps.setString(1, ""+reszlegId);
      }
      ResultSet rs=ps.executeQuery();
      while(rs.next()){
        Dolgozo dolgozo = new Dolgozo(rs.getInt("empId"), 
                                      rs.getString("name"), 
                                      rs.getInt("depId"), 
                                      (reszlegId==-1?"Részleg nélküli":rs.getString("depName")), 
                                      rs.getString("jobTitle"), 
                                      rs.getInt("SALARY"));
        lista.add(dolgozo);
      }
    }
    catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    kapcsolatZar();
    return lista;    
  }
  
  public ArrayList<Reszleg> lekerdezReszleg() {
    ArrayList<Reszleg> lista = new ArrayList<>();
    try {
      kapcsolatNyit();
      Statement s = kapcsolat.createStatement();
      ResultSet rs = s.executeQuery(
              "SELECT DEPARTMENT_ID, DEPARTMENT_NAME\n" +
              "FROM DEPARTMENTS\n" +
              "WHERE DEPARTMENT_ID IN \n" +
              "(SELECT DISTINCT DEPARTMENT_ID FROM EMPLOYEES)\n" +
              "ORDER BY 2");      
      while (rs.next()){
        Reszleg reszleg = new Reszleg(rs.getString("DEPARTMENT_NAME"), rs.getInt("DEPARTMENT_ID"));
        lista.add(reszleg);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    kapcsolatZar();
    return lista;
  }
  
  public ArrayList<Munkakor> lekerdezMunkakorok() { //Összes munkakör
    ArrayList<Munkakor> lista=new ArrayList<>();
    try {
      kapcsolatNyit();
      Statement s = kapcsolat.createStatement();
      ResultSet rs = s.executeQuery(
        "SELECT JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY\n"+
        "FROM JOBS J\n" +
        "ORDER BY JOB_TITLE");
      while(rs.next()) {
        Munkakor munkakor = new Munkakor(rs.getString("JOB_TITLE"), rs.getString("JOB_ID"), 
              rs.getInt("MIN_SALARY"), rs.getInt("MAX_SALARY"));
        lista.add(munkakor);
      }
      kapcsolatZar();
    }
    catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return lista;    
  }
}
