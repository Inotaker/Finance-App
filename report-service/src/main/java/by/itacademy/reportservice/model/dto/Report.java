package by.itacademy.reportservice.model.dto;

public class Report {
    //public class Report<T> {
    private String uuid;
    private long dt_create;//$int64
    private long dt_update;//$int64
    private String status;//ENUM
    private String type;//ENUM
    /**
     * readOnly: true
     * example: Дата совершения операции: 01.01.2021 - 01.01.2022
     * Описание параметров отчёта в человеческом формате
     **/
    private String description;

//    private T params;//params oneOf

}
