See pom.xml, this project depends on a separate Repo.

set memory as appropriate...

mvn clean verify
java -ea -Xms512m -Xmx5000m -jar target/benchmarks.jar
