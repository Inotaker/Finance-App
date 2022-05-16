package by.itacademy.accountservice.dao.api;

import by.itacademy.accountservice.model.entity.OperationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IOperationStorage extends JpaRepository<OperationEntity, UUID> {
    OperationEntity getFirstBy();
//    Operation deleteByUuidAndDt_update(@Param("uuid") String uuid, @Param("dt_update") long dt_update);

    List<OperationEntity> findAllByAccountAndAvailable(UUID uuid, boolean b);

    List<OperationEntity> findAllByAccountAndAvailable(Pageable pageable, UUID uuid, boolean b);
}
