package app;

import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import io.javalin.http.Context;
import io.javalin.http.Handler;

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
public class Page5 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html = html + "<html lang='en-us'>";

        //Start of head
        html = html + "<head>"; 
        
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Cumulative Reports</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<div class = container>";
        
        html = html + "<img src='banner.jpg' class='exp' width='100%'/>";
        html = html + "<h1 div class=centered>COVID TRACKER</h1> </div>";
        html = html + "</div>"; // closes container div
        
        html = html + "<ul class='topnav'>";
        html = html + "<li><a href='page1.html'>Homepage</a></li>";
        html = html + "<li><a href='page2.html'>Big Picture</a></li>";
        html = html + "<li><a href='page3.html'>Infection rates</a></li>";
        html = html + "<li><a href='page4.html'>Death rates</a></li>";
        html = html + "<li><a class=selected href='page5.html'>Cumulative Reports</a></li>";
        html = html + "<li><a href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a href='help.html'>Help</a></li>";

        html = html + "</ul>";
        
        html = html + "</head>";
        
        // Add the body
        html = html + "<body>";
        html = html + "<br>";
        JDBCConnection jdbc = new JDBCConnection();


        html = html + "<form action= '/page5.html' method='post' style='text-align: center'>";


        html = html + "<div class='form group'>";
        String radio = context.formParam("radio");
        if (radio == null) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_Climate' name='radio' value='Similar_Climate'>";
            html = html + "<label for='Similar_Climate' id ='size'>Similar Climate</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Surrounding_Countries' name='radio' value='Surrounding_Countries'>";
            html = html + "<label for='Surrounding_Countries' id ='size'>Surrounding Countries</label>";
        }
        else if (radio.equals("Similar_Climate")) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All'>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_Climate' name='radio' value='Similar_Climate' checked>";
            html = html + "<label for='Similar_Climate' id ='size'>Similar Climate</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Surrounding_Countries' name='radio' value='Surrounding_Countries'>";
            html = html + "<label for='Surrounding_Countries' id ='size'>Surrounding Countries</label>";
        }
        else if (radio.equals("Surrounding_Countries")){
            html = html + "<input type= 'radio' id='All' name='radio' value='All'>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_Climate' name='radio' value='Similar_Climate'>";
            html = html + "<label for='Similar_Climate' id ='size'>Similar Climate</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Surrounding_Countries' name='radio' value='Surrounding_Countries' checked>";
            html = html + "<label for='Surrounding_Countries' id ='size'>Surrounding Countries</label>";
        }
        else {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_Climate' name='radio' value='Similar_Climate'>";
            html = html + "<label for='Similar_Climate' id ='size'>Similar Climate</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Surrounding_Countries' name='radio' value='Surrounding_Countries'>";
            html = html + "<label for='Surrounding_Countries' id ='size'>Surrounding Countries</label>";

        }
        html = html + "</div>";//closes form group

        html = html + "<br>";

        html = html + "<div class='form-group'>";

        String countrynames_drop = context.formParam("countrynames_drop");


        if (countrynames_drop == null) {
            html = html + "<label for='countrynames_drop'>Select a country</label>";
            html = html + "&nbsp;";
            html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
            
            ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
            html = html + "<option>All Countries</option>";
            for (String countryName: countryNames) {
                html = html + "<option>" + countryName + "</option>";
            } 
            html = html + "</select>";
        }
        else if (countrynames_drop.equals("All Countries")) {  
            html = html + "<label for='countrynames_drop'>Select a country</label>";
            html = html + "&nbsp;";
            html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
            
            ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
            html = html + "<option>All Countries</option>";
            for (String countryName: countryNames) {
                html = html + "<option>" + countryName + "</option>";
            } 
            html = html + "</select>";

        }
        else {
            html = html + "<label for='countrynames_drop'>Select a country</label>";
            html = html + "&nbsp;";
            html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
            
            ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
            html = html + "<option>" + countrynames_drop + "</option>";
            html = html + "<option>All Countries</option>";
            for (String countryName: countryNames) {
                if (countryName == countrynames_drop){
                    int j = countryName.indexOf(countrynames_drop);
                    
                }
                else {
                    html = html + "<option>" + countryName + "</option>";
                }
            }
            html = html + "</select>";
        }
        html = html + "&nbsp;";
        html = html + "&nbsp;";
        html = html + "&nbsp;";

        String last_textbox = context.formParam("last_textbox");
        int last = 457;

        if (last_textbox == null) {
            html = html + "<label for='last_textbox'>Last x days</label>";
            html = html + "&nbsp;";
            html = html + "<input class='form-control' type = 'number' id='last_textbox' name='last_textbox' value='" + last + "''>";
        }
        else if (last_textbox.equals("")) {
            html = html + "<label for='last_textbox'>Last x days</label>";
            html = html + "&nbsp;";
            html = html + "<input class='form-control' type = 'number' id='last_textbox' name='last_textbox' value='" + last + "''>";

        }
        else {
            last = Integer.parseInt(last_textbox);
            html = html + "<label for='last_textbox'>Last x days</label>";
            html = html + "&nbsp;";
            html = html + "<input class='form-control' type = 'number' id='last_textbox' name='last_textbox' value=" + last + ">";
        }

        html = html + "&nbsp;";
        html = html + "&nbsp;";
        html = html + "&nbsp;";

        String within_textbox = context.formParam("within_textbox");
        int within = 3500;

        if (radio == null) {

        }
        else if (radio.equals("Surrounding_Countries")) {
            if (within_textbox == null) {
                html = html + "<label for='last_textbox'>Within x km</label>";
                html = html + "&nbsp;";
                html = html + "<input class='form-control' type = 'number' id='last_textbox' name='within_textbox'>";
            }
            else if (within_textbox.equals("")) {
                html = html + "<label for='last_textbox'>Within x km</label>";
                html = html + "&nbsp;";
                html = html + "<input class='form-control' type = 'number' id='last_textbox' name='within_textbox' value=" + within + ">";
            }
            else {
                within = Integer.parseInt(within_textbox);
                html = html + "<label for='last_textbox'>Within x km</label>";
                html = html + "&nbsp;";
                html = html + "<input class='form-control' type = 'number' id='last_textbox' name='within_textbox' value=" + within + ">";
            }
        }

    

        

        html = html + "</div>"; //closes country name and last_texbox form-group

        
        html = html + "<br>";

        html = html + "   <button type='submit' class='btn btn-primary' style='background-color: green; color:white;'>Show data</button>";
        html = html + "</form>";

        DecimalFormat df = new DecimalFormat("0.000000");
        df.setRoundingMode(RoundingMode.CEILING);
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);


        if (radio == null) {
            if (last == 457) {
                if (countrynames_drop == null) {
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
                    html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "],";
                    }
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();

                    html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    for (int i=0; i< countryname.size(); ++i) {
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                    }
                    html = html + "</table>";
                    System.out.println(countrynames_drop);

                    


                }
                else if (countrynames_drop.equals("All Countries")) {
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
                    html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "],";
                    }
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();

                    html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    for (int i=0; i< countryname.size(); ++i) {
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td></tr>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                    }
                    html = html + "</table>";
                }
                else {
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    int i = countryname.indexOf(countrynames_drop);
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
                    html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
               
                    html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "]";
                   
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();

                    

                    html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    html = html + "<tr><td>" + countryname.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                    html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                    html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                    html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";

                    html = html + "</table>";

                }
            }
            else if (last != 457) { 
                last = Integer.parseInt(last_textbox);
                if (last <= 0 ) {
                    if (countrynames_drop == null) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                       
                    }
                    else if (countrynames_drop.equals("All Countries")) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        int i = countryname.indexOf(countrynames_drop);
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
    
                    }
                }
                else { //last x days greater than 0
                    if (countrynames_drop == null) {

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                        for (int i = 0; i < countryNames1.size() - 1; ++i) {
                            html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "],";
                        }
                        html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";
                   
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        for (int i=0; i< countryname.size(); ++i) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                            html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                        }
                        html = html + "</table>";
                        System.out.println(countrynames_drop);
    
                    }
                    else if (countrynames_drop.equals("All Countries")) {

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                        for (int i = 0; i < countryNames1.size() - 1; ++i) {
                            html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "],";
                        }
                        html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";
                   
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        for (int i=0; i< countryname.size(); ++i) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                            html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                        }
                        html = html + "</table>";
                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        int i = countryname.indexOf(countrynames_drop);

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                     
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "]";
                       
                   
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
    
                        html = html + "</table>";
    
                    }

                }
                
            }
           
        }
        else if (radio.equals("All")) {
            if (last == 457) {
                if (countrynames_drop == null) {
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();

                    html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    for (int i=0; i< countryname.size(); ++i) {
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                    }
                    html = html + "</table>";
                    System.out.println(countrynames_drop);

                }
                else if (countrynames_drop.equals("All Countries")) {
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
                    html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                    ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                    for (int i = 0; i < countryNames1.size() - 1; ++i) {
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "],";
                    }
                    html = html + "['" + countryNames1.get(189)+ "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";
               
                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();

                    html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    for (int i=0; i< countryname.size(); ++i) {
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                    }
                    html = html + "</table>";
                }
                else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        int i = countryname.indexOf(countrynames_drop);

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                     
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "]";
                       
                   
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();


                    html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    html = html + "<tr><td>" + countryname.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                    html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                    html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                    html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";

                    html = html + "</table>";

                }
            }
            else if (last != 457) { 
                last = Integer.parseInt(last_textbox);
                if (last <= 0 ) {
                    if (countrynames_drop == null) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                       
                    }
                    else if (countrynames_drop.equals("All Countries")) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        int i = countryname.indexOf(countrynames_drop);
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
    
                    }
                }
                else { //last x days greater than 0
                    if (countrynames_drop == null) {
                        
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        for (int i=0; i< countryname.size(); ++i) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                            html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                        }
                        html = html + "</table>";
                        System.out.println(countrynames_drop);
                    }
                    else if (countrynames_drop.equals("All Countries")) {

                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                        for (int j = 0; j < countryNames1.size() - 1; ++j) {

                            html = html + "['" + countryNames1.get(j) + "', " + casesTotal1.get(j) / population1.get(j) + ", " + deathsTotal1.get(j) / population1.get(j) + "],";

                        }
                        html = html + "['" + countryNames1.get(189) + "', " + casesTotal1.get(189) / population1.get(189) + ", " + deathsTotal1.get(189) / population1.get(189) + "]";

                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for all countries</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        for (int i=0; i< countryname.size(); ++i) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                            html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                            html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                            html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
                        }
                        html = html + "</table>";

                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        int i = countryname.indexOf(countrynames_drop);

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        ArrayList<String> countryNames1 = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> casesTotal1 = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Integer> deathsTotal1 = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Double> population1 = jdbc.getCountryPopulationAlphabetic();
                     
                        html = html + "['" + countryNames1.get(i) + "', " + casesTotal1.get(i) / population1.get(i) + ", " + deathsTotal1.get(i) / population1.get(i) + "]";
                       
                   
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
    
                        html = html + "<h1 style='text-align: center'>Cumulative reports for "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "<tr><td>" + countryname.get(i) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(i)) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(i) / population.get(i))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(i) / population.get(i))*1000000)) + "</td></tr>";
    
                        html = html + "</table>";
    
                    }

                }
                
            }
            
            
        }
        else if (radio.equals("Surrounding_Countries")) {
            if (within == 3500) {
                if (last == 457) {
                    if (countrynames_drop == null) {
                        html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                        html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                    }
                    else if (countrynames_drop.equals("All Countries")) {
                        html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                        html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                        double lat1 = jdbc.getCountryLat(countrynames_drop);
                        double lon1 = jdbc.getCountryLon(countrynames_drop);
                        ArrayList<Double> latall = jdbc.getCountriesLat();
                        ArrayList<Double> lonall = jdbc.getCountriesLon();
                        ArrayList<String> nameswithin = jdbc.getNamesWithIn(countryname, lat1, lon1, latall, lonall, within);
                        int count = 0;

                        int i = countryname.indexOf(countrynames_drop);

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        
                        for (int j = 0; j < nameswithin.size() - 1 ; ++j) {
                            count = countryname.indexOf(nameswithin.get(j));
                            html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "],";

                        }
                        count = countryname.indexOf(nameswithin.get(nameswithin.size() - 1));
                        html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";

                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
    
                        html = html + "<h1 style='text-align: center'>Surrounding countries for " + countrynames_drop + "</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        
                            // html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            // html = html + "<td>" + casesTotal.get(i) + "</td>";
                            // html = html + "<td>" + deathsTotal.get(i) + "</td>";
                            // html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                            // html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                            // html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td></tr>";

                            for (int j = 0; j < nameswithin.size(); ++j) {
                                count = countryname.indexOf(nameswithin.get(j));
                                html = html + "<tr><td>" + countryname.get(count) + "</td>";
                                html = html + "<td>" + myFormat.format(casesTotal.get(count)) + "</td>";
                                html = html + "<td>" + myFormat.format(deathsTotal.get(count)) + "</td>";
                                html = html + "<td>" + df.format(deathsTotal.get(count) / ((double) casesTotal.get(count))) + "</td>";
                                html = html + "<td>" + df.format(deathsTotal.get(count) / population.get(count)) + "</td>";
                                html = html + "<td>" + df.format(casesTotal.get(count) / population.get(count)) + "</td>";
                                html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count) / population.get(count))*1000000)) + "</td>";
                                html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count) / population.get(count))*1000000)) + "</td></tr>";
                                
                            }
                            

                        html = html + "</table>";

                    }
                }
                else if (last != 457) {
                    last = Integer.parseInt(last_textbox);
                    if (last <= 0) { 
                        if (countrynames_drop == null) {
                            ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                            ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                            ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                            ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
        
                            html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                           
                        }
                        else if (countrynames_drop.equals("All Countries")) {
                            ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                            ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                            ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                            ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
        
                            html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }
                        else {
                            ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                            ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                            ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                            ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
        
                            int i = countryname.indexOf(countrynames_drop);
        
                            html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }  
                    }            
                    else {//last x days greater than 0
                        if (countrynames_drop == null) {
                            html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }
                        else if (countrynames_drop.equals("All Countries")) {
                            html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }
                        else {
                            ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                            ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                            ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                            ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                            double lat1 = jdbc.getCountryLat(countrynames_drop);
                            double lon1 = jdbc.getCountryLon(countrynames_drop);
                            ArrayList<Double> latall = jdbc.getCountriesLat();
                            ArrayList<Double> lonall = jdbc.getCountriesLon();
                            ArrayList<String> nameswithin = jdbc.getNamesWithIn(countryname, lat1, lon1, latall, lonall, within);
                            int count = 0;
    
                            int i = countryname.indexOf(countrynames_drop);

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
                            html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                            
                            for (int j = 0; j < nameswithin.size() - 1 ; ++j) {
                                count = countryname.indexOf(nameswithin.get(j));
                                html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "],";
    
                            }
                            count = countryname.indexOf(nameswithin.get(nameswithin.size() - 1));
                            html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";
    
                            html = html + "]);";
                            html = html + "var options = {};";
                            html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                            html = html + "chart.draw(data, options);";
                            html = html + "}";
                            html = html + "</script>";
                            html = html + "<br>";
                
                            html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
        
                            html = html + "<h1 style='text-align: center'>Surrounding countries for " + countrynames_drop + "</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            
                                // html = html + "<tr><td>" + countryname.get(i) + "</td>";
                                // html = html + "<td>" + casesTotal.get(i) + "</td>";
                                // html = html + "<td>" + deathsTotal.get(i) + "</td>";
                                // html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                                // html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                                // html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td></tr>";
    
                                for (int j = 0; j < nameswithin.size(); ++j) {
                                    count = countryname.indexOf(nameswithin.get(j));
                                    html = html + "<tr><td>" + countryname.get(count) + "</td>";
                                    html = html + "<td>" + myFormat.format(casesTotal.get(count)) + "</td>";
                                    html = html + "<td>" + myFormat.format(deathsTotal.get(count)) + "</td>";
                                    html = html + "<td>" + df.format(deathsTotal.get(count) / ((double) casesTotal.get(count))) + "</td>";
                                    html = html + "<td>" + df.format(deathsTotal.get(count) / population.get(count)) + "</td>";
                                    html = html + "<td>" + df.format(casesTotal.get(count) / population.get(count)) + "</td>";
                                    html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count) / population.get(count))*1000000)) + "</td>";
                                    html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count) / population.get(count))*1000000)) + "</td></tr>";
                                    
                                }
                                
    
                            html = html + "</table>";
    
                        }
                    }
                }
                    
                
            }
            else if (within != 3500) {
                within = Integer.parseInt(within_textbox);
                if (within <= 0) {
                    html = html + "<p style='text-align: center; color: red'>Please choose a value for 'Within x km' greater than or equals to 1.</p>";
                    html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    html = html + "</table>";


                }
                else {//get within greater than 0
                    if (last == 457) {
                        if (countrynames_drop == null) {
                            html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }
                        else if (countrynames_drop.equals("All Countries")) {
                            html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                            html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
        
                            html = html + "</table>";
                        }
                        else {
                            ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                            ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                            ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                            ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                            double lat1 = jdbc.getCountryLat(countrynames_drop);
                            double lon1 = jdbc.getCountryLon(countrynames_drop);
                            ArrayList<Double> latall = jdbc.getCountriesLat();
                            ArrayList<Double> lonall = jdbc.getCountriesLon();
                            ArrayList<String> nameswithin = jdbc.getNamesWithIn(countryname, lat1, lon1, latall, lonall, within);
                            int count = 0;
    
                            int i = countryname.indexOf(countrynames_drop);

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
                            html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                            
                            if (nameswithin.size() == 1) {
                                count = countryname.indexOf(nameswithin.get(0));
                                html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";
                            }
                            else {
                                for (int j = 0; j < nameswithin.size() - 1; ++j) {
                                    count = countryname.indexOf(nameswithin.get(j));
                                    html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "],";
                                }
                                count = countryname.indexOf(nameswithin.get(nameswithin.size() - 1));
                                html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";
                            }
    
                            html = html + "]);";
                            html = html + "var options = {};";
                            html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                            html = html + "chart.draw(data, options);";
                            html = html + "}";
                            html = html + "</script>";
                            html = html + "<br>";
                
                            html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
        
                            html = html + "<h1 style='text-align: center'>Surrounding countries for " + countrynames_drop + "</h1>" + "<table id='cumulative_reports'>";
                            html = html + "<tr><th>" + "Country Name" + "</th>";
                            html = html + "<th>" + "Total Infections" + "</th>";
                            html = html + "<th>" + "Total Deaths" + "</th>";
                            html = html + "<th>Deaths:Infected</th>";
                            html = html + "<th>Deaths:Population</th>";
                            html = html + "<th>Infected:Population</th>";
                            html = html + "<th>Infections per 1M pop</th>";
                            html = html + "<th>Deaths per 1M pop</th>";
                            html = html + "</tr>"; // closes table header row
    
                                for (int j = 0; j < nameswithin.size(); ++j) {
                                    count = countryname.indexOf(nameswithin.get(j));
                                    html = html + "<tr><td>" + countryname.get(count) + "</td>";
                                    html = html + "<td>" + myFormat.format(casesTotal.get(count)) + "</td>";
                                    html = html + "<td>" + myFormat.format(deathsTotal.get(count)) + "</td>";
                                    html = html + "<td>" + df.format(deathsTotal.get(count) / ((double) casesTotal.get(count))) + "</td>";
                                    html = html + "<td>" + df.format(deathsTotal.get(count) / population.get(count)) + "</td>";
                                    html = html + "<td>" + df.format(casesTotal.get(count) / population.get(count)) + "</td>";
                                    html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count) / population.get(count))*1000000)) + "</td>";
                                    html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count) / population.get(count))*1000000)) + "</td></tr>";
                                    
                                }
                                
    
                            html = html + "</table>";
    
                        }
                    }
                    else if (last != 457) {
                        last = Integer.parseInt(last_textbox);
                        if (last <= 0) { 
                            if (countrynames_drop == null) {
                                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                                ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
            
                                html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                                html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                html = html + "</table>";
                               
                            }
                            else if (countrynames_drop.equals("All Countries")) {
                                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                                ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
            
                                html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                                html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                html = html + "</table>";
                            }
                            else {
                                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                                ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
            
                                int i = countryname.indexOf(countrynames_drop);
            
                                html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                                html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                html = html + "</table>";
                            }  
                        }            
                        else {//last x days greater than 0
                            if (countrynames_drop == null) {
                                html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                                html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                html = html + "</table>";
                            }
                            else if (countrynames_drop.equals("All Countries")) {
                                html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                                html = html + "<h1 style='text-align: center'>Surrounding countries for (....)</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                html = html + "</table>";
                            }
                            else {
                                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                                ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                                ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                                double lat1 = jdbc.getCountryLat(countrynames_drop);
                                double lon1 = jdbc.getCountryLon(countrynames_drop);
                                ArrayList<Double> latall = jdbc.getCountriesLat();
                                ArrayList<Double> lonall = jdbc.getCountriesLon();
                                ArrayList<String> nameswithin = jdbc.getNamesWithIn(countryname, lat1, lon1, latall, lonall, within);
                                int count = 0;
        
                                int i = countryname.indexOf(countrynames_drop);

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
                                html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                                
                                if (nameswithin.size() == 1) {
                                    count = countryname.indexOf(nameswithin.get(0));
                                    html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";
                                }
                                else {
                                    for (int j = 0; j < nameswithin.size() - 1; ++j) {
                                        count = countryname.indexOf(nameswithin.get(j));
                                        html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "],";
                                    }
                                    count = countryname.indexOf(nameswithin.get(nameswithin.size() - 1));
                                    html = html + "['" + countryname.get(count) + "', " + casesTotal.get(count) / population.get(count) + ", " + deathsTotal.get(count) / population.get(count) + "]";
                                }
                                html = html + "]);";
                                html = html + "var options = {};";
                                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                                html = html + "chart.draw(data, options);";
                                html = html + "}";
                                html = html + "</script>";
                                html = html + "<br>";
            
                                html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
            
                                html = html + "<h1 style='text-align: center'>Surrounding countries for " + countrynames_drop + "</h1>" + "<table id='cumulative_reports'>";
                                html = html + "<tr><th>" + "Country Name" + "</th>";
                                html = html + "<th>" + "Total Infections" + "</th>";
                                html = html + "<th>" + "Total Deaths" + "</th>";
                                html = html + "<th>Deaths:Infected</th>";
                                html = html + "<th>Deaths:Population</th>";
                                html = html + "<th>Infected:Population</th>";
                                html = html + "<th>Infections per 1M pop</th>";
                                html = html + "<th>Deaths per 1M pop</th>";
                                html = html + "</tr>"; // closes table header row
            
                                
                                    // html = html + "<tr><td>" + countryname.get(i) + "</td>";
                                    // html = html + "<td>" + casesTotal.get(i) + "</td>";
                                    // html = html + "<td>" + deathsTotal.get(i) + "</td>";
                                    // html = html + "<td>" + df.format(deathsTotal.get(i) / ((double) casesTotal.get(i))) + "</td>";
                                    // html = html + "<td>" + df.format(deathsTotal.get(i) / population.get(i)) + "</td>";
                                    // html = html + "<td>" + df.format(casesTotal.get(i) / population.get(i)) + "</td></tr>";
        
                                    for (int j = 0; j < nameswithin.size(); ++j) {
                                        count = countryname.indexOf(nameswithin.get(j));
                                        html = html + "<tr><td>" + countryname.get(count) + "</td>";
                                        html = html + "<td>" + myFormat.format(casesTotal.get(count)) + "</td>";
                                        html = html + "<td>" + myFormat.format(deathsTotal.get(count)) + "</td>";
                                        html = html + "<td>" + df.format(deathsTotal.get(count) / ((double) casesTotal.get(count))) + "</td>";
                                        html = html + "<td>" + df.format(deathsTotal.get(count) / population.get(count)) + "</td>";
                                        html = html + "<td>" + df.format(casesTotal.get(count) / population.get(count)) + "</td>";
                                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count) / population.get(count))*1000000)) + "</td>";
                                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count) / population.get(count))*1000000)) + "</td></tr>";
                                        
                                    }
                                    
        
                                html = html + "</table>";
        
                            }
                        }
                    }
                }
            }
        }
        else if (radio.equals("Similar_Climate")) {
            if (last == 457) {
                if (countrynames_drop == null) {
                    html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                    html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    html = html + "</table>";

                }
                else if (countrynames_drop.equals("All Countries")) {
                    html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                    html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    html = html + "</table>";
                }
                else {

                        
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                    ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                    ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                    double lat = jdbc.getCountryLat(countrynames_drop);
                    ArrayList<Double> lats = jdbc.getCountriesLat();
                    ArrayList<Integer> count = jdbc.getIndexOfCountryWithSimilarLat(lat, lats);

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
                    html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                    
                    for (int j = 0; j < count.size() - 1; ++j) {
                        html = html + "['" + countryname.get(count.get(j)) + "', " + casesTotal.get(count.get(j)) / population.get(count.get(j)) + ", " + deathsTotal.get(count.get(j)) / population.get(count.get(j)) + "],";
                    }
                    html = html + "['" + countryname.get(count.get(count.size() - 1)) + "', " + casesTotal.get(count.get(count.size() - 1)) / population.get(count.get(count.size() - 1)) + ", " + deathsTotal.get(count.get(count.size() - 1)) / population.get(count.get(count.size() - 1)) + "]";

                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";

                    html = html + "<h1 style='text-align: center'>Countries sharing similar climate with "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Total Infections" + "</th>";
                    html = html + "<th>" + "Total Deaths" + "</th>";
                    html = html + "<th>Deaths:Infected</th>";
                    html = html + "<th>Deaths:Population</th>";
                    html = html + "<th>Infected:Population</th>";
                    html = html + "<th>Infections per 1M pop</th>";
                    html = html + "<th>Deaths per 1M pop</th>";
                    html = html + "</tr>"; // closes table header row

                    for (int i = 0; i < count.size(); ++i){
                        html = html + "<tr><td>" + countryname.get(count.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(count.get(i))) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(count.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(count.get(i)) / ((double) casesTotal.get(count.get(i)))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(count.get(i)) / population.get(count.get(i))) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(count.get(i)) / population.get(count.get(i))) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count.get(i)) / population.get(count.get(i)))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count.get(i)) / population.get(count.get(i)))*1000000)) + "</td></tr>";
                    }
                    html = html + "</table>";

                }
            }
            else if (last != 457) { 
                last = Integer.parseInt(last_textbox);
                if (last <= 0 ) {
                    if (countrynames_drop == null) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                       
                    }
                    else if (countrynames_drop.equals("All Countries")) {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                    }
                    else {
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
    
                        int i = countryname.indexOf(countrynames_drop);
    
                        html = html + "<p style='text-align: center; color: red'>Please give a value for 'Last x days' more than or equals to 1.</p>";
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
    
                    }
                }
                else { //last x days greater than 0
                    if (countrynames_drop == null) {
                        
                        html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
    
                    }
                    else if (countrynames_drop.equals("All Countries")) {

                        html = html + "<p style='text-align: center; color: red'>Please select a country.</p>";
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with (....)</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
    
                        html = html + "</table>";
                        
                    }
                    else {

                            
                        ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                        ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalLastxAlphabetic(last);
                        ArrayList<Integer> casesTotal = jdbc.getCasesTotalLastxAlphabetic(last);
                        ArrayList<Double> population = jdbc.getCountryPopulationAlphabetic();
                        double lat = jdbc.getCountryLat(countrynames_drop);
                        ArrayList<Double> lats = jdbc.getCountriesLat();
                        ArrayList<Integer> count = jdbc.getIndexOfCountryWithSimilarLat(lat, lats);
    
                        //int i = countryname.indexOf(countrynames_drop);

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
                        html = html + "['Country', 'Infected:Population', 'Deaths:Population'],";
                        
                        for (int j = 0; j < count.size() - 1 ; ++j) {
                            html = html + "['" + countryname.get(count.get(j)) + "', " + casesTotal.get(count.get(j)) / population.get(count.get(j)) + ", " + deathsTotal.get(count.get(j)) / population.get(count.get(j)) + "],";
                        }
                        html = html + "['" + countryname.get(count.get(count.size() - 1)) + "', " + casesTotal.get(count.get(count.size() - 1)) / population.get(count.get(count.size() - 1)) + ", " + deathsTotal.get(count.get(count.size() - 1)) / population.get(count.get(count.size() - 1)) + "]";
    
                        html = html + "]);";
                        html = html + "var options = {};";
                        html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                        html = html + "chart.draw(data, options);";
                        html = html + "}";
                        html = html + "</script>";
                        html = html + "<br>";
            
                        html = html + "<div id='regions_div' style='width: 900px; height: 500px; margin-left:auto; margin-right:auto;'></div>";
    
                        html = html + "<h1 style='text-align: center'>Countries sharing similar climate with "+ countrynames_drop +"</h1>" + "<table id='cumulative_reports'>";
                        html = html + "<tr><th>" + "Country Name" + "</th>";
                        html = html + "<th>" + "Total Infections" + "</th>";
                        html = html + "<th>" + "Total Deaths" + "</th>";
                        html = html + "<th>Deaths:Infected</th>";
                        html = html + "<th>Deaths:Population</th>";
                        html = html + "<th>Infected:Population</th>";
                        html = html + "<th>Infections per 1M pop</th>";
                        html = html + "<th>Deaths per 1M pop</th>";
                        html = html + "</tr>"; // closes table header row
                        
                        for (int i = 0; i < count.size(); ++i) {
                        html = html + "<tr><td>" + countryname.get(count.get(i)) + "</td>";
                        html = html + "<td>" + myFormat.format(casesTotal.get(count.get(i))) + "</td>";
                        html = html + "<td>" + myFormat.format(deathsTotal.get(count.get(i))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(count.get(i)) / ((double) casesTotal.get(count.get(i)))) + "</td>";
                        html = html + "<td>" + df.format(deathsTotal.get(count.get(i)) / population.get(count.get(i))) + "</td>";
                        html = html + "<td>" + df.format(casesTotal.get(count.get(i)) / population.get(count.get(i))) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((casesTotal.get(count.get(i)) / population.get(count.get(i)))*1000000)) + "</td>";
                        html = html + "<td>" +  myFormat.format(Math.round((deathsTotal.get(count.get(i)) / population.get(count.get(i)))*1000000)) + "</td></tr>";
                        }
                        html = html + "</table>";
    
                    }

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
        html = html + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
