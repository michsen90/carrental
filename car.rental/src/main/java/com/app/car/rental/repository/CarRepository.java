package com.app.car.rental.repository;

import com.app.car.rental.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {

    List<Cars> findByBrandAndModel(String brand, String model);
    List<Cars> findByEngine(String engine);
    Cars findCarByCarId(Long id);
    List<Cars> findByModel(String model);



    @Query(value = "select c from Cars c where c.carId NOT IN (" +
            " select c.carId from Cars c INNER JOIN c.booking b " +
            "where ( b.startDate <= :start_date AND b.endDate >= :start_date) OR " +
            "( b.startDate < :end_date AND b.endDate >= :end_date) OR" +
            "( :start_date <= b.startDate and :end_date >= b.endDate))" )
    List<Cars> findCarsByQuery(@Param("start_date") Date startDate, @Param("end_date") Date endDate);

}
