package app;

import java.util.ArrayList;

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
public class Help implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/help.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html = html + "<html lang='en-us'>";

        // Add some Header information
        html = html + "<head>"; 
        
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Help</title>";

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
        html = html + "<li><a href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a class=selected href='help.html'>Help</a></li>";

        html = html + "</ul>";
        
        html = html + "</head>";
         
        html = html + "<body>";
        html = html + "<div class=help>";
        html = html + "<br>";
        html = html + "<p> To return to the initial state of any page, click on the tab again.</p>";
        html = html + "<h1>Homepage</h1>";
        html = html + "<p>This is a static page to inform you about staying safe during the pandemic.</p>";
       
        html = html + "<h1>Big Picture</h1>";
        html = html + "<p>This is a static page as well. It shines a light on the imporant COVID statistics that general people might want to know.</p>";
 
        html = html + "<h1>Infection rates</h1>";
        html = html + "<p>The 'All' radio tab displays information about all countries whereas the 'Australia' radio tab displays information about the regions of Australia.</p>";
        html = html + "<p>Click on the radio button to select your required option.</p>";
        html = html + "<p>Use the 'Sort by' drop-down menu to sort the data to your liking.</p>";
        html = html + "<p>By default, you are displayed with the monthly cases of January 2020.</p>";
        html = html + "<p>You can also select your preferred month from the 'Monthly cases' drop-down.</p>";
        html = html + "<p>After you have selected your preferred options, click on the 'Show data' button to generate results.</p>";
        html = html + "<p>The data is sorted by the highest or least number of infections per country.</p>";
        
        html = html + "<h1>Death rates</h1>";
        html = html + "<p>This page works the same way as the 'Infection rates' page.</p>";
        html = html + "<p>The only difference is that the 'Total Infections' column is replaced by the 'Total Deaths' column.</p>";
        html = html + "<p>The table also has a new column which contains the ratio of 'Death:Infected'.</p>";
        html = html + "<p>The data is sorted by the highest or least number of deaths per country.</p>";
     
        html = html + "<h1>Cumulative reports</h1>";
        html = html + "<p>The 'All' radio tab displays cumulative information about all countries.</p>";
        html = html + "<p>The 'Similar Climate' radio tab displays information about countries that have similar climates.</p>";
        html = html + "<p>To generate results, please choose a country from the 'Select a country' drop-down.</p>";
        html = html + "<p>The 'Surrounding Countries' radio tab displays information about neighbouring countries.</p>";
        html = html + "<p>To generate results, please select a country from the 'Select a country' drop-down.</p>";
        html = html + "<br>";
        html = html + "<p>The 'Last x days' textbox allows you to choose the number of days you want cumulative information on.</p>";
        html = html + "<p>The 'Within x km' textbox allows you to set a radius around a country to find neighbouring countries within that radius.</p>";
        html = html + "<br>";
        html = html + "<p>Please enter numbers in the textboxes to generate data based on your requirements.</p>";
        html = html + "<p>After you have selected your preferred options, click on the 'Show data' button to generate results.</p>";
        html = html + "<h1>Comparisons</h1>";
        html = html + "<p>Please select a country in the 'All' radio tab to generate data to your preference.</p>";
        html = html + "<p>The 'All' radio tab compares the 'Infections per 1M pop', 'Death:Infected' ratio, 'Max daily infection' and the 'Max daily deaths' of the selected country to other countries. It displays the countries that have data within 25% of the selected country's data.</p>";
        html = html + "<p>Please select a US state in the 'US States' radio tab to generate data to your preference.</p>";
        html = html + "<p>The 'US States' radio tab compares the 'Infections per 1M pop', 'Death:Infected' ratio, 'Max daily infection' and the 'Max daily deaths' of the selected state to other US states as well as other countries. It displays the US states and the countries that have data within 25% of the selected state's data.</p>";
        html = html + "<p>Please select a country and a date in the 'Similar infections within similar period' radio tab to generate data to your preference.</p>";
        html = html + "<p>The 'Similar infections within similar period' radio tab compares the number of infections on the selected date of the selected country to other countries. It displays the countries that reached a similar number of infections within a similar period. The range for number of infections to be considered similar is 25% and the range for similar period is 5 days.";
        html = html + "<br>";

        
        html = html + "</div>"; //ends help div

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

        context.html(html);
    
    }
}
