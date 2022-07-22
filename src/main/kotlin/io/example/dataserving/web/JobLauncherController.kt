package io.example.dataserving.web

import io.example.dataserving.job.incrementer.DateIncrementer
import org.springframework.batch.core.Job
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("batch")
class JobLauncherController constructor(
    private val jobLauncher: JobLauncher,
    private val simpleJob4: Job,
    private val dateIncrementer: DateIncrementer
) {

    @PostMapping("csv")
    fun runCsvJob() {

        val jobParameters = dateIncrementer.getNext(null)

        jobLauncher.run(simpleJob4, jobParameters)
    }
}