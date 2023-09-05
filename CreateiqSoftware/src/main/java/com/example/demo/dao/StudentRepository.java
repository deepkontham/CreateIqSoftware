package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.entity.Students;

@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Students, Long> {
	
	@Query(value=" from Students s join  s.batches b  where b.batchid=?1")

	List<Students> findByBatchid(Long batchid);

	
	
	List<Students> findBysName(String sName);
	
//	@Query("from Students s join Batches b where b.bName=?1")
//	@Query(value="select * from students s join batches b on s.batch_id=b.batchid where b.b_name=?1",nativeQuery = true)
	@Query(value=" from Students s join  s.batches b  where b.bName=?1")
//	@Query(value="SELECT s FROM Students s JOIN s.batches b WHERE b.bName = ?1")
	List<Students> findByBname12(String bname);

//	List<Students> findBysName(String sName);
	@Query(value=" from Students s join  s.courses c  where c.typeOfCourse=?1")

	List<Students> findByCouresesName(String typeOfCourse);

	
}
