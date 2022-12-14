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
public class Page6 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page6.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        html = html + "<html lang='en-us'>";

        // Add some Header information
        html = html + "<head>"; 
        

        html = html + "<meta charset='UTF-8'>";
        html = html + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Comparisons</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<div class = container>";
        html = html + "<img src='banner.jpg' width='100%'/>";
        html = html + "<h1 div class=centered>COVID TRACKER</h1> </div>";
        html = html + "</div>";
        html = html + "<ul class='topnav'>";
        html = html + "<li><a href='page1.html'>Homepage</a></li>";
        html = html + "<li><a href='page2.html'>Big Picture</a></li>";
        html = html + "<li><a href='page3.html'>Infection rates</a></li>";
        html = html + "<li><a href='page4.html'>Death rates</a></li>";
        html = html + "<li><a href='page5.html'>Cumulative Reports</a></li>";
        html = html + "<li><a class=selected href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a href='help.html'>Help</a></li>";

        html = html + "</ul>";
        html = html + "<br>";
        
        html = html + "</head>";


        html = html + "<body>";


        
        JDBCConnection jdbc = new JDBCConnection();

        

        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.CEILING);



        html = html + "<form action= '/page6.html' method='post' style='text-align: center'>";
        html = html + "<div class='form group'>";
        String radio = context.formParam("radio");

        
        if (radio == null) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='US_States' name='radio' value='US_States'>";
            html = html + "<label for='US_States' id ='size'>US States</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_infections_within_similar_period' name='radio' value='Similar_infections_within_similar_period'>";
            html = html + "<label for='Similar_infections_within_similar_period' id ='size'>Similar infections within similar period</label>";
        }
        else if (radio.equals("US_States")) {
            html = html + "<input type= 'radio' id='All' name='radio' value='All'>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='US_States' name='radio' value='US_States' checked>";
            html = html + "<label for='US_States' id ='size'>US States</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_infections_within_similar_period' name='radio' value='Similar_infections_within_similar_period'>";
            html = html + "<label for='Similar_infections_within_similar_period' id ='size'>Similar infections within similar period</label>";
        }
        else if (radio.equals("Similar_infections_within_similar_period")){
            html = html + "<input type= 'radio' id='All' name='radio' value='All'>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='US_States' name='radio' value='US_States'>";
            html = html + "<label for='US_States' id ='size'>US States</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_infections_within_similar_period' name='radio' value='Similar_infections_within_similar_period' checked>";
            html = html + "<label for='Similar_infections_within_similar_period' id ='size'>Similar infections within similar period</label>";
        }
        else {
            html = html + "<input type= 'radio' id='All' name='radio' value='All' checked>";
            html = html + "<label for='All' id ='size'>All</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='US_States' name='radio' value='US_States'>";
            html = html + "<label for='US_States' id ='size'>US States</label>";
            html = html + "&nbsp;";
            html = html + "<input type= 'radio' id='Similar_infections_within_similar_period' name='radio' value='Similar_infections_within_similar_period'>";
            html = html + "<label for='Similar_infections_within_similar_period' id ='size'>Similar infections within similar period</label>";

        }
        html = html + "</div>";//closes form group

        

        html = html + "<div class='form-group'>";


        String USStatenames_drop = context.formParam("USStatenames_drop");
        String countrynames_drop = context.formParam("countrynames_drop");
        String no_infections = context.formParam("no_infections");


        if (radio == null) { 
            html = html + "<br>";
            if (countrynames_drop == null) {
                html = html + "<label for='countrynames_drop' id = 'change'>Select a country</label>";
                html = html + "&nbsp;";
                html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
                // html = html + "<option>" + countrynames_drop + "</option>";
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
                for (String countryName: countryNames) {
                    if (countryName == countrynames_drop){
                    
                    }
                    else {
                        html = html + "<option>" + countryName + "</option>";
                    }
                }
                html = html + "</select>";
            }


        }
        else if (radio.equals("All")) {
            html = html + "<br>";
            if (countrynames_drop == null) {
                html = html + "<label for='countrynames_drop'>Select a country</label>";
                html = html + "&nbsp;";
                html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
                // html = html + "<option>" + countrynames_drop + "</option>";
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
                for (String countryName: countryNames) {
                    if (countryName == countrynames_drop){
                    
                    }
                    else {
                        html = html + "<option>" + countryName + "</option>";
                    }
                }
                html = html + "</select>";
            }

        }
        else if (radio.equals("US_States")) {
            html = html + "<br>";
            if (USStatenames_drop == null) {
                html = html + "<label for='USStatenames_drop'>Select a US State</label>";
                html = html + "&nbsp;";
                html = html + "<select id='USStatenames_drop' name='USStatenames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> USStatenames = jdbc.getUSStatesAlphabetic();
                // html = html + "<option>" + countrynames_drop + "</option>";
                for (String USStatename: USStatenames) {
                    html = html + "<option>" + USStatename + "</option>";
                } 
                html = html + "</select>";

            }
            else {
                html = html + "<label for='USStatenames_drop'>Select a US State</label>";
                html = html + "&nbsp;";
                html = html + "<select id='USStatenames_drop' name='USStatenames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> USStatenames = jdbc.getUSStatesAlphabetic();
                html = html + "<option>" + USStatenames_drop + "</option>";
                for (String USStatename: USStatenames) {

                    html = html + "<option>" + USStatename + "</option>";
                    
                }
                html = html + "</select>";
            }

        }
        else if (radio.equals("Similar_infections_within_similar_period")) {
            html = html + "<br>";
            if (countrynames_drop == null) {
                html = html + "<label for='countrynames_drop'>Select a country</label>";
                html = html + "&nbsp;";
                html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
                // html = html + "<option>" + countrynames_drop + "</option>";
                for (String countryName: countryNames) {
                    html = html + "<option>" + countryName + "</option>";
                } 
                html = html + "</select>";
                html = html + "&nbsp;";

                int last = 457;
        
                if (no_infections == null) {
                    html = html + "<label for='no_infections'>Select a date</label>";
                    html = html + "&nbsp;";
                    html = html + "<select id='no_infections' name='no_infections' style='background-color: whitesmoke'>";
                    
                    ArrayList<String> dates = jdbc.getDatesDrop();
                    // html = html + "<option>" + countrynames_drop + "</option>";
                    for (String date: dates) {
                        html = html + "<option>" + date + "</option>";
                    } 
                    html = html + "</select>";
                }
                else {
                    html = html + "<label for='no_infections'>Select a date</label>";
                    html = html + "&nbsp;";
                    html = html + "<select id='no_infections' name='no_infections' style='background-color: whitesmoke'>";
                    
                    ArrayList<String> dates = jdbc.getDatesDrop();
                    html = html + "<option>" + no_infections + "</option>";
                    for (String date: dates) {
                        html = html + "<option>" + date + "</option>";
                    } 
                    html = html + "</select>";

                }
            }
            else {
                html = html + "<label for='countrynames_drop'>Select a country</label>";
                html = html + "&nbsp;";
                html = html + "<select id='countrynames_drop' name='countrynames_drop' style='background-color: whitesmoke'>";
                
                ArrayList<String> countryNames = jdbc.getCountryNamesAlphabetic();
                html = html + "<option>" + countrynames_drop + "</option>";
                for (String countryName: countryNames) {
                    if (countryName == countrynames_drop){
                    
                    }
                    else {
                        html = html + "<option>" + countryName + "</option>";
                    }
                }
                html = html + "</select>";
                html = html + "&nbsp;";
                
                int last = 457;
        
                if (no_infections == null) {
                    html = html + "<label for='no_infections'>Select a date</label>";
                    html = html + "&nbsp;";
                    html = html + "<select id='no_infections' name='no_infections' style='background-color: whitesmoke'>";
                    
                    ArrayList<String> dates = jdbc.getDatesDrop();
                    // html = html + "<option>" + countrynames_drop + "</option>";
                    for (String date: dates) {
                        html = html + "<option>" + date + "</option>";
                    } 
                    html = html + "</select>";
                }
                else {
                    html = html + "<label for='no_infections'>Select a date</label>";
                    html = html + "&nbsp;";
                    html = html + "<select id='no_infections' name='no_infections' style='background-color: whitesmoke'>";
                    
                    ArrayList<String> dates = jdbc.getDatesDrop();
                    html = html + "<option>" + no_infections + "</option>";
                    for (String date: dates) {
                        html = html + "<option>" + date + "</option>";
                    } 
                    html = html + "</select>";

                }

            }

        }
        

        html = html + "</div>";//closes form group

        html = html + "<br>";
        html = html + "   <button type='submit' class='btn btn-primary' style='background-color: green; color:white;'>Show data</button>";
        html = html + "</form>";//closes preference form

  
        if (radio == null) {
            if (countrynames_drop == null) {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();

                double noCases = jdbc.getCountryCase("Afghanistan");
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCases, casesTotal, populationCountry);

                double noDeaths = jdbc.getCountryDeath("Afghanistan");
                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeaths, deathsTotal, casesTotal);

                double noCasesDaily = jdbc.getCountryCaseDaily("Afghanistan");
                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesDaily);

                double noDeathsDaily = jdbc.getCountryDeathDaily("Afghanistan");
                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsDaily);

                //1st table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for total infections per 1M pop</h3>";
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
                html = html + "['Country', 'Total infections per 1M pop'],";

                for (int i = 0; i < casesSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(casesSim.get(i)) + "', " + Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000) + "],";
                }
                html = html + "['" + countryname.get(casesSim.get(casesSim.size() -1)) + "', " + Math.round(casesTotal.get(casesSim.get(casesSim.size() -1 )) / populationCountry.get(casesSim.get(casesSim.size() -1)) * 1000000) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar infection per 1M pop to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div
                //2nd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for death:infected</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap1);";
                html = html + "function drawRegionsMap1() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Death:Infected'],";

                for (int i = 0; i < deathsSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(deathsSim.get(i)) + "', " + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "],";
                }
                html = html + "['" + countryname.get(deathsSim.get(deathsSim.size() -1)) + "', " + df.format(deathsTotal.get(deathsSim.get(deathsSim.size() - 1)) / (double) casesTotal.get(deathsSim.get(deathsSim.size() - 1))) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div1'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div1' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar death:infected to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div

                //3rd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for max daily infections</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryCasesDaily.size() - 1; ++i) {
                    html = html + "['" + countryCasesDaily.get(i) + "', " + casesDaily.get(i) + "],";
                }
                html = html + "['" + countryCasesDaily.get(countryCasesDaily.size() - 1) + "', " + casesDaily.get(countryCasesDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max infections to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div

                //4th table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for max daily deaths</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap3);";
                html = html + "function drawRegionsMap3() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryDeathsDaily.size() - 1; ++i) {
                    html = html + "['" + countryDeathsDaily.get(i) + "', " + deathsDaily.get(i) + "],";
                }
                html = html + "['" + countryDeathsDaily.get(countryDeathsDaily.size() - 1) + "', " + deathsDaily.get(countryDeathsDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div3'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div3' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max deaths to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";

                html = html + "</div>";

                html = html + "</div>"; //closes row div



            }
            else {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();

                double noCases = jdbc.getCountryCase(countrynames_drop);
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCases, casesTotal, populationCountry);

                double noDeaths = jdbc.getCountryDeath(countrynames_drop);
                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeaths, deathsTotal, casesTotal);

                double noCasesDaily = jdbc.getCountryCaseDaily(countrynames_drop);
                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesDaily);

                double noDeathsDaily = jdbc.getCountryDeathDaily(countrynames_drop);
                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsDaily);

                //1st table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for total infections per 1M pop</h3>";

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
                html = html + "['Country', 'Total infections per 1M pop'],";

                for (int i = 0; i < casesSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(casesSim.get(i)) + "', " + Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000) + "],";
                }
                html = html + "['" + countryname.get(casesSim.get(casesSim.size() -1)) + "', " + Math.round(casesTotal.get(casesSim.get(casesSim.size() -1 )) / populationCountry.get(casesSim.get(casesSim.size() -1)) * 1000000) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h2 style='text-align: center'>Countries with similar infection per 1M pop to " + countrynames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div

                //2nd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for death:infected</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap1);";
                html = html + "function drawRegionsMap1() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Death:Infected'],";

                for (int i = 0; i < deathsSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(deathsSim.get(i)) + "', " + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "],";
                }
                html = html + "['" + countryname.get(deathsSim.get(deathsSim.size() -1)) + "', " + df.format(deathsTotal.get(deathsSim.get(deathsSim.size() - 1)) / (double) casesTotal.get(deathsSim.get(deathsSim.size() - 1))) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div1'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div1' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h2 style='text-align: center'>Countries with similar death:infected to " + countrynames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //3rd table
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryCasesDaily.size() - 1; ++i) {
                    html = html + "['" + countryCasesDaily.get(i) + "', " + casesDaily.get(i) + "],";
                }
                html = html + "['" + countryCasesDaily.get(countryCasesDaily.size() - 1) + "', " + casesDaily.get(countryCasesDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "<h2 style='text-align: center'>Countries with similar daily max infections to " + countrynames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";


                //4th table
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap3);";
                html = html + "function drawRegionsMap3() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryDeathsDaily.size() - 1; ++i) {
                    html = html + "['" + countryDeathsDaily.get(i) + "', " + deathsDaily.get(i) + "],";
                }
                html = html + "['" + countryDeathsDaily.get(countryDeathsDaily.size() - 1) + "', " + deathsDaily.get(countryDeathsDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div3'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div3' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max deaths to " + countrynames_drop + "</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                

            }
        }
        else if (radio.equals("All")) {
            if (countrynames_drop == null) {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();

                double noCases = jdbc.getCountryCase("Afghanistan");
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCases, casesTotal, populationCountry);

                double noDeaths = jdbc.getCountryDeath("Afghanistan");
                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeaths, deathsTotal, casesTotal);

                double noCasesDaily = jdbc.getCountryCaseDaily("Afghanistan");
                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesDaily);

                double noDeathsDaily = jdbc.getCountryDeathDaily("Afghanistan");
                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsDaily);

                
                //1st table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for total infections per 1M pop</h3>";
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
                html = html + "['Country', 'Total infections per 1M pop'],";

                for (int i = 0; i < casesSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(casesSim.get(i)) + "', " + Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000) + "],";
                }
                html = html + "['" + countryname.get(casesSim.get(casesSim.size() -1)) + "', " + Math.round(casesTotal.get(casesSim.get(casesSim.size() -1 )) / populationCountry.get(casesSim.get(casesSim.size() -1)) * 1000000) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar infection per 1M pop to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //2nd table

                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for death:infected</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap1);";
                html = html + "function drawRegionsMap1() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Death:Infected'],";

                for (int i = 0; i < deathsSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(deathsSim.get(i)) + "', " + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "],";
                }
                html = html + "['" + countryname.get(deathsSim.get(deathsSim.size() -1)) + "', " + df.format(deathsTotal.get(deathsSim.get(deathsSim.size() - 1)) / (double) casesTotal.get(deathsSim.get(deathsSim.size() - 1))) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div1'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div1' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar death:infected to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //3rd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for max daily infections</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryCasesDaily.size() - 1; ++i) {
                    html = html + "['" + countryCasesDaily.get(i) + "', " + casesDaily.get(i) + "],";
                }
                html = html + "['" + countryCasesDaily.get(countryCasesDaily.size() - 1) + "', " + casesDaily.get(countryCasesDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max infections to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div



                //4th table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of Afghanistan compared to other countries for max daily deaths</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap3);";
                html = html + "function drawRegionsMap3() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryDeathsDaily.size() - 1; ++i) {
                    html = html + "['" + countryDeathsDaily.get(i) + "', " + deathsDaily.get(i) + "],";
                }
                html = html + "['" + countryDeathsDaily.get(countryDeathsDaily.size() - 1) + "', " + deathsDaily.get(countryDeathsDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div3'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div3' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max deaths to Afghanistan</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div



            }
            else {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();

                double noCases = jdbc.getCountryCase(countrynames_drop);
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCases, casesTotal, populationCountry);

                double noDeaths = jdbc.getCountryDeath(countrynames_drop);
                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeaths, deathsTotal, casesTotal);
                
                double noCasesDaily = jdbc.getCountryCaseDaily(countrynames_drop);
                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesDaily);

                double noDeathsDaily = jdbc.getCountryDeathDaily(countrynames_drop);
                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsDaily);

                
                //1st table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for total infections per 1M pop</h3>";

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
                html = html + "['Country', 'Total infections per 1M pop'],";

                for (int i = 0; i < casesSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(casesSim.get(i)) + "', " + Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000) + "],";
                }
                html = html + "['" + countryname.get(casesSim.get(casesSim.size() -1)) + "', " + Math.round(casesTotal.get(casesSim.get(casesSim.size() -1 )) / populationCountry.get(casesSim.get(casesSim.size() -1)) * 1000000) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar infection per 1M pop to " + countrynames_drop + "</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //2nd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for death:infected</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap1);";
                html = html + "function drawRegionsMap1() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Death:Infected'],";

                for (int i = 0; i < deathsSim.size() - 1; ++i) {
                    html = html + "['" + countryname.get(deathsSim.get(i)) + "', " + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "],";
                }
                html = html + "['" + countryname.get(deathsSim.get(deathsSim.size() -1)) + "', " + df.format(deathsTotal.get(deathsSim.get(deathsSim.size() - 1)) / (double) casesTotal.get(deathsSim.get(deathsSim.size() - 1))) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div1'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div1' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar death:infected to " + countrynames_drop + "</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //3rd table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for max daily infections</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryCasesDaily.size() - 1; ++i) {
                    html = html + "['" + countryCasesDaily.get(i) + "', " + casesDaily.get(i) + "],";
                }
                html = html + "['" + countryCasesDaily.get(countryCasesDaily.size() - 1) + "', " + casesDaily.get(countryCasesDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max infections to " + countrynames_drop + "</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div


                //4th table
                html = html + "<div class='row'>";
                html = html + "<div class='column'>";
                html = html + "<h3 style='margin-left: 15px'>Geochart of " + countrynames_drop + " compared to other countries for max daily deaths</h3>";

                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap3);";
                html = html + "function drawRegionsMap3() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['Country', 'Max Daily Infections'],";

                for (int i = 0; i < countryDeathsDaily.size() - 1; ++i) {
                    html = html + "['" + countryDeathsDaily.get(i) + "', " + deathsDaily.get(i) + "],";
                }
                html = html + "['" + countryDeathsDaily.get(countryDeathsDaily.size() - 1) + "', " + deathsDaily.get(countryDeathsDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div3'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div3' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";

                html = html + "<h3 style='text-align: center'>Countries with similar daily max deaths to " + countrynames_drop + "</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";

                html = html + "</div>"; //closes row div
                

            }
    
        }
        else if (radio.equals("US_States")) {
            if (USStatenames_drop == null) {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();
                ArrayList<String> regionnameUS = jdbc.getUSStatesAlphabetic();
                ArrayList<Integer> casesTotalUS = jdbc.getCasesTotalUSAlphabetic();
                ArrayList<Integer> deathsTotalUS = jdbc.getDeathsTotalUSAlphabetic();
                ArrayList<Double> populationUS = jdbc.getUSStatePopulationAlphabetic();


                
                double noCasesUS = jdbc.getUSStateCase("Alabama");
                ArrayList<Integer> casesSimUS = jdbc.getIndexOfUSStateCases(noCasesUS, casesTotalUS, populationUS);
                
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCasesUS, casesTotal, populationCountry);



                double noDeathsUS = jdbc.getUSStateDeath("Alabama");
                ArrayList<Integer> deathsSimUS = jdbc.getIndexOfUSStateDeaths(noDeathsUS, deathsTotalUS, casesTotalUS);

                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeathsUS, deathsTotal, casesTotal);


                double noCasesUSDaily = jdbc.getUSStateCaseDaily("Alabama");
                ArrayList<String> USStatesCasesDaily = jdbc.getNameUSStatesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> USCasesDaily = jdbc.getUSStatesSimilarDailyInfection(noCasesUSDaily);

                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesUSDaily);


                double noDeathsUSDaily = jdbc.getUSStateDeathDaily("Alabama");
                ArrayList<String> USStatesDeathsDaily = jdbc.getNameUSStatesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> USDeathDaily = jdbc.getUSStatesSimilarDailyDeath(noDeathsUSDaily);

                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsUSDaily);

                html = html + "<div class='row'>";


                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of Alabama compared to other US states for total infections per 1M pop</h3>";
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
                html = html + "['State', 'Total infections per 1M pop'],";

                for (int i = 0; i < casesSimUS.size() - 1; ++i) {
                    html = html + "['" + regionnameUS.get(casesSimUS.get(i)) + "', " + Math.round(casesTotalUS.get(casesSimUS.get(i)) / populationUS.get(casesSimUS.get(i)) * 1000000) + "],";
                }
                html = html + "['" + regionnameUS.get(casesSimUS.get(casesSimUS.size() -1)) + "', " + Math.round(casesTotalUS.get(casesSimUS.get(casesSimUS.size() -1 )) / populationUS.get(casesSimUS.get(casesSimUS.size() -1)) * 1000000) + "]";
           
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";
                html = html + "<div class='column'>";
                //1st table
                html = html + "<h3 style='text-align: center'>States with similar infections per 1M pop to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(casesSimUS.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotalUS.get(casesSimUS.get(i)) / populationUS.get(casesSimUS.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //2nd table
                html = html + "<h3 style='text-align: center'>Countries with similar infections per 1M pop to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "</div>"; //closes div class row


                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of Alabama compared to other US states for death:infected</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Death:Infected'],";

                for (int i = 0; i < deathsSimUS.size() - 1; ++i) {
                    html = html + "['" + regionnameUS.get(deathsSimUS.get(i)) + "', " + df.format(deathsTotalUS.get(deathsSimUS.get(i)) / (double) casesTotalUS.get(deathsSimUS.get(i))) + "],";
                }
                html = html + "['" + regionnameUS.get(deathsSimUS.get(deathsSimUS.size() -1)) + "', " + df.format(deathsTotalUS.get(deathsSimUS.size() -1) / (double) casesTotalUS.get(deathsSimUS.size() -1)) + "]";
           
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";

                html = html + "<div class='column'>";
                //3rd table

                html = html + "<h3 style='text-align: center'>States with similar death:infected to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(deathsSimUS.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotalUS.get(deathsSimUS.get(i)) / (double) casesTotalUS.get(deathsSimUS.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //4th table

                html = html + "<h3 style='text-align: center'>Countries with similar death:infected to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column
                
                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of Alabama compared to other US states for max daily infections</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap4);";
                html = html + "function drawRegionsMap4() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Max Daily Infections'],";

                for (int i = 0; i < USStatesCasesDaily.size() - 1; ++i) {
                    html = html + "['" + USStatesCasesDaily.get(i) + "', " + USCasesDaily.get(i) + "],";
                }
                html = html + "['" + USStatesCasesDaily.get(USStatesCasesDaily.size() - 1) + "', " + USCasesDaily.get(USStatesCasesDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div4'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div4' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";
                html = html + "<div class='column'>";
                //5th table

                html = html + "<h3 style='text-align: center'>States with similar daily max infections to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USCasesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";

                //6th table
                html = html + "<h3 style='text-align: center'>Countries with similar daily max infections to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of Alabama compared to other US states for max daily Deaths</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap6);";
                html = html + "function drawRegionsMap6() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Max Daily Infections'],";

                for (int i = 0; i < USStatesDeathsDaily.size() - 1; ++i) {
                    html = html + "['" + USStatesDeathsDaily.get(i) + "', " + USDeathDaily.get(i) + "],";
                }
                html = html + "['" + USStatesDeathsDaily.get(USStatesDeathsDaily.size() - 1) + "', " + USDeathDaily.get(USStatesDeathsDaily.size() - 1) + "]";
           
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div6'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div6' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";

                html = html + "<div class='column'>";
                //7th table
                html = html + "<h3 style='text-align: center'>States with similar daily max deaths to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USDeathDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";
                //8th table
                html = html + "<h3 style='text-align: center'>Countries with similar daily max deaths to Alabama</h3>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row


        

            }
            else if (USStatenames_drop.equals("American Samoa")) {

                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();
                ArrayList<String> regionnameUS = jdbc.getUSStatesAlphabetic();
                ArrayList<Integer> casesTotalUS = jdbc.getCasesTotalUSAlphabetic();
                ArrayList<Integer> deathsTotalUS = jdbc.getDeathsTotalUSAlphabetic();
                ArrayList<Double> populationUS = jdbc.getUSStatePopulationAlphabetic();

                
                double noCasesUS = jdbc.getUSStateCase(USStatenames_drop);
                ArrayList<Integer> casesSimUS = jdbc.getIndexOfUSStateCases(noCasesUS, casesTotalUS, populationUS);

                
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCasesUS, casesTotal, populationCountry);



                double noDeathsUS = jdbc.getUSStateDeath(USStatenames_drop);
                ArrayList<Integer> deathsSimUS = jdbc.getIndexOfUSStateDeaths(noDeathsUS, deathsTotalUS, casesTotalUS);

                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeathsUS, deathsTotal, casesTotal);


                double noCasesUSDaily = jdbc.getUSStateCaseDaily(USStatenames_drop);
                ArrayList<String> USStatesCasesDaily = jdbc.getNameUSStatesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> USCasesDaily = jdbc.getUSStatesSimilarDailyInfection(noCasesUSDaily);

                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesUSDaily);


                double noDeathsUSDaily = jdbc.getUSStateDeathDaily(USStatenames_drop);
                ArrayList<String> USStatesDeathsDaily = jdbc.getNameUSStatesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> USDeathDaily = jdbc.getUSStatesSimilarDailyDeath(noDeathsUSDaily);

                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsUSDaily);

                html = html + "<div class='row'>";
                    

                html = html + "<div class='column'>";
                //1st table
                html = html + "<h2 style='text-align: center'>States with similar infections per 1M pop to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(casesSimUS.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotalUS.get(casesSimUS.get(i)) / populationUS.get(casesSimUS.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //2nd table
                html = html + "<h2 style='text-align: center'>Countries with similar infections per 1M pop to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "</div>"; //closes div class row


                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                //3rd table
                html = html + "<h2 style='text-align: center'>States with similar death:infected to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(deathsSimUS.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotalUS.get(deathsSimUS.get(i)) / (double) casesTotalUS.get(deathsSimUS.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //4th table
                html = html + "<h2 style='text-align: center'>Countries with similar death:infected to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";


                html = html + "<div class='column'>";
                //5th table
                html = html + "<h2 style='text-align: center'>States with similar daily max infections to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USCasesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";
                //6th table
                html = html + "<h2 style='text-align: center'>Countries with similar daily max infections to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";



                html = html + "<div class='column'>";
                //7th table
                html = html + "<h2 style='text-align: center'>States with similar daily max deaths to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USDeathDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";
                //8th table
                html = html + "<h2 style='text-align: center'>Countries with similar daily max deaths to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row

                
            }
            else {
                ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                ArrayList<Integer> casesTotal = jdbc.getCasesTotalAlphabetic();
                ArrayList<Integer> deathsTotal = jdbc.getDeathsTotalAlphabetic();
                ArrayList<Double> populationCountry = jdbc.getCountryPopulationAlphabetic();
                ArrayList<String> regionnameUS = jdbc.getUSStatesAlphabetic();
                ArrayList<Integer> casesTotalUS = jdbc.getCasesTotalUSAlphabetic();
                ArrayList<Integer> deathsTotalUS = jdbc.getDeathsTotalUSAlphabetic();
                ArrayList<Double> populationUS = jdbc.getUSStatePopulationAlphabetic();

                
                double noCasesUS = jdbc.getUSStateCase(USStatenames_drop);
                ArrayList<Integer> casesSimUS = jdbc.getIndexOfUSStateCases(noCasesUS, casesTotalUS, populationUS);

                
                ArrayList<Integer> casesSim = jdbc.getIndexOfCountriesCases(noCasesUS, casesTotal, populationCountry);



                double noDeathsUS = jdbc.getUSStateDeath(USStatenames_drop);
                ArrayList<Integer> deathsSimUS = jdbc.getIndexOfUSStateDeaths(noDeathsUS, deathsTotalUS, casesTotalUS);

                ArrayList<Integer> deathsSim = jdbc.getIndexOfCountriesDeaths(noDeathsUS, deathsTotal, casesTotal);


                double noCasesUSDaily = jdbc.getUSStateCaseDaily(USStatenames_drop);
                ArrayList<String> USStatesCasesDaily = jdbc.getNameUSStatesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> USCasesDaily = jdbc.getUSStatesSimilarDailyInfection(noCasesUSDaily);

                ArrayList<String> countryCasesDaily = jdbc.getNameCountriesSimilarDailyInfection(noCasesUSDaily);
                ArrayList<Integer> casesDaily = jdbc.getCountriesSimilarDailyInfection(noCasesUSDaily);


                double noDeathsUSDaily = jdbc.getUSStateDeathDaily(USStatenames_drop);
                ArrayList<String> USStatesDeathsDaily = jdbc.getNameUSStatesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> USDeathDaily = jdbc.getUSStatesSimilarDailyDeath(noDeathsUSDaily);

                ArrayList<String> countryDeathsDaily = jdbc.getNameCountriesSimilarDailyDeath(noDeathsUSDaily);
                ArrayList<Integer> deathsDaily = jdbc.getCountriesSimilarDailyDeath(noDeathsUSDaily);

                html = html + "<div class='row'>";
                    
                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of " + USStatenames_drop + " compared to other US states for total infections per 1M pop</h3>";
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
                html = html + "['State', 'Total infections per 1M pop'],";

                if (casesSimUS.size() == 1) {
                    html = html + "['" + regionnameUS.get(casesSimUS.get(0)) + "', " + Math.round(casesTotalUS.get(casesSimUS.get(0)) / populationUS.get(casesSimUS.get(0)) * 1000000) + "]";
                }
                else {
                    for (int i = 0; i < casesSimUS.size() - 1; ++i) {
                        html = html + "['" + regionnameUS.get(casesSimUS.get(i)) + "', " + Math.round(casesTotalUS.get(casesSimUS.get(i)) / populationUS.get(casesSimUS.get(i)) * 1000000) + "],";
                    }
                    html = html + "['" + regionnameUS.get(casesSimUS.get(casesSimUS.size() -1)) + "', " + Math.round(casesTotalUS.get(casesSimUS.get(casesSimUS.size() -1 )) / populationUS.get(casesSimUS.get(casesSimUS.size() -1)) * 1000000) + "]";
                }
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";

                html = html + "<div class='column'>";
                //1st table
                html = html + "<h2 style='text-align: center'>States with similar infections per 1M pop to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(casesSimUS.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotalUS.get(casesSimUS.get(i)) / populationUS.get(casesSimUS.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //2nd table
                html = html + "<h2 style='text-align: center'>Countries with similar infections per 1M pop to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Total Infections per 1M pop" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< casesSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(casesSim.get(i)) + "</td>";
                    int number = (int) Math.round(casesTotal.get(casesSim.get(i)) / populationCountry.get(casesSim.get(i)) * 1000000);
                    html = html + "<td>" + myFormat.format(number) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "</div>"; //closes div class row


                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of " + USStatenames_drop + " compared to other US states for death:infected</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap2);";
                html = html + "function drawRegionsMap2() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Death:Infected'],";

                if (deathsSimUS.size() == 1) {
                    html = html + "['" + regionnameUS.get(deathsSimUS.get(0)) + "', " + df.format(deathsTotalUS.get(0) / (double) casesTotalUS.get(0)) + "]";
                }
                else {
                    for (int i = 0; i < deathsSimUS.size() - 1; ++i) {
                        html = html + "['" + regionnameUS.get(deathsSimUS.get(i)) + "', " + df.format(deathsTotalUS.get(deathsSimUS.get(i)) / (double) casesTotalUS.get(deathsSimUS.get(i))) + "],";
                    }
                    html = html + "['" + regionnameUS.get(deathsSimUS.get(deathsSimUS.size() -1)) + "', " + df.format(deathsTotalUS.get(deathsSimUS.size() -1) / (double) casesTotalUS.get(deathsSimUS.size() -1)) + "]";
                }
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div2'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div2' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";

                html = html + "<div class='column'>";
                //3rd table
                html = html + "<h2 style='text-align: center'>States with similar death:infected to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSimUS.size(); ++i) {
                    html = html + "<tr><td>" + regionnameUS.get(deathsSimUS.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotalUS.get(deathsSimUS.get(i)) / (double) casesTotalUS.get(deathsSimUS.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column



                html = html + "<div class='column'>";
                //4th table
                html = html + "<h2 style='text-align: center'>Countries with similar death:infected to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Death:Infected" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< deathsSim.size(); ++i) {
                    html = html + "<tr><td>" + countryname.get(deathsSim.get(i)) + "</td>";
                    html = html + "<td>" + df.format(deathsTotal.get(deathsSim.get(i)) / (double) casesTotal.get(deathsSim.get(i))) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of " + USStatenames_drop + " compared to other US states for max daily infections</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap4);";
                html = html + "function drawRegionsMap4() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Max Daily Infections'],";
                if (USStatesCasesDaily.size() == 1) {
                    html = html + "['" + USStatesCasesDaily.get(0) + "', " + USCasesDaily.get(0) + "]";
                }
                else {
                    for (int i = 0; i < USStatesCasesDaily.size() - 1; ++i) {
                        html = html + "['" + USStatesCasesDaily.get(i) + "', " + USCasesDaily.get(i) + "],";
                    }
                    html = html + "['" + USStatesCasesDaily.get(USStatesCasesDaily.size() - 1) + "', " + USCasesDaily.get(USStatesCasesDaily.size() - 1) + "]";
                }
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div4'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div4' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                html = html + "</div>";

                html = html + "<div class='column'>";
                //5th table
                html = html + "<h2 style='text-align: center'>States with similar daily max infections to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USCasesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";
                //6th table
                html = html + "<h2 style='text-align: center'>Countries with similar daily max infections to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Infections" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryCasesDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryCasesDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(casesDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row




                html = html + "<div class='row'>";

                html = html + "<div class='column'>";
                html = html + "<h3>Geochart of " + USStatenames_drop + " compared to other US states for max daily Deaths</h3>";
                html = html + "<br>";
                html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                html = html + "<script type=\"text/javascript\">";
    
                html = html + "google.charts.load('current', {";
                html = html + "'packages':['geochart'],";
                html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                html = html + "});";
                html = html + "google.charts.setOnLoadCallback(drawRegionsMap6);";
                html = html + "function drawRegionsMap6() {";
                html = html + "var data = google.visualization.arrayToDataTable([";
                html = html + "['State', 'Max Daily Infections'],";

                if (USStatesDeathsDaily.size() == 1) {
                    html = html + "['" + USStatesDeathsDaily.get(0) + "', " + USDeathDaily.get(0) + "]";
                }
                else {
                    for (int i = 0; i < USStatesDeathsDaily.size() - 1; ++i) {
                        html = html + "['" + USStatesDeathsDaily.get(i) + "', " + USDeathDaily.get(i) + "],";
                    }
                html = html + "['" + USStatesDeathsDaily.get(USStatesDeathsDaily.size() - 1) + "', " + USDeathDaily.get(USStatesDeathsDaily.size() - 1) + "]";
                }
                html = html + "]);";
                html = html + "var options = {region: 'US',displayMode: 'regions', resolution: 'provinces', datelessRegionColor: 'transparent'};";
                html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div6'));";
                html = html + "chart.draw(data, options);";
                html = html + "}";
                html = html + "</script>";
                html = html + "<br>";
    
                html = html + "<div id='regions_div6' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";
                html = html + "</div>";


                html = html + "<div class='column'>";
                //7th table
                html = html + "<h2 style='text-align: center'>States with similar daily max deaths to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "State Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< USStatesDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + USStatesDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(USDeathDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column

                html = html + "<div class='column'>";
                //8th table
                html = html + "<h2 style='text-align: center'>Countries with similar daily max deaths to " + USStatenames_drop + "</h2>" + "<table id='comparison'>";
                html = html + "<tr><th>" + "Country Name" + "</th>";
                html = html + "<th>" + "Max Daily Deaths" + "</th>";
                html = html + "</tr>"; // closes table header row

                for (int i=0; i< countryDeathsDaily.size(); ++i) {
                    html = html + "<tr><td>" + countryDeathsDaily.get(i) + "</td>";
                    html = html + "<td>" + myFormat.format(deathsDaily.get(i)) + "</td></tr>";
                }
                html = html + "</table>";
                html = html + "</div>";//closes div class column


                html = html + "</div>"; //closes div class row

                
            }

        }
        else if (radio.equals("Similar_infections_within_similar_period")) {
            if (no_infections == null) {
                if (countrynames_drop == null) {
                    System.out.println("null null");
                    html = html + "<p style='color: red; text-align: center'>Please select a country and date</p>";
                    System.out.println("nonull nonull");
                    int xDay = jdbc.getXDaysFromUser(no_infections);
                    int sum = jdbc.getSUMForCountry(countrynames_drop, xDay);
                    double minSum = sum - (sum * 0.25);
                    double maxSum = sum + (sum * 0.25);
                    ArrayList<Integer> minCases = jdbc.getInfectionsMin(xDay);
                    ArrayList<Integer> maxCases = jdbc.getInfectionsMax(xDay);
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> count = new ArrayList<Integer>();

                }
                else {
                    System.out.println("null nonull");
                    html = html + "<p style='color: red; text-align: center'>Please select a date</p>";
                }
            }
            else {
                if (countrynames_drop == null) {
                    System.out.println("nonull null");
                }
                else {
                    System.out.println("nonull nonull");
                    int xDay = jdbc.getXDaysFromUser(no_infections);
                    String minDate = jdbc.getMinDateFromDate(xDay);
                    String maxDate = jdbc.getMaxDateFromDate(xDay);
                    int sum = jdbc.getSUMForCountry(countrynames_drop, xDay);
                    double minSum = sum - (sum * 0.25);
                    double maxSum = sum + (sum * 0.25);
                    ArrayList<Integer> minCases = jdbc.getInfectionsMin(xDay);
                    ArrayList<Integer> maxCases = jdbc.getInfectionsMax(xDay);
                    ArrayList<String> countryname = jdbc.getCountryNamesAlphabetic();
                    ArrayList<Integer> count = new ArrayList<Integer>();


                    html = html + "<br>";
                    html = html + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>";
                    html = html + "<script type=\"text/javascript\">";
        
                    html = html + "google.charts.load('current', {";
                    html = html + "'packages':['geochart'],";
                    html = html + "'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'";
                    html = html + "});";
                    html = html + "google.charts.setOnLoadCallback(drawRegionsMap6);";
                    html = html + "function drawRegionsMap6() {";
                    html = html + "var data = google.visualization.arrayToDataTable([";
                    html = html + "['State', 'Max Daily Infections'],";
    
                    // if (USStatesDeathsDaily.size() == 1) {
                    //     html = html + "['" + USStatesDeathsDaily.get(0) + "', " + USDeathDaily.get(0) + "]";
                    // }
                    // else {
                    //     for (int i = 0; i < USStatesDeathsDaily.size() - 1; ++i) {
                    //         html = html + "['" + USStatesDeathsDaily.get(i) + "', " + USDeathDaily.get(i) + "],";
                    //     }
                    // html = html + "['" + USStatesDeathsDaily.get(USStatesDeathsDaily.size() - 1) + "', " + USDeathDaily.get(USStatesDeathsDaily.size() - 1) + "]";
                    // }

                    for (int i=0; i < minCases.size(); ++i) {
                        if ((minCases.get(i) >= minSum) && (minCases.get(i) <= maxSum)) {
                            html = html + "['" + countryname.get(i) + "', " + minCases.get(i) + "],";                    
                        }
                        else if ((maxCases.get(i) >= minSum) && (maxCases.get(i) <= maxSum)) {
                            html = html + "['" + countryname.get(i) + "', " + maxCases.get(i) + "],";                 
                        }
                    }
                    if ((minCases.get(minCases.size() - 1) >= minSum) && (minCases.get(minCases.size() - 1) <= maxSum)) {
                        html = html + "['" + countryname.get(minCases.size() - 1) + "', " + minCases.get(minCases.size() - 1) + "]";
                    }
                    else if ((maxCases.get(maxCases.size() - 1) >= minSum) && (maxCases.get(maxCases.size() - 1) <= maxSum)) {
                        html = html + "['" + countryname.get(maxCases.size() - 1) + "', " + minCases.get(maxCases.size() - 1) + "]";
                    }

                    html = html + "]);";
                    html = html + "var options = {};";
                    html = html + "var chart = new google.visualization.GeoChart(document.getElementById('regions_div6'));";
                    html = html + "chart.draw(data, options);";
                    html = html + "}";
                    html = html + "</script>";
                    html = html + "<br>";
        
                    html = html + "<div id='regions_div6' style='width: 600px; height: 400px; margin-left:auto; margin-right:auto;'></div>";

                    html = html + "<h3 style='text-align: center; color: green'>In " + countrynames_drop + ", the number of cases on  " + no_infections + " was " + sum + ".</h3>";

                    html = html + "<h2 style='text-align: center'>Countries which reached similar infection milestone to " + countrynames_drop + " during the period " + minDate + " - " + maxDate + "</h2>" + "<table id='comparison'>";
                    html = html + "<tr><th>" + "Country Name" + "</th>";
                    html = html + "<th>" + "Infections within similar period" + "</th>";
                    html = html + "</tr>"; // closes table header row

                    
                    for (int i=0; i < minCases.size(); ++i) {
                        if ((minCases.get(i) >= minSum) && (minCases.get(i) <= maxSum)) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(minCases.get(i))+ "</td></tr>";                       
                         }
                        else if ((maxCases.get(i) >= minSum) && (maxCases.get(i) <= maxSum)) {
                            html = html + "<tr><td>" + countryname.get(i) + "</td>";
                            html = html + "<td>" + myFormat.format(maxCases.get(i)) + "</td></tr>";                        
                        }
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
        html = html + "</html>";
                                                            
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
