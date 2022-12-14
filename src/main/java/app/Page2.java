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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page2.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html = html + "<html lang='en-us'>";

        // Add some Header information
        html = html + "<head>"; 
        
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Big Picture</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<div class = container>";
        html = html + "<img src='banner.jpg' width='100%'/>";
        html = html + "<h1 div class=centered>COVID TRACKER</h1> </div>";
        html = html + "</div>";

        html = html + "<ul class='topnav'>";
        html = html + "<li><a href='page1.html'>Homepage</a></li>";
        html = html + "<li><a class=selected href='page2.html'>Big Picture</a></li>";
        html = html + "<li><a href='page3.html'>Infection rates</a></li>";
        html = html + "<li><a href='page4.html'>Death rates</a></li>";
        html = html + "<li><a href='page5.html'>Cumulative Reports</a></li>";
        html = html + "<li><a href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a href='help.html'>Help</a></li>";
        html = html + "</ul>";
        
        html = html + "</head>";

        html = html + "<body>";
        html = html + "<div id='page-container2'>";
        html = html + "<div id='content-wrap2'>";


        html = html + "<br>";
        html = html + "<br>";

        html = html + "<div class=float-container>";

        html = html + "<div class=float-child>";
        html = html + "<div class=box1>Total number of confirmed infections upto April 2021</div>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<font size='+50'><b>144,741,753</b></font>";
        html = html + "<br>";
        html = html + "<font size='-1' style = 'color:green;font-family:Helvetica Neue'>Since 22nd Jan, 2020.</font>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "</div>";//closes float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box2>Total number of confirmed deaths upto April 2021</div>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<font size='+50'><b>3,082,663</b></font>";
        html = html + "<br>";
        html = html + "<font size='-1' style = 'color:green;font-family:Helvetica Neue'>Since 22nd Jan, 2020.</font>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "</div>";//closes float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box3>Total number of approximate recoveries upto April 2021</div>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<font size='+50'><b>141,659,090</b></font>";
        html = html + "<br>";
        html = html + "<font size='-1' style = 'color:green;font-family:Helvetica Neue'>Since 22nd Jan, 2020.</font>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "<br>";
        html = html + "</div>";//closes float child

        


        html = html + "<div class=float-child>";
        html = html + "<div class=box3>Countries with the highest number of infections</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>USA</b></li>";
        html = html + "<br>";
        html = html + "<li><b>India</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Brazil</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box4>Countries with the highest number of deaths</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>USA</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Brazil</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Mexico</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box3>Countries that are handling the pandemic the best</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>Mauritius</b></li>";
        html = html + "<br>";
        html = html + "<li><b>New Zealand</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Iceland</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box4>Countries with the highest 'Infected:Population' ratio</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>Andorra</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Montenegro</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Czechia</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box4>Countries with the highest 'Death:Population' ratio</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>Hungary</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Czechia</b></li>";
        html = html + "<br>";
        html = html + "<li><b>San Marino</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child

        html = html + "<div class=float-child>";
        html = html + "<div class=box4>Countries with the highest 'Death:Infected' ratio</div>";
        html = html + "<br>";
        html = html + "<ol>";
        html = html + "<li><b>Vanuatu</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Yemen</b></li>";
        html = html + "<br>";
        html = html + "<li><b>Mexico</b></li>";
        html = html + "<br>";
        html = html + "</ol>";
        html = html + "</div>";//close float child



        html = html + "</div>";//close float container
        
        html = html + "<div class = info>";
        html = html + "<p>For detailed analysis, check out the other tabs.</p>";
        html = html + "</div>";
        

        html = html + "</div>";


        html = html + "<footer id='footer2'>";

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
        html = html + "</div>";
        

        // Finish the HTML webpage
        html = html + "</body>";
        html = html + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
