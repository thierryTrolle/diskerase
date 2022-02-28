# DiskErase

DiskErase is a simple command line app to erase data from disk 

Deleting data from a disk or formatting it is not enough, it is possible to recover data. This application writes data to all the empty space.

## Usage

use binary on binary directory

```sh
java -jar diskerase-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

indicate the root of the file system where you want to fill the empty space and thus not be able to recover the data.

note: the user who runs the command must have write access to the file system.

## Compilation
 
```sh
mvn package
```