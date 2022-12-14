package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies Database
 * This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/COVID.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }
    //Get months 
    public ArrayList<String> getMonth() {
        ArrayList<String> months = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Months";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String month   = results.getString("MonthName");
                

                // For now we will just store the movieName and ignore the id
                months.add(month);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return months;
    }

    //Page3 
    //all by worst
    public ArrayList<String> getCountryNamesWorst() {
        ArrayList<String> countryNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name FROM Country ORDER BY CasesTotal DESC, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryName     = results.getString("Name");
                

                // For now we will just store the movieName and ignore the id
                countryNames.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryNames;
    }

    public ArrayList<Integer> getCasesTotalWorst() {
        ArrayList<Integer> casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY CasesTotal DESC, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesTotal.add(caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesTotal;
    }

    public ArrayList<String> getInfectedHighestDateWorst() {
        ArrayList<String> i_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT cd1.Date FROM CountryData cd1 JOIN COUNTRY c ON cd1.CountryName = c.Name GROUP BY cd1.CountryName HAVING MAX(cd1.NoNewCases) ORDER BY CasesTotal DESC, c.Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String i_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                i_highdates.add(i_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return i_highdates;
    }
    
    public ArrayList<Integer> getMonthlyCasesWorst(String month) {
        ArrayList<Integer> casesMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT SUM(NoNewCases) FROM Country c JOIN CountryData cd ON c.Name = cd.CountryName JOIN Months m ON cd.MonthNo = m.MonthNo WHERE (DATE >= '" + minDate + "' AND DATE <= '" + maxDate + "') GROUP BY CountryName ORDER BY CasesTotal DESC, c.Name;";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseMonthly   = results2.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesMonthly.add(caseMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return casesMonthly;
    }

    public ArrayList<Integer> getMonthlyCasesLeast(String month) {
        ArrayList<Integer> casesMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT SUM(NoNewCases) FROM Country c JOIN CountryData cd ON c.Name = cd.CountryName JOIN Months m ON cd.MonthNo = m.MonthNo WHERE (DATE >= '" + minDate + "' AND DATE <= '" + maxDate + "') GROUP BY CountryName ORDER BY CasesTotal, c.Name;";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseMonthly   = results2.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesMonthly.add(caseMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return casesMonthly;
    }


    //Infected all by least

    public ArrayList<String> getCountryNamesLeast() {
        ArrayList<String> countryNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name FROM Country ORDER BY CasesTotal, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryName     = results.getString("Name");
                

                // For now we will just store the movieName and ignore the id
                countryNames.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryNames;
    }

    public ArrayList<Integer> getCasesTotalLeast() {
        ArrayList<Integer> casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY CasesTotal, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesTotal.add(caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesTotal;
    }

    public ArrayList<String> getInfectedHighestDateLeast() {
        ArrayList<String> i_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT cd1.Date FROM CountryData cd1 JOIN COUNTRY c ON cd1.CountryName = c.Name GROUP BY cd1.CountryName HAVING MAX(cd1.NoNewCases) ORDER BY CasesTotal, c.Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String i_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                i_highdates.add(i_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return i_highdates;
    }


    //Page 3 AUS
    //Infected AUS by worst

    public ArrayList<String> getAusRegionNamesWorst() {
        ArrayList<String> regionNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT RegionName FROM Region WHERE CountryName='Australia' ORDER BY CasesTotal DESC, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String regionName     = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                regionNames.add(regionName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return regionNames;
    }

    public ArrayList<Integer> getAusCasesTotalWorst() {
        ArrayList<Integer> aus_casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE CountryName='Australia' ORDER BY CasesTotal DESC, RegionName;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int aus_caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                aus_casesTotal.add(aus_caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return aus_casesTotal;
    }


    public ArrayList<String> getAusInfectedHighestDateWorst() {
        ArrayList<String> ausi_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd1.Date FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName WHERE r.CountryName='Australia' GROUP BY rd1.RegionName HAVING MAX(rd1.NoNewCases) ORDER BY r.CasesTotal DESC, r.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String ausi_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                ausi_highdates.add(ausi_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ausi_highdates;
    }

    //Infected AUS by least

    public ArrayList<String> getAusRegionNamesLeast() {
        ArrayList<String> regionNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT RegionName FROM Region WHERE CountryName='Australia' ORDER BY CasesTotal, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String regionName     = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                regionNames.add(regionName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return regionNames;
    }

    public ArrayList<Integer> getAusCasesTotalLeast() {
        ArrayList<Integer> aus_casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE CountryName='Australia' ORDER BY CasesTotal, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int aus_caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                aus_casesTotal.add(aus_caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return aus_casesTotal;
    }


    public ArrayList<String> getAusInfectedHighestDateLeast() {
        ArrayList<String> ausi_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd1.Date FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName WHERE r.CountryName='Australia' GROUP BY rd1.RegionName HAVING MAX(rd1.NoNewCases) ORDER BY r.CasesTotal, r.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String ausi_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                ausi_highdates.add(ausi_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ausi_highdates;
    }

    public ArrayList<Integer> getAusMonthlyCasesWorst(String month) {
        ArrayList<Integer> casesMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT rd1.RegionName, SUM(NoNewCases) FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName  JOIN Months m ON rd1.MonthNo = m.MonthNo WHERE (Date >= '" + minDate + "' AND Date <= '" + maxDate + "' AND r.CountryName='Australia') GROUP BY rd1.RegionName ORDER BY r.CasesTotal DESC, r.RegionName";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseMonthly   = results2.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesMonthly.add(caseMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return casesMonthly;
    }

    public ArrayList<Integer> getAusMonthlyCasesLeast(String month) {
        ArrayList<Integer> casesMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT rd1.RegionName, SUM(NoNewCases) FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName  JOIN Months m ON rd1.MonthNo = m.MonthNo WHERE (Date >= '" + minDate + "' AND Date <= '" + maxDate + "' AND r.CountryName='Australia') GROUP BY rd1.RegionName ORDER BY r.CasesTotal, r.RegionName";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseMonthly   = results2.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesMonthly.add(caseMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return casesMonthly;
    }


    //Page 4
    //Deaths All by worst

    public ArrayList<String> getCountryNames1Worst() {
        ArrayList<String> countryNames1 = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name FROM Country ORDER BY DeathsTotal DESC, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryName1     = results.getString("Name");
                

                // For now we will just store the movieName and ignore the id
                countryNames1.add(countryName1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryNames1;
    }

    public ArrayList<Integer> getDeathsTotalWorst() {
        ArrayList<Integer> deathsTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country ORDER BY DeathsTotal DESC, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                deathsTotal.add(deathTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsTotal;
    }

    
    public ArrayList<String> getDeathsHighestDateWorst() {
        ArrayList<String> d_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT c.Name, cd1.Date FROM CountryData cd1 JOIN COUNTRY c ON cd1.CountryName = c.Name GROUP BY cd1.CountryName HAVING (MAX(cd1.NoDeaths)> -1) ORDER BY DeathsTotal DESC";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String d_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                d_highdates.add(d_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return d_highdates;
    } 

    //Deaths all by least

    public ArrayList<String> getCountryNames1Least() {
        ArrayList<String> countryNames1 = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name FROM Country ORDER BY DeathsTotal, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryName1     = results.getString("Name");
                

                // For now we will just store the movieName and ignore the id
                countryNames1.add(countryName1);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryNames1;
    }

    public ArrayList<Integer> getDeathsTotalLeast() {
        ArrayList<Integer> deathsTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country ORDER BY DeathsTotal, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                deathsTotal.add(deathTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsTotal;
    }

    
    public ArrayList<String> getDeathsHighestDateLeast() {
        ArrayList<String> d_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT c.Name, cd1.Date FROM CountryData cd1 JOIN COUNTRY c ON cd1.CountryName = c.Name GROUP BY cd1.CountryName HAVING (MAX(cd1.NoDeaths)> -1) ORDER BY DeathsTotal";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String d_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                d_highdates.add(d_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return d_highdates;
    } 

    //Deaths AUS by worst

    public ArrayList<String> getAusRegionNames1Worst() {
        ArrayList<String> regionNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT RegionName FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal DESC, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String regionName     = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                regionNames.add(regionName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return regionNames;
    }

    public ArrayList<Integer> getAusDeathsTotalWorst() {
        ArrayList<Integer> aus_casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal DESC, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int aus_caseTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                aus_casesTotal.add(aus_caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return aus_casesTotal;
    }


    public ArrayList<String> getAusDeathsHighestDateWorst() {
        ArrayList<String> ausi_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd1.Date FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName WHERE r.CountryName='Australia' GROUP BY rd1.RegionName HAVING (MAX(rd1.NoDeaths)> -1) ORDER BY r.DeathsTotal DESC, r.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String ausi_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                ausi_highdates.add(ausi_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ausi_highdates;
    }
    
    // Deaths AUS by least
    public ArrayList<String> getAusRegionNames1Least() {
        ArrayList<String> regionNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT RegionName FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String regionName     = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                regionNames.add(regionName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return regionNames;
    }

    public ArrayList<Integer> getAusDeathsTotalLeast() {
        ArrayList<Integer> aus_casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int aus_caseTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                aus_casesTotal.add(aus_caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return aus_casesTotal;
    }


    public ArrayList<String> getAusDeathsHighestDateLeast() {
        ArrayList<String> ausi_highdates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd1.Date FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName WHERE r.CountryName='Australia' GROUP BY rd1.RegionName HAVING (MAX(rd1.NoDeaths)> -1) ORDER BY r.DeathsTotal, r.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String ausi_highdate   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                ausi_highdates.add(ausi_highdate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ausi_highdates;
    }

    //Getting ALL casestotal by deathstotal for page 4

    public ArrayList<Double> getCasesTotalOrderDeathsTotalWorst() {
        ArrayList<Double> casesByDeaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY DeathsTotal DESC, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double casesByDeath     = results.getDouble("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesByDeaths.add(casesByDeath);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesByDeaths;
    }

    public ArrayList<Double> getCasesTotalOrderDeathsTotalLeast() {
        ArrayList<Double> casesByDeaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name, CasesTotal FROM Country ORDER BY DeathsTotal, Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double casesByDeath     = results.getDouble("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesByDeaths.add(casesByDeath);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesByDeaths;
    }

    //Getting AUS casesTotal by deathTotals for page 4
    public ArrayList<Double> getAUSCasesTotalOrderDeathsTotalWorst() {
        ArrayList<Double> casesByDeaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal DESC, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double casesByDeath     = results.getDouble("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesByDeaths.add(casesByDeath);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesByDeaths;
    }

    public ArrayList<Double> getAUSCasesTotalOrderDeathsTotalLeast() {
        ArrayList<Double> casesByDeaths = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE CountryName='Australia' ORDER BY DeathsTotal, RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double casesByDeath     = results.getDouble("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesByDeaths.add(casesByDeath);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesByDeaths;
    }

    public ArrayList<Integer> getMonthlyDeathsWorst(String month) {
        ArrayList<Integer> deathsMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT SUM(NoDeaths) FROM Country c JOIN CountryData cd ON c.Name = cd.CountryName JOIN Months m ON cd.MonthNo = m.MonthNo WHERE (DATE >= '" + minDate + "' AND DATE <= '" + maxDate + "') GROUP BY CountryName ORDER BY DeathsTotal DESC, c.Name;";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathMonthly   = results2.getInt("SUM(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsMonthly.add(deathMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return deathsMonthly;
    }

    public ArrayList<Integer> getMonthlyDeathsLeast(String month) {
        ArrayList<Integer> deathsMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT SUM(NoDeaths) FROM Country c JOIN CountryData cd ON c.Name = cd.CountryName JOIN Months m ON cd.MonthNo = m.MonthNo WHERE (DATE >= '" + minDate + "' AND DATE <= '" + maxDate + "') GROUP BY CountryName ORDER BY DeathsTotal, c.Name;";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathMonthly   = results2.getInt("SUM(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsMonthly.add(deathMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return deathsMonthly;
    }

    public ArrayList<Integer> getAusMonthlyDeathsWorst(String month) {
        ArrayList<Integer> deathsMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT rd1.RegionName, SUM(NoDeaths) FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName  JOIN Months m ON rd1.MonthNo = m.MonthNo WHERE (Date >= '" + minDate + "' AND Date <= '" + maxDate + "' AND r.CountryName='Australia') GROUP BY rd1.RegionName ORDER BY r.DeathsTotal DESC, r.RegionName";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathMonthly   = results2.getInt("SUM(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsMonthly.add(deathMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return deathsMonthly;
    }

    public ArrayList<Integer> getAusMonthlyDeathsLeast(String month) {
        ArrayList<Integer> deathsMonthly = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MinDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            String minDate = results.getString("MinDate");
            statement.close();
            System.out.println(minDate);

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT MaxDate FROM Months WHERE MonthName = '" + month + "'";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
            String maxDate = results1.getString("MaxDate");
            statement1.close();
            System.out.println(maxDate);

            Statement statement2 = connection.createStatement();
            statement2.setQueryTimeout(30);

            // The Query
            String query2 = "SELECT rd1.RegionName, SUM(NoDeaths) FROM RegionData rd1 JOIN Region r ON rd1.RegionName = r.RegionName  JOIN Months m ON rd1.MonthNo = m.MonthNo WHERE (Date >= '" + minDate + "' AND Date <= '" + maxDate + "' AND r.CountryName='Australia') GROUP BY rd1.RegionName ORDER BY r.DeathsTotal, r.RegionName";
            
            // Get Result
            ResultSet results2 = statement2.executeQuery(query2);
            
            while (results2.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathMonthly   = results2.getInt("SUM(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsMonthly.add(deathMonthly);
            }

            // Close the statement because we are done with it
            statement2.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        // return casesMonthly;
        return deathsMonthly;
    }

    //Page 5
    //Get country names alphabetically 
    public ArrayList<String> getCountryNamesAlphabetic() {
        ArrayList<String> countryNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Name FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String countryName   = results.getString("Name");
                

                // For now we will just store the movieName and ignore the id
                countryNames.add(countryName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryNames;
    }

    //get casesTotal by country name alphabetically
    public ArrayList<Integer> getCasesTotalAlphabetic() {
        ArrayList<Integer> casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesTotal.add(caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesTotal;
    }

    //get casesTotal by country name alphabetically for last x days
    public ArrayList<Integer> getCasesTotalLastxAlphabetic(int date) {
        ArrayList<Integer> casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(NoNewCases) FROM CountryData WHERE (LastxDays >= '0' AND LastxDays <= '" + date + "') GROUP BY CountryName ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseTotal   = results.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesTotal.add(caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesTotal;
    }

    //get deathsTotal by country name alphabetically
    public ArrayList<Integer> getDeathsTotalAlphabetic() {
        ArrayList<Integer> deathsTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                deathsTotal.add(deathTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsTotal;
    }
    //get deathsTotal by country name alphabetically for last x days
    public ArrayList<Integer> getDeathsTotalLastxAlphabetic(int date) {
        ArrayList<Integer> deathsTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(NoDeaths) FROM CountryData WHERE (LastxDays >= '0' AND LastxDays <= '" + date + "') GROUP BY CountryName ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathTotal   = results.getInt("SUM(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsTotal.add(deathTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsTotal;
    }

    //Get Population by country name
    public ArrayList<Double> getCountryPopulationAlphabetic() {
        ArrayList<Double> populations = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Population FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double population   = results.getDouble("Population");
                

                // For now we will just store the movieName and ignore the id
                populations.add(population);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return populations;
    }

    //get lat for selected country
    public double getCountryLat(String countryName) {
        double lat = 0.00;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Latitude FROM Country WHERE Name = '" + countryName + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

           
            lat  = results.getDouble("Latitude");
               
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return lat;
    }

    //get lon for selected country
    public double getCountryLon(String countryName) {
        double lon = 0.00;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Longitude FROM Country WHERE Name = '" + countryName + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

           
            lon  = results.getDouble("Longitude");
               
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return lon;
    }

    //get lat for all country
    public ArrayList<Double> getCountriesLat() {
        ArrayList<Double> lats = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Latitude FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double lat   = results.getDouble("Latitude");
                

                // For now we will just store the movieName and ignore the id
                lats.add(lat);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return lats;
    }

    //get all lon country
    public ArrayList<Double> getCountriesLon() {
        ArrayList<Double> lons = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Longitude FROM Country ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double lon   = results.getDouble("Longitude");
                

                // For now we will just store the movieName and ignore the id
                lons.add(lon);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return lons;
    }

    //get countries within x km of selected country
    public ArrayList<String> getNamesWithIn (ArrayList<String> countrynames, double lat1, double lon1, ArrayList<Double> latall, ArrayList<Double> lonall, int x) {
        ArrayList<String> name = new ArrayList<String>();
        double lat2 = 0.00;
        double lon2 = 0.00;
        double dlon = 0.00;
        double dlat = 0.00;
        double a = 0.00;
        double c  = 0.00;
        double r = 6371;

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);

        for (int i = 0; i < countrynames.size(); ++i) {
            lat2 = Math.toRadians(latall.get(i));
            lon2 = Math.toRadians(lonall.get(i));
            dlon = lon2 - lon1;
            dlat = lat2 - lat1;
            a = (Math.pow(Math.sin(dlat / 2), 2)) + (Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2));
            c = 2 * Math.asin(Math.sqrt(a));

            if ((c * r) <= x) {
                name.add(countrynames.get(i));
            }

        }

        return name;
    }

    public ArrayList<Integer> getIndexOfCountryWithSimilarLat (double lat, ArrayList<Double> lats) {
        ArrayList<Integer> count = new ArrayList<Integer>();
        double minRange = lat - 4.0;
        double maxRange = lat + 4.0;

        for (int i = 0; i < lats.size(); ++i) {

            if ((lats.get(i) >= minRange) && (lats.get(i) <= maxRange)) {
                count.add(i);
            }
        }



        return count;
    }

    //Page 6
    //Get US states alphabetically
    public ArrayList<String> getUSStatesAlphabetic() {
        ArrayList<String> stateNames = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT RegionName FROM Region WHERE CountryName = 'United States' GROUP BY RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String stateName   = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                stateNames.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return stateNames;
    }

    //Get US state population alphabetically 
    public ArrayList<Double> getUSStatePopulationAlphabetic() {
        ArrayList<Double> populations = new ArrayList<Double>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Population FROM Region WHERE CountryName='United States' ORDER BY RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                double population   = results.getDouble("Population");
                

                // For now we will just store the movieName and ignore the id
                populations.add(population);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return populations;
    }


    //All tab
    //get number of cases of a particular country
    public double getCountryCase(String countryName) {
        double noCase = 0.0;
        double population = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Country WHERE Name = '" + countryName + "' ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

                noCase   = results.getDouble("CasesTotal");
                

            statement.close();

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT Population FROM Country WHERE Name = '" + countryName + "' ORDER BY Name";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);

                population   = results1.getDouble("Population");
                

            statement1.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ((noCase/population)*1000000);
    }

    //get indexes of countries similar to selected country for cases
    public ArrayList<Integer> getIndexOfCountriesCases (double noCases, ArrayList<Integer> casesTotal, ArrayList<Double> populationCountry) {
        ArrayList<Integer> cases = new ArrayList<Integer>();
        double minRange = noCases - (noCases * 0.25);
        double maxRange = noCases + (noCases * 0.25);

        for (int i = 0; i < casesTotal.size(); ++i) {
            
            if ((((casesTotal.get(i)/ populationCountry.get(i))*1000000)  >= minRange) && (((casesTotal.get(i)/ populationCountry.get(i))*1000000) <= maxRange)) {
                cases.add(i);
            }  

        }

        return cases;
    }

    //get number of deaths of a particular country
    public double getCountryDeath(String countryName) {
        double noDeath = 0.0;
        double noCases = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Country WHERE Name = '" + countryName + "' ORDER BY Name";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
                noDeath   = results.getDouble("DeathsTotal");

            statement.close();
            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT CasesTotal FROM Country WHERE Name = '" + countryName + "' ORDER BY Name";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
                noCases  = results1.getDouble("CasesTotal");

            statement1.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return (noDeath/noCases);
    }

    //get indexes of countries similar to selected country for deaths
    public ArrayList<Integer> getIndexOfCountriesDeaths (double noDeaths, ArrayList<Integer> deathsTotal, ArrayList<Integer> casesTotal) {
        ArrayList<Integer> deaths = new ArrayList<Integer>();
        double minRange = noDeaths - (noDeaths * 0.25);
        double maxRange = noDeaths + (noDeaths * 0.25);

        for (int i = 0; i < deathsTotal.size(); ++i) {
            
            if (((deathsTotal.get(i) / (double) casesTotal.get(i)) >= minRange) && ((deathsTotal.get(i) / (double) casesTotal.get(i)) <= maxRange)) {
                deaths.add(i);
            } 

        }

        return deaths;
    }

    //get number daily cases of a particular country
    public double getCountryCaseDaily(String countryName) {
        double noCaseD = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoNewCases) FROM CountryData WHERE CountryName = '" + countryName + "' GROUP BY CountryName ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                noCaseD   = results.getInt("MAX(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return noCaseD;
    }

    //get indexes of countries similar to selected country for daily cases
    public ArrayList<Integer> getIndexOfCountriesCasesDaily (double noCasesD, ArrayList<Integer> casesTotal) {
        ArrayList<Integer> cases = new ArrayList<Integer>();
        double minRange = 0.0;
        double maxRange = 0.0;

        for (int i = 0; i < casesTotal.size(); ++i) {
            minRange = noCasesD - (noCasesD * 0.25);
            maxRange = noCasesD + (noCasesD * 0.25);
            
            if ((casesTotal.get(i) >= minRange) && (casesTotal.get(i) <= maxRange)) {
                cases.add(i);
            }  

        }

        return cases;
    }

    //get country names with similar daily infection for selected country
    public ArrayList<String> getNameCountriesSimilarDailyInfection(double noCasesDaily) {
        ArrayList<String> countryName = new ArrayList<String>();
        double minRange = noCasesDaily - (noCasesDaily * 0.25);
        double maxRange = noCasesDaily + (noCasesDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM CountryData GROUP BY CountryName HAVING ((MAX(NoNewCases) >= " + minRange + ") AND (MAX(NoNewCases) <= " + maxRange + ")) ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String stateName   = results.getString("CountryName");
                

                // For now we will just store the movieName and ignore the id
                countryName.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryName;
    }

    //get country cases with similar daily infection for selected country
    public ArrayList<Integer> getCountriesSimilarDailyInfection(double noCasesDaily) {
        ArrayList<Integer> casesDaily = new ArrayList<Integer>();
        double minRange = noCasesDaily - (noCasesDaily * 0.25);
        double maxRange = noCasesDaily + (noCasesDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoNewCases) FROM CountryData GROUP BY CountryName HAVING ((MAX(NoNewCases) >= " + minRange + ") AND (MAX(NoNewCases) <= " + maxRange + ")) ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int stateName   = results.getInt("MAX(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesDaily.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesDaily;
    }


    //get number daily deaths of a particular country
    public double getCountryDeathDaily(String countryName) {
        double noDeathD = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoDeaths) FROM CountryData WHERE CountryName = '" + countryName + "' GROUP BY CountryName ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                noDeathD   = results.getInt("MAX(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return noDeathD;
    }

    //get country names with similar daily death for selected country
    public ArrayList<String> getNameCountriesSimilarDailyDeath(double noDeathsDaily) {
        ArrayList<String> countryName = new ArrayList<String>();
        double minRange = noDeathsDaily - (noDeathsDaily * 0.25);
        double maxRange = noDeathsDaily + (noDeathsDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName FROM CountryData GROUP BY CountryName HAVING ((MAX(NoDeaths) >= " + minRange + ") AND (MAX(NoDeaths) <= " + maxRange + ")) ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String stateName   = results.getString("CountryName");
                

                // For now we will just store the movieName and ignore the id
                countryName.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return countryName;
    }

    //get country deaths with similar daily infection for selected country
    public ArrayList<Integer> getCountriesSimilarDailyDeath(double noDeathsDaily) {
        ArrayList<Integer> deathsDaily = new ArrayList<Integer>();
        double minRange = noDeathsDaily - (noDeathsDaily * 0.25);
        double maxRange = noDeathsDaily + (noDeathsDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoDeaths) FROM CountryData GROUP BY CountryName HAVING ((MAX(NoDeaths) >= " + minRange + ") AND (MAX(NoDeaths) <= " + maxRange + ")) ORDER BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int stateName   = results.getInt("MAX(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsDaily.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsDaily;
    }

    //US tab
    //get casesTotal by US region name alphabetically
    public ArrayList<Integer> getCasesTotalUSAlphabetic() {
        ArrayList<Integer> casesTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE CountryName='United States' ORDER BY RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int caseTotal   = results.getInt("CasesTotal");
                

                // For now we will just store the movieName and ignore the id
                casesTotal.add(caseTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesTotal;
    }

    //get deathsTotal by US region name alphabetically
    public ArrayList<Integer> getDeathsTotalUSAlphabetic() {
        ArrayList<Integer> deathsTotal = new ArrayList<Integer>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Region WHERE CountryName='United States' ORDER BY RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int deathTotal   = results.getInt("DeathsTotal");
                

                // For now we will just store the movieName and ignore the id
                deathsTotal.add(deathTotal);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsTotal;
    }

    //get number of cases of a particular US State
    public double getUSStateCase(String stateName) {
        double noCase = 0.0;
        double population = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CasesTotal FROM Region WHERE RegionName = '" + stateName + "' ORDER BY RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
                noCase   = results.getDouble("CasesTotal");

            statement.close();


            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT Population FROM Region WHERE RegionName = '" + stateName + "' ORDER BY RegionName";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);
                population   = results1.getDouble("Population");

            statement1.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return ((noCase/population)*1000000);
    }

     //get indexes of US states similar to selected US state for cases
    public ArrayList<Integer> getIndexOfUSStateCases(double noCasesUS, ArrayList<Integer> casesTotalUS, ArrayList<Double> populationUS) {
        ArrayList<Integer> cases = new ArrayList<Integer>();
        double minRange = noCasesUS - (noCasesUS * 0.25);
        double maxRange = noCasesUS + (noCasesUS * 0.25);

        for (int i = 0; i < casesTotalUS.size(); ++i) {
            
            if ((((casesTotalUS.get(i) / populationUS.get(i)) * 1000000) >= minRange) && (((casesTotalUS.get(i) / populationUS.get(i)) * 1000000)  <= maxRange)) {
                cases.add(i);
            }  

        }

        return cases;
    }

    //get number of deaths of a US state
    public double getUSStateDeath(String stateName) {
        double noDeath = 0.0;
        double noCase = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DeathsTotal FROM Region WHERE RegionName = '" + stateName +  "' ORDER BY RegionName;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

                noDeath   = results.getDouble("DeathsTotal");
               
            statement.close();

            Statement statement1 = connection.createStatement();
            statement1.setQueryTimeout(30);

            // The Query
            String query1 = "SELECT CasesTotal FROM Region WHERE RegionName = '" + stateName + "' ORDER BY RegionName";
            
            // Get Result
            ResultSet results1 = statement1.executeQuery(query1);

                noCase   = results1.getDouble("CasesTotal");
               
            statement1.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return (noDeath / noCase);
    }

    //get indexes of US states similar to selected US state for deaths
    public ArrayList<Integer> getIndexOfUSStateDeaths(double noDeathsUS, ArrayList<Integer> deathsTotalUS, ArrayList<Integer> casesTotalUS) {
        ArrayList<Integer> cases = new ArrayList<Integer>();
        double minRange = noDeathsUS - (noDeathsUS * 0.25);
        double maxRange = noDeathsUS + (noDeathsUS * 0.25);

        for (int i = 0; i < deathsTotalUS.size(); ++i) {
            
            if (((deathsTotalUS.get(i) / (double) casesTotalUS.get(i)) >= minRange) && ((deathsTotalUS.get(i) / (double) casesTotalUS.get(i)) <= maxRange)) {
                cases.add(i);
            }  

        }

        return cases;
    }

    //get number daily cases of a particular US state
    public double getUSStateCaseDaily(String stateName) {
        double noCaseD = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoNewCases) FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (rd.RegionName = '" + stateName + "')) ORDER BY rd.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                noCaseD   = results.getInt("MAX(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return noCaseD;
    }

    //get US state names with similar daily infection for selected US state
    public ArrayList<String> getNameUSStatesSimilarDailyInfection(double noCasesUSDaily) {
        ArrayList<String> stateName = new ArrayList<String>();
        double minRange = noCasesUSDaily - (noCasesUSDaily * 0.25);
        double maxRange = noCasesUSDaily + (noCasesUSDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd.RegionName FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (MAX(NoNewCases) >= " + minRange + ") AND (MAX(NoNewCases) <= " + maxRange + ")) ORDER BY rd.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String name   = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                stateName.add(name);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return stateName;
    }

    //get US state cases with similar daily infection for selected US state
    public ArrayList<Integer> getUSStatesSimilarDailyInfection(double noCasesUSDaily) {
        ArrayList<Integer> casesDaily = new ArrayList<Integer>();
        double minRange = noCasesUSDaily - (noCasesUSDaily * 0.25);
        double maxRange = noCasesUSDaily + (noCasesUSDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoNewCases) FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (MAX(NoNewCases) >= " + minRange + ") AND (MAX(NoNewCases) <= " + maxRange + ")) ORDER BY rd.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int stateName   = results.getInt("MAX(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                casesDaily.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return casesDaily;
    }

    //get number daily deaths of a particular US state
    public double getUSStateDeathDaily(String stateName) {
        double noDeathD = 0.0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoDeaths) FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (rd.RegionName = '" + stateName + "')) ORDER BY rd.RegionName;";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                noDeathD   = results.getInt("MAX(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return noDeathD;
    }

    //get US state names with similar daily death for selected US state
    public ArrayList<String> getNameUSStatesSimilarDailyDeath(double noDeathsUSDaily) {
        ArrayList<String> stateName = new ArrayList<String>();
        double minRange = noDeathsUSDaily - (noDeathsUSDaily * 0.25);
        double maxRange = noDeathsUSDaily + (noDeathsUSDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT rd.RegionName FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (MAX(NoDeaths) >= " + minRange + ") AND (MAX(NoDeaths) <= " + maxRange + ")) ORDER BY rd.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String name   = results.getString("RegionName");
                

                // For now we will just store the movieName and ignore the id
                stateName.add(name);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return stateName;
    }

    //get US state deaths with similar daily death for selected US state
    public ArrayList<Integer> getUSStatesSimilarDailyDeath(double noDeathsUSDaily) {
        ArrayList<Integer> deathsDaily = new ArrayList<Integer>();
        double minRange = noDeathsUSDaily - (noDeathsUSDaily * 0.25);
        double maxRange = noDeathsUSDaily + (noDeathsUSDaily * 0.25);

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT MAX(NoDeaths) FROM RegionData rd JOIN Region r ON rd.RegionName= r.RegionName GROUP BY rd.RegionName HAVING ((r.CountryName = 'United States') AND (MAX(NoDeaths) >= " + minRange + ") AND (MAX(NoDeaths) <= " + maxRange + ")) ORDER BY rd.RegionName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int stateName   = results.getInt("MAX(NoDeaths)");
                

                // For now we will just store the movieName and ignore the id
                deathsDaily.add(stateName);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return deathsDaily;
    }

    
    //Similar tab PAGE 6
    // Dates drop down menu
    public ArrayList<String> getDatesDrop() {
        ArrayList<String> dates = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Date FROM CountryData WHERE (CountryName='Australia' AND LastxDays <= 452)";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String date   = results.getString("Date");
                

                // For now we will just store the movieName and ignore the id
                dates.add(date);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return dates;

    }

    //get corresponding lastxdays from userdate
    public int getXDaysFromUser(String userDate) {
        int x = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT LastxDays FROM CountryData WHERE Date='" + userDate + "' AND CountryName='Australia'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                x  = results.getInt("LastxDays");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return x;

    }    

    //get SUM(NoNewCases) from countryname_drop
    public int getSUMForCountry(String userCountry, int xDay) {
        int x = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(NoNewCases) FROM CountryData WHERE CountryName='" + userCountry + "' AND LastxDays >=" + xDay + " AND LastxDays <= 457 GROUP BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                x  = results.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return x;

    }    

    //get List of cases upto Min date
    public ArrayList<Integer> getInfectionsMin(int userDate) {
        ArrayList<Integer> infections = new ArrayList<Integer>();
        int minDate = userDate - 5;
        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
            
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(NoNewCases) FROM CountryData WHERE (LastxDays >= " + minDate + ") AND (LastxDays <= 457) GROUP BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int cases   = results.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                infections.add(cases);
            }
        

            // Close the statement because we are done with it
            statement.close();
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;

    }

    //get List of cases upto Max date
    public ArrayList<Integer> getInfectionsMax(int userDate) {
        ArrayList<Integer> infections = new ArrayList<Integer>();
        int maxDate = userDate + 5;
        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
            
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT SUM(NoNewCases) FROM CountryData WHERE (LastxDays >= " + maxDate + ") AND (LastxDays <= 457) GROUP BY CountryName";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                int cases   = results.getInt("SUM(NoNewCases)");
                

                // For now we will just store the movieName and ignore the id
                infections.add(cases);
            }
        

            // Close the statement because we are done with it
            statement.close();
            
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return infections;

    }

    //get minDate from userDate
    public String getMinDateFromDate(int userDate) {
        String x = "";

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Date FROM CountryData WHERE LastxDays = " + (userDate + 5) + " AND CountryName = 'Australia'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);


                x  = results.getString("Date");
 
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return x;

    } 

    //get maxDate from userDate
    public String getMaxDateFromDate(int userDate) {
        String x = "";

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Date FROM CountryData WHERE LastxDays = " + (userDate - 5) + " AND CountryName = 'Australia'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

                x  = results.getString("Date");
   
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if ((userDate - 5) == 0) {
            x = "2021-04-23";
        }
        else if ((userDate - 5) == -1) {
            x = "2021-04-24";
        }
        else if ((userDate - 5) == -2) {
            x = "2021-04-25";
        }
        else if ((userDate - 5) == -3) {
            x = "2021-04-26";
        }
        else if ((userDate - 5) == -4) {
            x = "2021-04-27";
        }

        return x;

    }    

}
