# spring-boot-data-cassandra

### Overview of Spring Boot Cassandra CRUD example

We will build a Spring Boot Cassandra Rest CRUD API for a Tutorial application in that:

* Each Tutorial has id, title, description, published status.
* Apis help to create, retrieve, update, delete Tutorials.
* Apis also support custom finder methods such as find by published status or by title.

### Set up Cassandra Database

Open Cassandra CQL Shell:

– Create Cassandra naruto keyspace:

```shell
create keyspace naruto with replication={'class':'SimpleStrategy', 'replication_factor':1};
```

– Create tutorial table in the keyspace:

```shell
use naruto;

CREATE TABLE tutorial(
id timeuuid PRIMARY KEY,
title text,
description text,
published boolean
);
```

Do you remember that we have `findByTitleContaining()` method?
The method executes `SELECT * FROM tutorial WHERE tutorial LIKE '%title%';` in Cassandra.

So we need the to create a custom index that has options with mode: CONTAINS along with analyzer_class to make
case_sensitive effective.
Run the command:

```shell
CREATE CUSTOM INDEX idx_title ON naruto.tutorial (title)
USING 'org.apache.cassandra.index.sasi.SASIIndex'
WITH OPTIONS = {
'mode': 'CONTAINS',
'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.NonTokenizingAnalyzer',
'case_sensitive': 'false'};
```

These are APIs that we need to provide:

| Methods |             	Urls	              |                                         Actions |
|---------|:-------------------------------:|------------------------------------------------:|
| POST	   |         /api/tutorials	         |                             create new Tutorial |
| GET	    |         /api/tutorials	         |                          retrieve all Tutorials |
| GET	    |       /api/tutorials/:id	       |                      retrieve a Tutorial by :id |
| PUT	    |       /api/tutorials/:id	       |                        update a Tutorial by :id |
| DELETE	 |       /api/tutorials/:id	       |                        delete a Tutorial by :id |
| DELETE	 |         /api/tutorials	         |                            delete all Tutorials |
| GET	    |    /api/tutorials/published	    |                    find all published Tutorials |
| GET	    | /api/tutorials?title=[keyword]	 | find all Tutorials which title contains keyword |
