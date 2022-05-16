package by.itacademy.accountschedulerservice.dao.api;

import by.itacademy.accountschedulerservice.model.entity.ScheduledOperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IScheduledOperationStorage extends JpaRepository<ScheduledOperationEntity, UUID> {
    List<ScheduledOperationEntity> findAllBy(Pageable pageable);

}
