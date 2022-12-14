package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples.
 * This page currently:
 *  - Provides a link back to the index page
 *  - Displays the list of movies from the Movies Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page3 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html = html + "<html lang='en-us'>";

        //Start of head
        html = html + "<head>"; 
        
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Infections rates</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<div class = container>";
        html = html + "<img src='banner.jpg' width='100%'/>";
        html = html + "<h1 div class=centered>COVID TRACKER</h1> </div>";
        html = html + "</div>";
        
        html = html + "<ul class='topnav'>";
        html = html + "<li><a href='page1.html'>Homepage</a></li>";
        html = html + "<li><a href='page2.html'>Big Picture</a></li>";
        html = html + "<li><a class=selected href='page3.html'>Infection rates</a></li>";
        html = html + "<li><a href='page4.html'>Death rates</a></li>";
        html = html + "<li><a href='page5.html'>Cumulative Reports</a></li>";
        html = html + "<li><a href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a href='help.html'>Help</a></li>";

        html = html + "</ul>";
        html = html + "<br>";
        
        html = html + "</head>";
         

      //Start of body
        JDBCConnection jdbc = new JDBCConnection();
        html = html + "<body>";

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        html = html + "<form action= '/page3.html' method='post' style='text-align: center'>";
        html = html + "<div class='form group'>";

        String radio = context.formParam("radio");
        if (radio == null) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Australia' name='radio' value='Australia'>";
            html = html + "<label for='Australia' id ='size'>Australia</label>";
        }
        else if (radio.equals("Australia")) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All'>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Australia' name='radio' value='Australia' checked>";
            html = html + "<label for='Australia' id ='size'>Australia</label>";
        }
        else {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Australia' name='radio' value='Australia'>";
            html = html + "<label for='Australia' id ='size'>Australia</label>";
        }
        
        html = html + "</div>"; // closes radio form-group
        html = html + "<br>";
    
        html = html + "<div class='form-group'>";
        
        


        String sort_drop = context.formParam("sort_drop");
        if (sort_drop == null) {
            html = html + "<label for='sort_drop'>Sort by</label>";
            html = html + "&nbsp;";
            html = html + "<select id='sort_drop' name='sort_drop' style='background-color: whitesmoke;'>";
            html = html + "<option>" + "<li>Worst affected</li>" + "</option>";
            html = html + "<option>" + "<li>Least affected</li>" + "</option>";

            
        }
        else if (sort_drop.equals("Least affected")) {
            html = html + "<label for='sort_drop'>Sort by</label>";
            html = html + "&nbsp;";
            html = html + "<select id='sort_drop' name='sort_drop' style='background-color: whitesmoke;'>";
            html = html + "<option>" + "<li>Worst affected</li>" + "</option>";
            html = html + "<option selected>" + "<li>Least affected</li>" + "</option>";
        }
        else {
            html = html + "<label for='sort_drop'>Sort by</label>";
            html = html + "&nbsp;";
            html = html + "<select id='sort_drop' name='sort_drop' style='background-color: whitesmoke;'>";
            html = html + "<option selected>" + "<li>Worst affected</li>" + "</option>";
            html = html + "<option>" + "<li>Least affected</li>" + "</option>";
        }
        
        html = html + "</select>";
        // html = html + "</div>"; //closes sort by form-group
        html = html + "&nbsp;";
        html = html + "&nbsp;";
        html = html + "&nbsp;";
        // html = html + "<div class='form-group'>";
        
        String monthlycases_drop = context.formParam("monthlycases_drop");
        if (monthlycases_drop == null) {
            html = html + "<label for='monthlycases_drop'>Monthly cases</label>";
            html = html + "&nbsp;";
            html = html + "<select id='monthlycases_drop' name='monthlycases_drop' style='background-color: whitesmoke'>";
            html = html + "&nbsp;";
            
            ArrayList<String> months = jdbc.getMonth();
            // html = html + "<option>" + monthlycases_drop + "</option>";
            for (String month: months) {
                html = html + "<option>" + month + "</option>";
            } 
            html = html + "</select>";

        }
        else {
            html = html + "<label for='monthlycases_drop'>Monthly cases</label>";
            html = html + "&nbsp;";
            html = html + "<select id='monthlycases_drop' name='monthlycases_drop' style='background-color: whitesmoke'>";
            
            ArrayList<String> months = jdbc.getMonth();
            html = html + "<option>" + monthlycases_drop + "</option>";
            for (String month: months) {
                if (month == monthlycases_drop){
                
                }
                else {
                    html = html + "<option>" + month + "</option>";
                }
            }
            html = html + "</select>";
        }

        
        html = html + "</div>"; //closes monthly-cases form-group
        html = html + "<br>";
        
        
        html = html + "   <button type='submit' class='btn btn-primary' style='background-color: green; color: white;'>Show data</button>";
        html = html + "</form>";
  

        //html = html + "</th>";


        if (radio == null) {  
            if (sort_drop == null){
                if (monthlycases_drop == null) {

                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['Country', 'Total Infections'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) + "],";
                    };
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
            else if (sort_drop.equals("Worst affected")) {
                if (monthlycases_drop == null) {
                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
            else if (sort_drop.equals("Least affected")) {
                if (monthlycases_drop == null) {
                    ArrayList<String> countryNames = jdbc.getCountryNamesLeast();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalLeast();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateLeast();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesLeast(month);
            
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    ArrayList<String> countryNames = jdbc.getCountryNamesLeast();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalLeast();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateLeast();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesLeast(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
        }
        else if (radio.equals("All")) {
            if (sort_drop == null){
                if (monthlycases_drop == null) {
                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['Country', 'Total Infections'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) + "],";
                    };
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";  

        

                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + myFormat.format(countryNames.get(i)) + "</td>";
                        html = html + "<td>" + casesTotal.get(i) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
            else if (sort_drop.equals("Worst affected")) {
                if (monthlycases_drop == null) {
                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['Country', 'Total Infections'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) + "],";
                    };
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";  

        

                    ArrayList<String> countryNames = jdbc.getCountryNamesWorst();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalWorst();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateWorst();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesWorst(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
            else if (sort_drop.equals("Least affected")) {
                if (monthlycases_drop == null) {
                    ArrayList<String> countryNames = jdbc.getCountryNamesLeast();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalLeast();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateLeast();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly Cases(January 2020) </th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = "January 2020";
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesLeast(month);
            
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
                    // if (monthlycases_drop == null) {
                    //     html = html + "<td> Please select a month </td></tr>";       
                    // }
    
                    //html = html + outputMonthlyCases(monthlycases_drop);
    
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    

                }
                else {
                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['Country', 'Total Infections'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) + "],";
                    };
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";  

        

                    ArrayList<String> countryNames = jdbc.getCountryNamesLeast();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalLeast();
                    ArrayList<String> i_highdates = jdbc.getInfectedHighestDateLeast();
    
            
                    html = html + "<h1 style='text-align: center'>Infections by Country</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Country" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th> Monthly cases(" + monthlycases_drop + ")</th>";
                    html = html + "</tr>"; // closes table header row
                
                    String month = monthlycases_drop;
    
                    ArrayList<Integer> monthly = jdbc.getMonthlyCasesLeast(month);
            
            
    
                    //Printing out table
                    for (int i=0; i< countryNames.size(); ++i) {
                        html = html + "<tr><td>" + countryNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + i_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
            
                    // Finish the List HTML
                    html = html + "</table>";
                    System.out.println(radio);
                    
                }
            }
        }
        else if (radio.equals("Australia")) {
            if (sort_drop ==  null) {
                if(monthlycases_drop == null) {

                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['State Code','State', 'Total Infections'],";
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalWorst();
                    html = html + "['AU-VIC', 'Victoria', " + aus_casesTotal.get(0) + "],";
                    html = html + "['AU-NSW', 'New South Wales', " + aus_casesTotal.get(1) + "],";
                    html = html + "['AU-QLD', 'Queensland', " + aus_casesTotal.get(2) + "],";
                    html = html + "['AU-WA', 'Western Australia', " + aus_casesTotal.get(3) + "],";
                    html = html + "['AU-SA', 'South Australia', " + aus_casesTotal.get(4) + "],";
                    html = html + "['AU-TAS', 'Tasmania', " + aus_casesTotal.get(5) + "],";
                    html = html + "['AU-NT', 'Northern Territory', " + aus_casesTotal.get(6) + "],";
                    html = html + "['AU-ACT', 'Australian Capital Territory', " + aus_casesTotal.get(7) + "]";

                    html = html + "]);";
                    html = html + "var options = { region: 'AU',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 1000px; height: 600px; margin-left:auto; margin-right:auto;'></div>";


                    ArrayList<String> regionNames = jdbc.getAusRegionNamesWorst();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateWorst();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th>" + "Monthly cases(January 2020)" + "</th></tr>";
                    String month = "January 2020";
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesWorst(month);
    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
                else {
                    ArrayList<String> regionNames = jdbc.getAusRegionNamesWorst();
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalWorst();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateWorst();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th>" + "Monthly cases("+ monthlycases_drop + ")</th></tr>";

                    
                    String month = monthlycases_drop;
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesWorst(month);

    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
            }
            else if (sort_drop.equals("Worst affected")) {
                if(monthlycases_drop == null) {
                    ArrayList<String> regionNames = jdbc.getAusRegionNamesWorst();
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalWorst();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateWorst();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th>" + "Monthly cases(January 2020)" + "</th></tr>";
                    String month = "January 2020";
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesWorst(month);
    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
                else {

                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['State Code','State', 'Total Infections'],";
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalWorst();
                    html = html + "['AU-VIC', 'Victoria', " + aus_casesTotal.get(0) + "],";
                    html = html + "['AU-NSW', 'New South Wales', " + aus_casesTotal.get(1) + "],";
                    html = html + "['AU-QLD', 'Queensland', " + aus_casesTotal.get(2) + "],";
                    html = html + "['AU-WA', 'Western Australia', " + aus_casesTotal.get(3) + "],";
                    html = html + "['AU-SA', 'South Australia', " + aus_casesTotal.get(4) + "],";
                    html = html + "['AU-TAS', 'Tasmania', " + aus_casesTotal.get(5) + "],";
                    html = html + "['AU-NT', 'Northern Territory', " + aus_casesTotal.get(6) + "],";
                    html = html + "['AU-ACT', 'Australian Capital Territory', " + aus_casesTotal.get(7) + "]";
               
                    html = html + "]);";
                    html = html + "var options = { region: 'AU',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 1000px; height: 600px; margin-left:auto; margin-right:auto;'></div>";


                    ArrayList<String> regionNames = jdbc.getAusRegionNamesWorst();
                    //ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalWorst();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateWorst();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th>" + "Monthly cases(" + monthlycases_drop + ")</th>";
                    
                    String month = monthlycases_drop;
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesWorst(month);

    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
            }
            else if (sort_drop.equals("Least affected")){
                if(monthlycases_drop == null) {
                    ArrayList<String> regionNames = jdbc.getAusRegionNamesLeast();
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalLeast();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateLeast();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th></tr>";
                    html = html + "<th>" + "Monthly cases(January 2020)" + "</th></tr>";
                    String month = "January 2020";
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesLeast(month);
    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
                else {

                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap);";
                    html = html + "function drawRegionsMap() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['State Code','State', 'Total Infections'],";
                    ArrayList<Integer> aus_casesTotal1 = jdbc.getAusCasesTotalWorst();
                    html = html + "['AU-VIC', 'Victoria', " + aus_casesTotal1.get(0) + "],";
                    html = html + "['AU-NSW', 'New South Wales', " + aus_casesTotal1.get(1) + "],";
                    html = html + "['AU-QLD', 'Queensland', " + aus_casesTotal1.get(2) + "],";
                    html = html + "['AU-WA', 'Western Australia', " + aus_casesTotal1.get(3) + "],";
                    html = html + "['AU-SA', 'South Australia', " + aus_casesTotal1.get(4) + "],";
                    html = html + "['AU-TAS', 'Tasmania', " + aus_casesTotal1.get(5) + "],";
                    html = html + "['AU-NT', 'Northern Territory', " + aus_casesTotal1.get(6) + "],";
                    html = html + "['AU-ACT', 'Australian Capital Territory', " + aus_casesTotal1.get(7) + "]";
                    
                    html = html + "]);";
                    html = html + "var options = { region: 'AU',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 1000px; height: 600px; margin-left:auto; margin-right:auto;'></div>";


                    ArrayList<String> regionNames = jdbc.getAusRegionNamesLeast();
                    ArrayList<Integer> aus_casesTotal = jdbc.getAusCasesTotalLeast();
                    ArrayList<String> ausi_highdates = jdbc.getAusInfectedHighestDateLeast();
    
                    html = html + "<h1 style='text-align: center'>Infections by Region (Australia)</h1>" + "<table id='infections'>";
                    html = html + "<tr><th>" + "Region" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Date of Highest Infection" + "</th>";
                    html = html + "<th>" + "Monthly cases(" + monthlycases_drop + ")</th></tr>";
                    
                    String month = monthlycases_drop;
                    ArrayList<Integer> monthly = jdbc.getAusMonthlyCasesLeast(month);

    
                    for (int i=0; i< regionNames.size(); ++i) {
                        html = html + "<tr><td>" + regionNames.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(aus_casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + ausi_highdates.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(monthly.get(i)) + "</td></tr>";
                    }
    
    
                    html = html + "</table>";
                    
                }
            }
        }
       
        html = html + "</body>";


        html = html + "<footer id='footer345'>";

        html = html + "<div class = leftcolumn>";
        html = html + "<ul>";
        html = html + "<h3>Contact us</h3>";
        html = html + "<li>Email: s3866903@student.rmit.edu.au</li>";
        html = html + "<pre style='font-family:Arial'>           s3866910@student.rmit.edu.au</pre>";
        html = html + "<li>Number: +8801859547004</li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "<div class = rightcolumn>";
        html = html + "<ul>";
        html = html + "<h3>Send us feedback</h3>";
        html = html + "<li>Email us your thoughts on how to improve the website.</li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "</footer>";
        //html = html + "</div>";

        // Finish the HTML webpage
        html = html + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
