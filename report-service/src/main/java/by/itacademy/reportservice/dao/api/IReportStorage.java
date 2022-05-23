package by.itacademy.reportservice.dao.api;

import by.itacademy.reportservice.model.entity.ReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IReportStorage extends JpaRepository<ReportEntity, UUID> {
    List<ReportEntity> findAllBy(Pageable pageable);
}
