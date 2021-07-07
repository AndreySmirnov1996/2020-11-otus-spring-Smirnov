package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class JobShell {

    private final Job importUserJob;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "smj")
    public void startMigrationJobWithJobLauncher() throws Exception {
        jobLauncher.run(importUserJob, new JobParametersBuilder().toJobParameters());
    }
}
