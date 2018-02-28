stvmeek
=======

stvmeek is based on Roy Ward's Voting System Toolbox [http://prdownloads.sourceforge.net/votesystem/vote-0-4.zip]


Building with maven
```
mvn clean install
```

Sample invocation

```
java -cp target/stvmeek-1.0-SNAPSHOT.jar VoteMain -seats 3 examples/colours-cand.csv
```

Sample output

```
Blue                                     not elected
Green                                    elected
Orange                                   elected
Red                                      not elected
Violet                                   not elected
Yellow                                   elected
Done!
```