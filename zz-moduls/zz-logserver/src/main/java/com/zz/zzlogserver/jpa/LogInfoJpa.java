package com.zz.zzlogserver.jpa;

import com.zz.zzlogserver.domain.LogInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/9/29 17:21
 */
public interface LogInfoJpa extends JpaRepository<LogInfo,Integer> {
}
