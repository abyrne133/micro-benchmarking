#!/bin/sh
java -ea -Xms512m -Xmx5000m -jar target/benchmarks.jar JavaHashMapBenchMarkPut -i 5 -bs 10 -wi 5 -wbs 10000 -t 1 -bm SingleShotTime -foe true -f 2 -o results/out.txt -rff results/machine-out.csv -p initialCapacity=16,100,1024,1000,10000,65536,100000,524288,1000000,1048576 -p loadFactor=0.125,0.250,0.375,0.5,0.625,0.75,0.875,1 -tu us
git add .
git commit -m "shell script test results"
git push