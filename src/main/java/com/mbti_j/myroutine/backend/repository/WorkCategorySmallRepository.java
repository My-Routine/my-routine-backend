package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.WorkCategoryLarge;
import com.mbti_j.myroutine.backend.model.entity.WorkCategorySmall;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkCategorySmallRepository extends JpaRepository<WorkCategorySmall, Long> {

    List<WorkCategorySmall> findAllByWorkCategoryLarge(WorkCategoryLarge workCategoryLarge);
}