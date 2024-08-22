package exporting;

import gui.panels.ReportCreationPanel;
import gui.panels.details.DetailPanel;

import java.io.*;

/**
 * A class used to take the information in the current app and build an HTML website from it
 * Would use text blocks however it would mean I couldn't add in values
 */
public class DoorReportBuilder implements ReportBuilder{

    private ReportCreationPanel reportCreationPanel;
    private StringBuilder stringBuilder;
    private PrintWriter printWriter;

    public DoorReportBuilder(ReportCreationPanel reportCreationPanel) {
        this.reportCreationPanel = reportCreationPanel;
        stringBuilder = new StringBuilder();
        generateHTML();
    }

    /**
     * Builds the HTML for the file export
     */
    public void generateHTML() {
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html lang=\"en\">\n");
        generateHead();
        stringBuilder.append("<body>\n");
        generateHeader();
        generateMain();
        stringBuilder.append(
            "</body>\n" +
            "</html>");

        System.out.println("Bazinga");
    }

    public void writeToFile() {

    }

    /**
     * Builds the HTML head
     */
    public void generateHead() {
        stringBuilder.append(
                "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <meta name=\"author\" content=\"BWP-Report-Creator\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0\">\n" +
                        "\n" +
                        "    <title>Example Door Schedule</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                        "</head>");
    }

    /**
     * Builds the HTML header
     */
    public void generateHeader() {
        stringBuilder.append(
            "    <header>\n" +
            "        <div id=\"project-details\">\n" +
            "            <h1>PROJECT: <span>Title of the project goes here</span></h1>  <!-- To fill in -->\n" +
            "            <h1>PROJECT NUMBER: <span>XXX ####</span></h1> <!-- To fill in -->\n" +
            "            <h1>DOOR SCHEDULE: <span>CZ01</span></h1> <!-- To fill in -->\n" +
            "        </div>\n" +
            "\n" +
            "        <div id=\"internal-door-summary\">\n" +
            "            <h2>INTERNAL DOOR SUMMARY</h2>\n" +
            "            \n" +
            "            <div id=\"internal-door-size-and-fire-rating\">\n" +
            "                <h3>INTERNAL DOOR SIZE AND FIRE RATING</h3>\n" +
            "                <ul>\n" +
            "                    <li>\n" +
            "                        <span>762 x 1981</span> <!-- To fill in -->\n" +
            "                        <span>FD30</span> <!-- To fill in -->\n" +
            "                        <span>15 required</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <span>838 x 1981</span> <!-- To fill in -->\n" +
            "                        <span>Not rated</span> <!-- To fill in -->\n" +
            "                        <span>10 required</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <span>Hinges:</span>\n" +
            "                        <span>75 required</span> <!-- To fill in -->\n" +
            "                        <span>(38 pair)</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <span>Handles:</span>\n" +
            "                        <span>25 required</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <span>Latches:</span>\n" +
            "                        <span>75 required</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                    <li>\n" +
            "                        <span>Internal Locks:</span>\n" +
            "                        <span>5 required</span> <!-- To fill in -->\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "    </header>\n"
        );
    }

    /**
     * Builds the HTML main
     */
    public void generateMain() {

        stringBuilder.append(
            "    <main>\n" +
            "        <div id=\"door-schedule\">\n" +
            "            <h2>DOOR SCHEDULE</h2>\n" +
            "\n"
        );
        generateItemDivs();
        stringBuilder.append(
            "        </div>\n" +
            "    </main>\n"
        );
    }

    public void generateItemDivs() {
        for (DetailPanel detailPanel : reportCreationPanel.getListOfDetailsPanels()) {
            stringBuilder.append(
                "            <div class=\"door-container\"> <!-- Could do this as an <ul> but it is more natural to use divs I feel here -->\n" + // door container div start
                "                <ul>\n" +
                "                    <li><h3>D1:</h3></li>\n" +
                "\n" +
                "                    <li>\n" +
                "                        <span>Location:</span>\n" +
                "                        <span>\n" +
                "                            <div>Ground Floor</div> <!-- To fill in -->\n" +
                "                            <div>Entrance Hall</div> <!-- To fill in -->\n" +
                "                        </span>\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Leaf Size:</span>\n" +
                "                        <span>762 x 1981</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Fire Rating:</span>\n" +
                "                        <span>FD 30 [3]</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Glazed:</span>\n" +
                "                        <span>Y [4]</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Door Type:</span>\n" +
                "                        <span>TBA by client</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Entrance level:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Part M Compliant</span>\n" +
                "                        <span>Yes</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Wall Construction:</span>\n" +
                "                        <span>89mm partition</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Internal / External:</span>\n" +
                "                        <span>Internal</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Part M Threshold:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Clear Opening</span>\n" +
                "                        <span>766</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Structural Opening:</span>\n" +
                "                        <span>950 x 2065</span> <!-- To fill in -->\n" +
                "                        <span>above screed</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Additional Ply Lining:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Frame detail:</span>\n" +
                "                        <span>CD20-1</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Sill detail:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Architrave type:</span>\n" +
                "                        <span>As 9.11.F</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"bold-font\">Ironmongery:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Hinges</span>\n" +
                "                        <span>1.5 pair</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Latch:</span>\n" +
                "                        <span>Yes</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span>Handles:</span>\n" +
                "                        <span>Yes</span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Locks:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "                    <li>\n" +
                "                        <span class=\"red-font\">Notes:</span>\n" +
                "                        <span></span> <!-- To fill in -->\n" +
                "                    </li>\n" +
                "\n" +
                "\n" +
                "                </ul>\n" +
                "\n" +
                "            </div>\n"// door container div end
            );
        }
    }
}

