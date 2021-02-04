package ir.saha.web.rest;

import java.io.FileOutputStream;
import java.util.List;

import ir.saha.domain.HesabResi;
import ir.saha.domain.NirooCode;
import ir.saha.domain.Yegan;
import ir.saha.repository.HesabResiRepository;
import ir.saha.repository.NirooCodeRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;

import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BarChart {

    @Autowired
    private NirooCodeRepository nirooCodeRepository;


    @Autowired
    private HesabResiRepository hesabResiRepository;
    @GetMapping("/excel")
    public  void bilanSalGhable(@RequestParam(name="sal") int sal) throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");

        Row row;
        Cell cell;

        row = sheet.createRow(0);
        row.createCell(0);
        row.createCell(10).setCellValue("شرح");
        row.createCell(9).setCellValue("مدت ماموریت پیشبینی شده");
        row.createCell(8).setCellValue("مدت ماموریت انجام شده");

        List<NirooCode> all = nirooCodeRepository.findAll();
        for (int r = 0; r < all.size(); r++) {
            NirooCode nirooCode = all.get(r);
            row = sheet.createRow(r);
            cell = row.createCell(10);
            cell.setCellValue(nirooCode.getName());
            int totalKarbarSize=0;
            for (Yegan yegan : nirooCode.getYegans()) {
                totalKarbarSize=totalKarbarSize+yegan.getKarbars().size();
            }
            HesabResi allBySal = hesabResiRepository.findAllBySal(sal);
            cell = row.createCell(9);
            cell.setCellValue(totalKarbarSize*(365-allBySal.getTedadRoozayeTatilSal()));
            cell = row.createCell(8);
            cell.setCellValue(new java.util.Random().nextDouble());
        }

        XSSFDrawing drawing = (XSSFDrawing)sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20);

        XSSFChart chart = drawing.createChart(anchor);

        CTChart ctChart = ((XSSFChart)chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBoolean.setVal(true);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);

        for (int r = 2; r < 6; r++) {
           CTBarSer ctBarSer = ctBarChart.addNewSer();
           CTSerTx ctSerTx = ctBarSer.addNewTx();
           CTStrRef ctStrRef = ctSerTx.addNewStrRef();
           ctStrRef.setF("Sheet1!$A$" + r);
           ctBarSer.addNewIdx().setVal(r-2);
           CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
           ctStrRef = cttAxDataSource.addNewStrRef();
           ctStrRef.setF("Sheet1!$B$1:$D$1");
           CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
           CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
           ctNumRef.setF("Sheet1!$B$" + r + ":$D$" + r);

           //at least the border lines in Libreoffice Calc ;-)
           ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] {0,0,0});

        }

        //telling the BarChart that it has axes and giving them Ids
        ctBarChart.addNewAxId().setVal(123456);
        ctBarChart.addNewAxId().setVal(123457);

        //cat axis
        CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(false);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //val axis
        CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); //id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewDelete().setVal(false);
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //legend
        CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);

System.out.println(ctChart);

        FileOutputStream fileOut = new FileOutputStream("/home/arsham/Downloads/BarChart.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
}
