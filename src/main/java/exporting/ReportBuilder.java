package exporting;

/**
 * Interface to ensure all the report builders have the same key methods
 */
public interface ReportBuilder {
    void generateHTML();
    void generateHead();
    void generateHeader();
    void generateMain();
    void generateItemDivs();
    void writeToFile();
}
