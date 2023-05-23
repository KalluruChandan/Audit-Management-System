package com.cognizant.auditseverity.service;

import com.cognizant.auditseverity.model.AuditBenchMark;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "audit-benchmark", url = "${Benchmark_URL}")
public interface BenchMarkFeignService {
    @GetMapping("/AuditBenchmark")
    public List<AuditBenchMark> getAllBenchmarks(@RequestHeader("Authorization") String jwt);
}
