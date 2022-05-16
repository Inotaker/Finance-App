package by.itacademy.reportservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "report", schema = "report_service")
public class ReportEntity {
    @Id
    private String uuid;
    private LocalDateTime dt_create;//$int64
    private LocalDateTime dt_update;//$int64
    String status;//ENUM
    String type;//ENUM
    String description;/** readOnly: true
     example: Дата совершения операции: 01.01.2021 - 01.01.2022
     Описание параметров отчёта в человеческом формате **/
    //params oneOf

}
