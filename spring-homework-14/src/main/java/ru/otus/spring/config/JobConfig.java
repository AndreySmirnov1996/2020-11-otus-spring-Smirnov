package ru.otus.spring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.otus.spring.domain.H2Book;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.repositories.H2BookRepository;
import ru.otus.spring.service.TransformBookService;

import java.util.HashMap;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JobConfig {

    public static final String IMPORT_USER_JOB_NAME = "importUserJob";
    private static final int CHUNK_SIZE = 5;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

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
    public ItemProcessor<MongoBook, H2Book> processor(TransformBookService transformBookService) {
        return transformBookService::doH2BookFromMongoBook;
    }

    @StepScope
    @Bean
    public RepositoryItemWriter<H2Book> writer(H2BookRepository h2BookRepository) {
        return new RepositoryItemWriterBuilder<H2Book>()
                .repository(h2BookRepository)
                //.methodName("saveAll")
                .build();
    }

    @Bean
    public Job importUserJob(Step transformBookStep) {
        return jobBuilderFactory.get(IMPORT_USER_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformBookStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step transformBookStep(ItemReader<MongoBook> reader, ItemWriter<H2Book> writer,
                                  ItemProcessor<MongoBook, H2Book> itemProcessor) {
        return stepBuilderFactory.get("transformBookStep")
                .<MongoBook, H2Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(@NonNull MongoBook o) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(@NonNull Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(@NonNull List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<MongoBook, H2Book>() {
                    public void beforeProcess(MongoBook o) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(@NonNull MongoBook o, H2Book o2) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(@NonNull MongoBook o, @NonNull Exception e) {
                        log.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }
}
