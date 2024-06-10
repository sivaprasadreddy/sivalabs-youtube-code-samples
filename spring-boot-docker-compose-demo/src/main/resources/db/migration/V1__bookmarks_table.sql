create sequence bookmark_id_seq start with 1000 increment by 50;

create table bookmarks (
    id bigint not null DEFAULT NEXT VALUE FOR bookmark_id_seq,
    title varchar(100) not null,
    url varchar(100) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

INSERT INTO bookmarks(title, url, created_at) VALUES
('How (not) to ask for Technical Help?','https://sivalabs.in/how-to-not-to-ask-for-technical-help', CURRENT_TIMESTAMP),
('Getting Started with Kubernetes','https://sivalabs.in/getting-started-with-kubernetes', CURRENT_TIMESTAMP),
('Few Things I learned in the HardWay in 15 years of my career','https://sivalabs.in/few-things-i-learned-the-hardway-in-15-years-of-my-career', CURRENT_TIMESTAMP),
('All the resources you ever need as a Java & Spring application developer','https://sivalabs.in/all-the-resources-you-ever-need-as-a-java-spring-application-developer', CURRENT_TIMESTAMP),
('SpringBoot Integration Testing using Testcontainers Starter','https://sivalabs.in/spring-boot-integration-testing-using-testcontainers-starter', CURRENT_TIMESTAMP),
('Testing SpringBoot Applications','https://sivalabs.in/spring-boot-testing', CURRENT_TIMESTAMP)
;