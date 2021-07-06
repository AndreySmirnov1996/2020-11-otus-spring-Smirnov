package ru.otus.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.service.CleanUpService;
import ru.otus.spring.service.ShowBookService;

import java.util.HashMap;
import java.util.List;


@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 1;
    private final Logger logger = LoggerFactory.getLogger("Batch");

    public static final String IMPORT_USER_JOB_NAME = "importUserJob";
    public static final String OUTPUT_FILE_NAME = "output.txt";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CleanUpService cleanUpService;

    @StepScope
    @Bean
    public MongoItemReader<MongoBook> reader(MongoTemplate template) {
        return new MongoItemReaderBuilder<MongoBook>()
                .name("mongoItemReader")
                .template(template)
                .jsonQuery("{}")
                .targetType(MongoBook.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoBook, MongoBook> processor(ShowBookService showBookService) {
        return showBookService::doHappyBirthday;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<MongoBook> writer(@Value("#{jobParameters['" + OUTPUT_FILE_NAME + "']}") String outputFileName) {
        return new FlatFileItemWriterBuilder<MongoBook>()
                .name("bookItemWriter")
                .resource(new FileSystemResource(outputFileName))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }


    @Bean
    public MethodInvokingTaskletAdapter cleanUpTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();

        adapter.setTargetObject(cleanUpService);
        adapter.setTargetMethod("cleanUp");

        return adapter;
    }


    @Bean
    public Job importUserJob(Step transformPersonsStep, Step cleanUpStep) {
        return jobBuilderFactory.get(IMPORT_USER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformPersonsStep)
                .next(cleanUpStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step transformPersonsStep(ItemReader<MongoBook> reader, FlatFileItemWriter<MongoBook> writer,
                                     ItemProcessor<MongoBook, MongoBook> itemProcessor) {
        return stepBuilderFactory.get("transformPersonsStep")
                .<MongoBook, MongoBook>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        logger.info("Начало чтения");
                    }

                    public void afterRead(@NonNull MongoBook o) {
                        logger.info("Конец чтения");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи");
                    }

                    public void afterWrite(@NonNull List list) {
                        logger.info("Конец записи");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<MongoBook, MongoBook>() {
                    public void beforeProcess(MongoBook o) {
                        logger.info("Начало обработки");
                    }

                    public void afterProcess(@NonNull MongoBook o, MongoBook o2) {
                        logger.info("Конец обработки");
                    }

                    public void onProcessError(@NonNull MongoBook o, @NonNull Exception e) {
                        logger.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Начало пачки");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Конец пачки");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        logger.info("Ошибка пачки");
                    }
                })
//                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    @Bean
    public Step cleanUpStep() {
        return this.stepBuilderFactory.get("cleanUpStep")
                .tasklet(cleanUpTasklet())
                .build();
    }
}
