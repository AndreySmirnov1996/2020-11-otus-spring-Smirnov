package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.crud.BookCrudService;

import static ru.otus.spring.config.JobConfig.IMPORT_USER_JOB_NAME;
import static ru.otus.spring.config.JobConfig.OUTPUT_FILE_NAME;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class JobShell {

    private final Job importUserJob;
    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final JobExplorer jobExplorer;

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "smj")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addString(OUTPUT_FILE_NAME, OUTPUT_FILE_NAME)
                .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "showInfo", key = "si")
    public void showInfo() {
        log.info("Migration job name: {}", importUserJob.getName());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_USER_JOB_NAME));
    }

}
