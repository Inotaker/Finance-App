package by.itacademy.reportservice.dao.api;

import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IReportStorage extends JpaRepository<ReportEntity, UUID> {
}
