package by.itacademy.reportservice.services;

import by.itacademy.reportservice.controller.advice.ValidationException;
import by.itacademy.reportservice.dao.api.IReportStorage;
import by.itacademy.reportservice.model.dto.Page;
import by.itacademy.reportservice.model.dto.Report;
import by.itacademy.reportservice.model.dto.enums.ReportStatus;
import by.itacademy.reportservice.model.dto.enums.ReportType;
import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ReportService {
    private final IReportStorage storage;
    private final ConversionService conversionService;

    public ReportService(IReportStorage storage, ConversionService conversionService) {
        this.storage = storage;
        this.conversionService = conversionService;
    }

    public Page<Report> getPage(String size, String page) {
        Page<Report> reportPage = new Page<>();

        int p = Integer.parseInt(page) - 1;/**отсчет страниц идет с нуля*/
        int s = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(p, s);
        List<ReportEntity> reportEntities = this.storage.findAllBy(pageable);
        List<Report> reports = new ArrayList<>();
        for (ReportEntity reportEntity : reportEntities) {
            reports.add(this.conversionService.convert(reportEntity, Report.class));
        }
        reportPage.setContent(reports);
        if (!pageable.hasPrevious()) {
            reportPage.setFirst(true);
        }

        int totalElement = this.storage.findAll().size();
        int totalPage = totalElement / s;

        if (totalElement % s > 0) {/**Если есть остаток после деления, значит есть неполная страница*/
            totalPage++;
        }
        if (pageable.getPageSize() > reportEntities.size() || pageable.getPageSize() == totalElement) {
            reportPage.setLast(true);
        }
        reportPage.setTotal_pages(totalPage);
        reportPage.setTotal_element(totalElement);
        reportPage.setNumber_of_elements(reportEntities.size());
        reportPage.setNumber(pageable.getPageNumber() + 1);/**отсчет страниц идет с нуля*/
        return reportPage;
    }

    public Report transformReport(Map<String, Object> reportMap, String type) {
        List<UUID> accounts = new ArrayList<>();
        long from = 0;
        long to = 0;
        List<UUID> categories = new ArrayList<>();
        long time = System.currentTimeMillis();

        for (Map.Entry<String, Object> entry : reportMap.entrySet()) {
            if (entry.getKey().equals("accounts")) {
                accounts = (List<UUID>) entry.getValue();
            }
            if (entry.getKey().equals("from")) {
                from = Long.parseLong((String) entry.getValue());
            }
            if (entry.getKey().equals("to")) {
                to = Long.parseLong((String) entry.getValue());
            }
            if (entry.getKey().equals("categories")) {
                categories = (List<UUID>) entry.getValue();
            }
        }
        switch (ReportType.valueOf(type)) {
            /** readOnly: true
             example: Дата совершения операции: 01.01.2021 - 01.01.2022
             Описание параметров отчёта в человеческом формате **/
            case BY_CATEGORY:
                return Report.Builder.aReport().withDt_create(time).withDt_update(time).withUuid(UUID.randomUUID()).withDescription("Период совершения операций: " + this.conversionService.convert(from, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + " - " + this.conversionService.convert(to, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + ". " + "Отчет по категориям: ???").withParams(reportMap).withType(type).withStatus(ReportStatus.PROGRESS.toString()).build();
            case BALANCE:
                return Report.Builder.aReport().withDt_create(time).withDt_update(time).withUuid(UUID.randomUUID()).withDescription("Период совершения операций: " + this.conversionService.convert(from, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + " - " + this.conversionService.convert(to, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + ". " + "Баланс по счетам: ???").withParams(reportMap).withType(type).withStatus(ReportStatus.PROGRESS.toString()).build();
            case BY_DATE:
                return Report.Builder.aReport().withDt_create(time).withDt_update(time).withUuid(UUID.randomUUID()).withDescription("Период совершения операций: " + this.conversionService.convert(from, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + " - " + this.conversionService.convert(to, LocalDateTime.class).format(DateTimeFormatter.ISO_DATE_TIME) + ". " + "Отчет по датам: ???").withParams(reportMap).withType(type).withStatus(ReportStatus.PROGRESS.toString()).build();
            default:
                throw new ValidationException("то то пошло не так");
        }
    }


    public ReportEntity add(ReportEntity entity) {
//
//        // создание самого excel файла в памяти
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // создание листа с названием "Просто лист"
//        HSSFSheet sheet = workbook.createSheet("Просто лист");
//
//        // заполняем список какими-то данными
//        List<ReportParamByCategory> paramByCategories = fillData();
//        paramByCategories.add(new ReportParamByCategory());
//
//        // счетчик для строк
//        int rowNum = 0;
//
//        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
//        Row row = sheet.createRow(rowNum);
//        row.createCell(0).setCellValue("From");
//        row.createCell(1).setCellValue("To");
//        row.createCell(2).setCellValue("Category");
//        row.createCell(3).setCellValue("Accounts");
//
//        // заполняем лист данными
//        for (ReportParamByCategory reportParam : paramByCategories) {
//            createSheetHeader(sheet, ++rowNum, reportParam.);
//        }
//
//        // записываем созданный в памяти Excel документ в файл
//        try (FileOutputStream out = new FileOutputStream("I:\\Apache POI Excel File.xls")) {
//            workbook.write(out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Excel файл успешно создан!");
        entity.setUuid(UUID.randomUUID());
        this.storage.save(entity);
        return entity;
    }

    // заполнение строки (rowNum) определенного листа (sheet)
    // данными  из dataModel созданного в памяти Excel файла


//    private static void createSheetHeader(HSSFSheet sheet, int rowNum, ReportParamByCategory paramByCategories) {
//        Row row = sheet.createRow(rowNum);
//
//        row.createCell(0).setCellValue(paramByCategories.getFrom());
//        row.createCell(1).setCellValue(paramByCategories.getTo());
//        row.createCell(2).setCellValue((Date) paramByCategories.getCategories());
//        row.createCell(3).setCellValue((Date) paramByCategories.getAccounts());
//    }
//
//    // заполняем список рандомными данными
//    // в реальных приложениях данные будут из БД или интернета
//    private static List<ReportParamByCategory> fillData() {
//        List<ReportParamByCategory> reportParam = new ArrayList<>();
//        reportParam.add(new ReportParamByCategory());
//        reportParam.add(new ReportParamByCategory());
//        reportParam.add(new ReportParamByCategory());
//
//        return reportParam;
//    }
}
