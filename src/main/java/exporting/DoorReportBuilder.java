package exporting;

import gui.panels.ProjectDetailsPanel;
import gui.panels.ReportCreationPanel;
import gui.panels.details.*;
import items.doors.Door;

import java.awt.*;
import java.io.*;


/**
 * A class used to take the information in the current app and build an HTML website from it
 * Would use text blocks however it would mean I couldn't add in values
 */
public class DoorReportBuilder implements ReportBuilder{

    private ReportCreationPanel reportCreationPanel;
    private ProjectDetailsPanel projectDetailsPanel;
    private StringBuilder stringBuilder;

    public DoorReportBuilder(ReportCreationPanel reportCreationPanel, ProjectDetailsPanel projectDetailsPanel) {
        this.reportCreationPanel = reportCreationPanel;
        this.projectDetailsPanel = projectDetailsPanel;
        stringBuilder = new StringBuilder();
    }

    /**
     * Builds the HTML for the file export
     */
    public void generateHTML() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n");
        stringBuilder.append("<html lang=\"en\">\n");
        generateHead();
        stringBuilder.append("<body>\n");
        generateHeader();
        generateMain();
        stringBuilder.append(
            "</body>\n" +
            "</html>");
        writeToFile();
    }

    /**
     * Writes the contents of stringbuilder to a file to then be opened
     */
    public void writeToFile() {
        try {
            String filePath = "src/main/resources/currentExport/currentExport.html";
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println(stringBuilder);
            pw.flush();
            pw.close();

            File htmlFile = new File(filePath);
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (IOException e) {
            System.out.println("Exception when writing to file");
            throw new RuntimeException(e);
        }
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
                        "    <title>"+projectDetailsPanel.getTitleField().getText()+" / "+projectDetailsPanel.getProjectNumberField().getText()+" / "+projectDetailsPanel.getItemNumberField().getText()+"</title>\n" +
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
            "            <h1>PROJECT: <span>"+projectDetailsPanel.getTitleField().getText()+"</span></h1>  <!-- To fill in -->\n" +
            "            <h1>PROJECT NUMBER: <span>"+projectDetailsPanel.getProjectNumberField().getText()+"</span></h1> <!-- To fill in -->\n" +
            "            <h1>DOOR SCHEDULE: <span>"+projectDetailsPanel.getItemNumberField().getText()+"</span></h1> <!-- To fill in -->\n" +
            "        </div>\n" +
            "\n" +
            "        <div id=\"internal-door-summary\">\n" +
            "            <h2>INTERNAL DOOR SUMMARY</h2>\n" +
            "            \n" +
            "            <div id=\"internal-door-size-and-fire-rating\">\n" + //todo all of the rest of this relative to the statistics section
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

    /**
     * Generates the different divs and appends them to the stringbuilder relative to the number of items on the screen
     * todo if attribute is blank then ignore it (thats what the bit in red means, should clarify if wanting done on only red sections or not)
     */
    public void generateItemDivs() {
        for (DetailPanel detailPanel : reportCreationPanel.getListOfDetailsPanels()) {
            Door door = ((DoorDataPanel) detailPanel.getDataPanel()).getDoor();
            // I know this will work because the only way this data panel could be added and this report builder added is if the report state is in Door
            stringBuilder.append("            <div class=\"door-container\"> <!-- Could do this as an <ul> but it is more natural to use divs I feel here -->\n"); // door container div start
            stringBuilder.append("                <ul>\n");
            stringBuilder.append("                    <li><h3>"+door.getName()+"</h3></li>\n");
            stringBuilder.append("\n");

            stringBuilder.append("                    <li>\n");
            stringBuilder.append("                        <span>Location:</span>\n");
            stringBuilder.append("                        <span>\n");
            stringBuilder.append("                            <div>"+ door.getFloor()+"</div> <!-- To fill in -->\n");
            stringBuilder.append("                            <div>"+ door.getRoom()+"</div> <!-- To fill in -->\n");
            stringBuilder.append("                        </span>\n");
            stringBuilder.append("                    </li>\n");


            if (!(door.getLeafType()).isEmpty() || !(door.getLeafWidth()).isEmpty() ||
                    !(door.getLeafHeight()).isEmpty() || !(door.getLeafNumber()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Leaf Size:</span>\n");
                stringBuilder.append("                        <span>\n");
                stringBuilder.append("                            <div>" + door.getLeafType() + "</div> <!-- To fill in -->\n");
                stringBuilder.append("                            <div>" + door.getLeafWidth() + " x " + door.getLeafHeight() + "</div> <!-- To fill in -->\n");
                stringBuilder.append("                            <div>" + door.getLeafNumber() + "</div> <!-- To fill in -->\n");
                stringBuilder.append("                        </span>\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getFireRating()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Fire Rating:</span>\n");
                stringBuilder.append("                        <span>" + door.getFireRating() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getGlazed()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Glazed:</span>\n");
                stringBuilder.append("                        <span>" + door.getGlazed() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getDoorType()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Door Type:</span>\n");
                stringBuilder.append("                        <span>" + door.getDoorType() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getEntranceLevel()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Entrance level:</span>\n");
                stringBuilder.append("                        <span>" + door.getEntranceLevel() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getPartMCompliant()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Part M Compliant</span>\n");
                stringBuilder.append("                        <span>" + door.getPartMCompliant() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getWallConstruction()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Wall Construction:</span>\n");
                stringBuilder.append("                        <span>" + door.getWallConstruction() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getInternalExternal()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Internal / External:</span>\n");
                stringBuilder.append("                        <span>" + door.getInternalExternal() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getPartMThreshold()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Part M Threshold:</span>\n");
                stringBuilder.append("                        <span>" + door.getPartMThreshold() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getClearOpening()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Clear Opening</span>\n");
                stringBuilder.append("                        <span>" + door.getClearOpening() + "</span> <!-- To fill in -->\n"); //todo
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getStructuralOpeningWidth()).isEmpty()
                    || !(door.getStructuralOpeningHeight()).isEmpty()
                    || !(door.getStructuralOpeningDetails()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Structural Opening:</span>\n");
                stringBuilder.append("                        <span>" + door.getStructuralOpeningWidth() + " x " + door.getStructuralOpeningHeight() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                        <span>" + door.getStructuralOpeningDetails() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getAdditionalPlyLining()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Additional Ply Lining:</span>\n");
                stringBuilder.append("                        <span>" + door.getAdditionalPlyLining() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }
            if (!(door.getFrameDetails()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Frame detail:</span>\n");
                stringBuilder.append("                        <span>" + door.getFrameDetails() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }
            if (!(door.getSillDetails()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Sill detail:</span>\n");
                stringBuilder.append("                        <span>" + door.getSillDetails() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }
            if (!(door.getArchitraveType()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Architrave type:</span>\n");
                stringBuilder.append("                        <span>" + door.getArchitraveType() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            stringBuilder.append("                    <li>\n");
            stringBuilder.append("                        <span class=\"bold-font\">Ironmongery:</span>\n");
            stringBuilder.append("                        <span></span>\n"); // leave as it is used with the style to align the items
            stringBuilder.append("                    </li>\n");


            if (!(door.getIronmongery().getHinges()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Hinges</span>\n");
                stringBuilder.append("                        <span>" + door.getIronmongery().getHinges() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getIronmongery().getLatch()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Latch:</span>\n");
                stringBuilder.append("                        <span>" + door.getIronmongery().getLatch() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getIronmongery().getHandle()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Handles:</span>\n");
                stringBuilder.append("                        <span>" + door.getIronmongery().getHandle() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getIronmongery().getLock()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Locks:</span>\n");
                stringBuilder.append("                        <span>" + door.getIronmongery().getLock() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            if (!(door.getAdditionalNotes()).isEmpty()) {
                stringBuilder.append("                    <li>\n");
                stringBuilder.append("                        <span>Additional Notes:</span>\n");
                stringBuilder.append("                        <span>" + door.getAdditionalNotes() + "</span> <!-- To fill in -->\n");
                stringBuilder.append("                    </li>\n");
            }

            stringBuilder.append("\n");
            stringBuilder.append("\n");
            stringBuilder.append("                </ul>\n");
            stringBuilder.append("\n");
            stringBuilder.append("            </div>\n"// door container div end
            );
        }
    }
}

