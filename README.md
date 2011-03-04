# Spring Batch Workshop

## Samples

* hello world: writing "Hello World" on the console
* chunk processing: simple illustration of the chunk processing
* flat file reading: reading from a flat file (and writing to a database)
* skip: skipping a badly formatted line in a CSV file instead of crashing
* dynamic job parameters: passing parameters to job artefacts (e.g. input file to an item reader)
* JDBC paging: reading large result sets from a database using paging
* execution metadata: seeing how Spring Batch stores execution metadata to enable restart and ease monitoring

TBD:

* embedding business logic in an ItemProcessor
* triggering a job execution with an external event (using Spring Integration)
* scheduling with Spring Scheduler
* scaling with local partitioning

## Installation instructions

To get the code, two solutions:

### If you have git installed

just type the following command:
    $ git clone git://github.com/acogoluegnes/Spring-Batch-Workshop.git

### If you don't have git installed or don't want to use it

There's a "Downloads" link on the github webpage to download an archive.

### Once you downloaded the code

The projects are based on Maven, so any IDE with Maven support should be fine
(import the projects as Maven projects with Eclipse and the M2Eclipse plugin for
example).

To check that everything is fine, go to the root of the project and launch the tests:
	$ mvn test

## Content

Each Spring Batch topic is covered by two projects: the -start project is
the one you should start from. The -solution is the solution. You just need
to follow the TODOs. The concepts are (quickly) covered in the PDF file
at the root of this project. The PDF contains additional instructions and hints to
work on the projects.
