# bowling

Application to calculate the scores of ten pin bowling game 
General rules of game : https://en.wikipedia.org/wiki/Ten-pin_bowling


### Problem input/output

(When scoring “X” indicates a strike, “/” indicates a spare, “-” indicates a miss)

- X X X X X X X X X XXX (12 rolls: 12 strikes) = 10 frames * 30 points = 300
- 9- 9- 9- 9- 9- 9- 9- 9- 9- 9- (20 rolls: 10 pairs of 9 and miss) = 10 frames * 9 points = 90
- 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 (21 rolls: 10 pairs of 5 and spare, with a final 5) = 10 frames * 15 points = 150


### Environment
```bash
java -version  java 11
```
```bash
mvn  -version  
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T09:58:13+02:00)
Maven home: /usr/local/Cellar/maven/3.5.2/libexec
```
### Running the tests
```bash
mvn test 
```

### Build and run the app

1. mvn install
2. Run the spring boot app 
3. curl -X POST \
    http://localhost:8080/bowling/scores/calculate \
    -F 'frames=-/ -/ -/ -/ -/ -/ -/ -/ -/ -/2'

### Swagger avaiable at 

http://localhost:8080/swagger-ui.html
