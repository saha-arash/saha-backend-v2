package ir.saha.web.rest.vm;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BarChart {

    public static void main(String[] args) throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("barchart");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1.0));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("BarChart");
            chart.setTitleOverlay(false);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            // Use a category axis for the bottom axis.
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("x");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("f(x)");
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

            XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            XDDFChartData.Series series1 = data.addSeries(xs, ys1);
            series1.setTitle("2x", null);
            XDDFChartData.Series series2 = data.addSeries(xs, ys2);
            series2.setTitle("3x", null);
            chart.plot(data);

            // in order to transform a bar chart into a column chart, you just need to change the bar direction
            XDDFBarChartData bar = (XDDFBarChartData) data;
            bar.setBarDirection(BarDirection.COL);
            // looking for "Stacked Bar Chart"? uncomment the following line
            // bar.setBarGrouping(BarGrouping.STACKED);

            solidFillSeries(data, 0, PresetColor.CHARTREUSE);
            solidFillSeries(data, 1, PresetColor.TURQUOISE);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("/home/arsham/Downloads/ooxml-bar-chart.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    private static void solidFillSeries(XDDFChartData data, int index, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFChartData.Series series = data.getSeries().get(index);
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }
}