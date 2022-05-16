package by.itacademy.accountservice.dao.api;

import by.itacademy.accountservice.model.entity.AccountEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAccountStorage extends JpaRepository<AccountEntity, UUID> {
//    @Query("select d from AccountEntity d where d.dt_update = :dt_update")
//    AccountEntity getByDt_update(@Param("dt_update") long dt_update);

    List<AccountEntity> findAllBy(Pageable pageable);

    @Override
    long count();
}
