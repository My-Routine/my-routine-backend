package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.Work;
import com.mbti_j.myroutine.backend.model.entity.WorkCategorySmall;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findAllByWorkCategorySmall(WorkCategorySmall workCategorySmall);
}
