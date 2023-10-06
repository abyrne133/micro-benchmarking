#!/bin/sh

DEFAULT_ARGS="-ea -Xms512m -Xmx5000m -jar target/benchmarks.jar JavaHashMapBenchMarkPut -i 5 -wi 5 -wbs 10000 -t 1 -bm SingleShotTime -foe true -f 2 -p initialCapacity=16,100,1024,1000,10000,65536,100000,524288,1000000,1048576,2097152 -p loadFactor=0.125,0.250,0.375,0.5,0.625,0.75,0.875,1 -tu us"

BATCH_SIZE=10
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=100
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=1000
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=10000
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=100000
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=1000000
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push

BATCH_SIZE=5000000
java $DEFAULT_ARGS -bs $BATCH_SIZE -o results/out_$BATCH_SIZE.txt -rff results/machine_out_$BATCH_SIZE.csv
git add .
git commit -m "shell script test results batch $BATCH_SIZE"
git push
