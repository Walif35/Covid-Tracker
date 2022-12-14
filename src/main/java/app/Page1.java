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
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page1.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";
        html = html + "<html lang='en-us'>";

        // Add some Header information
        html = html + "<head>"; 
        
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";
        html = html + "<title>Homepage</title>";

        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        html = html + "<div class = container>";
        html = html + "<img src='banner.jpg' width='100%'/>";
        html = html + "<h1 div class=centered>COVID TRACKER</h1> </div>";
        html = html + "</div>";
        html = html + "<ul class='topnav'>";
        html = html + "<li><a class='selected' href='page1.html'>Homepage</a></li>";
        html = html + "<li><a href='page2.html'>Big Picture</a></li>";
        html = html + "<li><a href='page3.html'>Infection rates</a></li>";
        html = html + "<li><a href='page4.html'>Death rates</a></li>";
        html = html + "<li><a href='page5.html'>Cumulative Reports</a></li>";
        html = html + "<li><a href='page6.html'>Comparisons</a></li>";
        html = html + "<li><a href='help.html'>Help</a></li>";

        html = html + "</ul>";
        
        html = html + "</head>";
         

        // Add some CSS (external file)
        

        // Add the body
        html = html + "<body>";
        html = html + "<div id='page-container1'>";
        html = html + "<div id='content-wrap1'>";

        // html = html + "<br>";

        html = html + "<h2 style='text-align:center'>To prevent the spread of COVID-19</h2>";

        html = html + "<div class = flex-container>";

        html = html + "<div class = flex-items>";
        html = html + "<img src='1st.png' height='200px' width='250px'/> ";
        html = html + "<p>Clean your hands with soap or sanitise frequently.</p>";
        html = html + "</div>";

        html = html + "<div class = flex-items>";
        html = html + "<img src='2.jpg' height='200px' width='250px'/> ";
        html = html + "<p>Wear a mask at all times whenever you're outside.</p>";
        html = html + "</div>";

        html = html + "<div class = flex-items>";
        html = html + "<img src='3.png' height='200px' width='250px'/> ";
        html = html + "<p>Maintain social distancing, at least 6 ft.</p>";
        html = html + "</div>";

        html = html + "<div class = flex-items>";
        html = html + "<img src='4.png' height='200px' width='250px'/> ";
        html = html + "<p>Isolate yourself from others if you experience any symptoms.</p>";
        html = html + "</div>";

        html = html + "</div>";

        html = html + "<br>";

        html = html + "<hr>";

      
        html = html + "<h2 style='text-align:center'>Symptoms of COVID-19</h2>";

        html = html + "<div class = flex-container1>";
        html = html + "<div>";
        html = html + "<p>Most common symptoms:";
        html = html + "<ul>";
        html = html + "<li>Fever</li>";
        html = html + "<li>Dry cough</li>";
        html = html + "<li>Tiredness</li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "<div>";
        html = html + "<p>Less common symptoms:";
        html = html + "<ul>";
        html = html + "<li>Aches and pains</li>";
        html = html + "<li>Sore throat</li>";
        html = html + "<li>Diarrhoea</li>";
        html = html + "<li>Conjunctivitis</li>";
        html = html + "<li>Headache</li>";
        html = html + "<li>Loss of taste or smell</li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "<div>";
        html = html + "<p>Most severe symptoms:";
        html = html + "<ul>";
        html = html + "<li>Difficulty breathing</li>";
        html = html + "<li>Chest pain or pressure</li>";
        html = html + "<li>Loss of speech or movement</li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "</div>";
        //html = html + "<br>";
        // html = html + "<br>";
        // html = html + "<br>";

    html = html + "<hr>";    
    html = html + "<br>";    

        html = html + "<div class=flex-container2>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        html = html + "<div id=rectangle>";
        html = html + "<a href='page2.html'>Find the most important statistics to know about COVID.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        
        html = html + "<div id=rectangle>";
        html = html + "<a href='page3.html'>Find infection rates by country or by regions of Australia.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        
        html = html + "<div id=rectangle>";
        html = html + "<a href='page4.html'>Find death rates by country or by regions of Australia.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";

        html = html + "<div id=rectangle>";
        html = html + "<a href='page5.html'>Find statistics of countries with similar climates.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";

        html = html + "</div>"; // closes flex container 2
        html = html + "<br>";

        html = html + "<div class=flex-container2>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        html = html + "<div id=rectangle>";
        html = html + "<a href='page5.html'>Find surrounding countries of any country to identify cross-border transmission.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        
        html = html + "<div id=rectangle>";
        html = html + "<a href='page6.html'>Compare the statistics of any country with other countries.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";

        html = html + "<div id=rectangle>";
        html = html + "<a href='page6.html'>Compare any US state with other states and other countries.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";

        html = html + "<div id=rectangle>";
        html = html + "<a href='page6.html'>Find countries that reached similar milestones to a chosen country around the same period.</a>";
        html = html + "</div>";
        html = html + "&nbsp";
        html = html + "&nbsp";

        html = html + "</div>"; // closes flex container 2

        html = html + "</div>"; //closes flex-container1

        html = html + "<footer id='footer1'>";

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

        html = html + "<div class=source>";
        
        html = html + "<p style='margin-left:43px;'>Data has been collected from John Hopkins University.</p>";
        html = html + "<ul>";
        html = html + "<li><a href='https://pngtree.com/freebackground/blue-gradient-creative-virus-background_1174065.html' target='_blank'>Banner source | </a></li>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        html = html + "<li><a href='http://clipart-library.com/clipart/6ip5LayAT.htm' target='_blank'>Image 1 source | </a></li>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        html = html + "<li><a href='https://www.freepik.com/free-vector/crowd-people-wearing-face-masks_8269462.htm' target='_blank'>Image 2 source | </a></li>";
        html = html + "&nbsp";
        html = html + "&nbsp";
        html = html + "<li><a href='https://www.citypng.com/photo/1666/cartoon-pandemic-social-distance-6ft-free-vector' target='_blank'>Image 3 source</a></li>";
        html = html + "</ul>";
        html = html + "</div>";

        html = html + "</body>";





        // Add HTML for link back to the homepage
        
        
        // Finish the HTML webpage
        html = html + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
